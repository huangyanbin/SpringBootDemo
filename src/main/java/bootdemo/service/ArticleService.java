package bootdemo.service;

import bootdemo.dao.ArticleMapper;
import bootdemo.dao.ArticleTypeMapper;
import bootdemo.dao.UserMapper;
import bootdemo.entity.Article;
import bootdemo.entity.ArticleType;
import bootdemo.entity.ResultCode;
import bootdemo.entity.User;
import bootdemo.exception.ResultException;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.sql.Date;
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

    public List<Article> getAllArticles(int pageNum,int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        return  articleMapper.queryAll();
    }

    public Article saveArticle(Article article,String userName,int typeId)throws Exception{

        if(StringUtils.isEmpty(article.getTitle())){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"文章标题不能为空！");
        }
        int count = userMapper.isCheckUserName(userName);
        if(count == 0){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该用户！");
        }
        ArticleType articleType = articleTypeMapper.findById(typeId);
        if(articleType == null){
            throw new ResultException(ResultCode.DATA_EMPTY_ERROR,"没有该文章分类！");
        }
        User user = userMapper.findByUserName(userName);
        article.setType(articleType);
        article.setCreateTime(new Date(System.currentTimeMillis()));
        article.setUser(user);
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
}
