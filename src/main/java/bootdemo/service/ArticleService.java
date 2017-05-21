package bootdemo.service;

import bootdemo.dao.ArticleMapper;
import bootdemo.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by huang on 2017/5/21.
 */
@Service
public class ArticleService {

    @Autowired
    private ArticleMapper mapper;

    public List<Article> getAllArticles(){
       return mapper.queryAll();
    }

    public int saveArticle(Article article){
        mapper.save(article);
        return 1;
    }
}
