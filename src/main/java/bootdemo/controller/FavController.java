package bootdemo.controller;

import bootdemo.entity.Result;
import bootdemo.service.FavService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
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



    @RequestMapping(value = "add")
    public Result addFav(@RequestParam(value = "uid")int uid,@RequestParam(value = "articleId")int articleID) throws Exception{
        return ResultUtils.getSuccessResult(service.saveFav(uid,articleID));
    }


    @RequestMapping(value = "del")
    public Result deleteFav(@RequestParam(value = "uid")int uid,@RequestParam(value = "articleId")int articleID) throws Exception{
        return ResultUtils.getSuccessResult(service.deleteFav(uid,articleID));
    }

}
