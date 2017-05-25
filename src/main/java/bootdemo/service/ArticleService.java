package bootdemo.service;

import bootdemo.dao.*;
import bootdemo.entity.*;
import bootdemo.exception.ResultException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by huang on 2017/5/21.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTypeMapper articleTypeMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private FavMapper favMapper;

    @Autowired
    private CommentMapper commentMapper;
    @Cacheable(value = "getAllArticles")
    public List<Article> getAllArticles(int pageNum,int pageSize,int uid){
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles =  articleMapper.queryAll();
         for (Article article :articles){
            int favCount = favMapper.getFavCountByarticleId(article.getId());
            article.setFavCount(favCount);
            //只获取第一页的数据
            int commentCount = commentMapper.getCommentCountByArticleId(article.getId());
             PageHelper.startPage(1,2); //test
             List<Comment> comments = commentMapper.getCommentByArticleId(article.getId());
             article.setCommentCount(commentCount);
             article.setComments(comments);
             article.setLike(favMapper.checkFavByUid(uid,article.getId()));
         }
         return articles;
    }
    @CacheEvict(value = "getAllArticles",allEntries = true)
    public Article saveArticle(Article article,int uid,int typeId)throws Exception{
        checkUid(uid);
        if(StringUtils.isEmpty(article.getTitle())){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"文章标题不能为空！");
        }
        ArticleType articleType = articleTypeMapper.findById(typeId);
        if(articleType == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该文章分类！");
        }
        article.setType(articleType);
        article.setCreateTime(new Date(System.currentTimeMillis()));
        articleMapper.save(article);
        return article;
    }

    public ArticleType saveArticleType(ArticleType articleType){
        if(StringUtils.isEmpty(articleType.getTitle())){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"分类名称不能为空！");
        }
        articleType.setCreateTime(new Date(System.currentTimeMillis()));
        articleTypeMapper.save(articleType);
        return articleType;
    }

    public List<ArticleType> getAllArticleType(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return articleTypeMapper.queryAll();
    }
    private void checkUid(int uid) throws Exception{

        int count = userMapper.isCheckUserID(uid);
        if (count == 0){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该用户");
        }
    }

}
