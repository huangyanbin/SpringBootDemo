package bootdemo.controller;

import bootdemo.entity.Result;
import bootdemo.service.CollectionService;
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
@RequestMapping(value ="/collection")
public class CollectionController {

    @Autowired
    CollectionService service;

    @RequestMapping(value = "list/{pageNum}/{pageSize}")
    public Result getAllCollection(@PathVariable int pageNum,@PathVariable int pageSize,@RequestParam(value = "typeId")int collectionTypeId)throws Exception{
        return ResultUtils.getSuccessResult(service.getCollectionByTypeID(pageNum,pageSize,collectionTypeId));
    }

    @RequestMapping(value = "add")
    public Result addCollection(@RequestParam(value = "typeId")int collectionTypeId,@RequestParam(value = "articleId")int articleID) throws Exception{
        return ResultUtils.getSuccessResult(service.saveCollection(collectionTypeId,articleID));
    }

    @RequestMapping(value = "type/list")
    public Result getAllCollectionType(@RequestParam(value = "uid")int uid)throws Exception{
        return ResultUtils.getSuccessResult(service.getCollectionTypeByUserId(uid));
    }

    @RequestMapping(value = "type/add")
    public Result addArticleType(@RequestParam(value = "title")String title,@RequestParam(value = "uid")int uid) throws Exception{
        return ResultUtils.getSuccessResult(service.saveCollectionType(uid,title));
    }
}
