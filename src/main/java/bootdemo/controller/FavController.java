package bootdemo.controller;

import bootdemo.entity.Article;
import bootdemo.entity.ArticleType;
import bootdemo.entity.FavType;
import bootdemo.entity.Result;
import bootdemo.service.FavService;
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
@RequestMapping(value ="/fav")
public class FavController {

    @Autowired
    FavService service;

    @RequestMapping(value = "list/{pageNum}/{pageSize}")
    public Result getAllFav(@PathVariable int pageNum,@PathVariable int pageSize,@RequestParam(value = "typeId")int favTypeId)throws Exception{
        return ResultUtils.getSuccessResult(service.getFavByTypeID(pageNum,pageSize,favTypeId));
    }

    @RequestMapping(value = "add")
    public Result addFav(@RequestParam(value = "typeId")int favTypeId,@RequestParam(value = "articleId")int articleID) throws Exception{
        return ResultUtils.getSuccessResult(service.saveFav(favTypeId,articleID));
    }

    @RequestMapping(value = "type/list")
    public Result getAllFavType(@RequestParam(value = "userName")String userName)throws Exception{
        return ResultUtils.getSuccessResult(service.getFavTypeByUserName(userName));
    }

    @RequestMapping(value = "type/add")
    public Result addArticleType(@RequestParam(value = "title")String title,@RequestParam(value = "userName")String userName) throws Exception{
        return ResultUtils.getSuccessResult(service.saveFavType(userName,title));
    }
}
