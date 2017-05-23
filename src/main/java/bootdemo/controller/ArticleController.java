package bootdemo.controller;

import bootdemo.entity.Article;
import bootdemo.entity.ArticleType;
import bootdemo.entity.Result;
import bootdemo.service.ArticleService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 2017/5/21.
 */
@RestController
@RequestMapping(value ="/article")
public class ArticleController {

    @Autowired
    ArticleService service;

    @RequestMapping(value = "list/{pageNum}/{pageSize}")
    public Result getAllArticles(@PathVariable int pageNum,@PathVariable int pageSize){
        return ResultUtils.getSuccessResult(service.getAllArticles(pageNum,pageSize));
    }

    @RequestMapping(value = "add")
    public Result addArticle(Article article, @RequestParam(value = "userName")String userName,@RequestParam(value = "typeId") int  typeId) throws Exception{
        return ResultUtils.getSuccessResult(service.saveArticle(article,userName,typeId));
    }

    @RequestMapping(value = "type/list/{pageNum}/{pageSize}")
    public Result getAllArticlesType(@PathVariable int pageNum, @PathVariable int pageSize){
        return ResultUtils.getSuccessResult(service.getAllArticleType(pageNum,pageSize));
    }

    @RequestMapping(value = "type/add")
    public Result addArticleType(ArticleType articleType) throws Exception{
        return ResultUtils.getSuccessResult(service.saveArticleType(articleType));
    }
}
