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
    public Result addFollow(@RequestParam(value = "userName") String userName,@RequestParam(value = "follow") String followName) throws Exception{
        return ResultUtils.getSuccessResult(service.follow(userName,followName));
    }

    @RequestMapping(value = "del")
    public Result cancel(@RequestParam(value = "userName") String userName, @RequestParam(value = "follow") String followName) throws Exception{
        return ResultUtils.getSuccessResult(service.cancelFollow(userName,followName));
    }

    @RequestMapping(value = "all/ing")
    public Result getFollowing(@RequestParam(value = "userName") String userName) throws Exception{
        return ResultUtils.getSuccessResult(service.getAllFollowing(userName));
    }

    @RequestMapping(value = "all/ed")
    public Result getFollowed(@RequestParam(value = "userName") String userName) throws Exception{
        return ResultUtils.getSuccessResult(service.getAllFollowed(userName));
    }
}
