package bootdemo.controller;

import bootdemo.entity.Article;
import bootdemo.service.ArticleService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * Created by huang on 2017/7/28.
 */
@Controller
public class FMController {


    @Autowired
    ArticleService service;
    @RequestMapping("/login")
    public String login(Map<String,Object> map){
        return "login";
    }

    @RequestMapping("/artList")
    public String getArticleList(Map<String,Object> map){
        return "art_list";
    }


    @RequestMapping("/artAdd")
    public String addArticle(Map<String,Object> map){
        return "art_add";
    }

    @RequestMapping("/artDetail/{pageNum}")
    public String getArticleDetail(Map<String,Object> map, @PathVariable int pageNum){
         List<Article> articles =  service.getAllArticles(pageNum,1,1);
         map.put("article",articles.get(0));
         map.put("pageNum",pageNum);
        return "art_detail";
    }
}
