package bootdemo.service;

import bootdemo.dao.ArticleMapper;
import bootdemo.dao.CommentMapper;
import bootdemo.dao.UserMapper;
import bootdemo.entity.Article;
import bootdemo.entity.Comment;
import bootdemo.entity.ResultCode;
import bootdemo.entity.User;
import bootdemo.exception.ResultException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by huang on 2017/5/21.
 */
@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @CacheEvict(value = {"getCommentsByUserName", "getCommentsByArticle", "getCommentCountByArticle"}, allEntries = true)
    public Comment saveComment(int uid, String content, int articleId) throws Exception {
        checkUid(uid);
        Article article = articleMapper.findById(articleId);
        if (article == null) {
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR, "没有该文章");
        }
        Comment comment = new Comment();
        comment.setCommitTime(new Date(System.currentTimeMillis()));
        comment.setArticle(article);
        comment.setContent(content);
        commentMapper.saveComment(comment);
        return comment;
    }

    @CacheEvict(value = {"getCommentsByUserName", "getCommentsByArticle", "getCommentCountByArticle"}, allEntries = true)
    public int deleteComment(int uid, int commentId)throws Exception {
        checkUid(uid);
        commentMapper.deleteComment(commentId, uid);
        return 1;
    }

    @Cacheable(value = "getCommentsByUserName")
    public List<Comment> getCommentsByUserName(int pageNum, int pageSize, int uid) throws Exception {
        checkUid(uid);
        PageHelper.startPage(pageNum, pageSize);
        return commentMapper.findCommentsByUid(uid);
    }

    @Cacheable(value = "getCommentsByArticle")
    public List<Comment> getCommentsByArticle(int pageNum, int pageSize, int articleId) throws Exception {

        PageHelper.startPage(pageNum, pageSize);
        List<Comment> comments = commentMapper.getCommentByArticleId(articleId);
        return comments;
    }

    @Cacheable(value = "getCommentCountByArticle")
    public Integer getCommentCountByArticle(int articleId) throws Exception {

        return commentMapper.getCommentCountByArticleId(articleId);
    }

    private void checkUid(int uid) throws ResultException {

        int count = userMapper.isCheckUserID(uid);
        if (count == 0) {
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR, "没有该用户");
        }
    }

}
