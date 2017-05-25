package bootdemo.controller;

import bootdemo.entity.Result;
import bootdemo.service.CommentService;
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
@RequestMapping(value ="/comment")
public class CommentController {

    @Autowired
    CommentService service;




    @RequestMapping(value = "add")
    public Result addComment(@RequestParam(value = "uid")int uid,@RequestParam(value = "content")String  content,@RequestParam(value = "articleId")int articleID) throws Exception{
        return ResultUtils.getSuccessResult(service.saveComment(uid,content,articleID));
    }


    @RequestMapping(value = "delete")
    public Result deleteComment(@RequestParam(value = "uid")int uid,@RequestParam(value = "articleId")int articleID) throws Exception{
        return ResultUtils.getSuccessResult(service.deleteComment(uid,articleID));
    }

}
