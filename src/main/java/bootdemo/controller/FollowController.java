package bootdemo.controller;

import bootdemo.entity.Result;
import bootdemo.entity.User;
import bootdemo.service.FollowService;
import bootdemo.service.UserService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by huang on 2017/5/21.
 */
@RestController
@RequestMapping(value ="/follow")
public class FollowController {

    @Autowired
    FollowService service;


    @RequestMapping(value = "add")
    public Result addFollow(@RequestParam(value = "uid") int uid,@RequestParam(value = "follow") int follow) throws Exception{
        return ResultUtils.getSuccessResult(service.follow(uid,follow));
    }

    @RequestMapping(value = "del")
    public Result cancel(@RequestParam(value = "uid") int uid, @RequestParam(value = "follow") int follow) throws Exception{
        return ResultUtils.getSuccessResult(service.cancelFollow(uid,follow));
    }

    @RequestMapping(value = "list/ing")
    public Result getFollowing(@RequestParam(value = "uid") int uid) throws Exception{
        return ResultUtils.getSuccessResult(service.getAllFollowing(uid));
    }

    @RequestMapping(value = "list/ed")
    public Result getFollowed(@RequestParam(value = "uid") int uid) throws Exception{
        return ResultUtils.getSuccessResult(service.getAllFollowed(uid));
    }
}
