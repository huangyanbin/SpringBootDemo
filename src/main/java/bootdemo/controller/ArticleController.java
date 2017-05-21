package bootdemo.controller;

import bootdemo.entity.Article;
import bootdemo.entity.Result;
import bootdemo.service.ArticleService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 2017/5/21.
 */
@RestController
@RequestMapping(value ="/article")
public class ArticleController {

    @Autowired
    ArticleService service;

    @RequestMapping(value = "all")
    public Result getAllArticles(){
        return ResultUtils.getSuccessResult(service.getAllArticles());
    }

    @RequestMapping(value = "add")
    public Result addArticle(Article article){
        return ResultUtils.getSuccessResult(service.saveArticle(article));
    }
}
