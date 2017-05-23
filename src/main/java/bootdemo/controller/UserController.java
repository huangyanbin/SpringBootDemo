package bootdemo.controller;

import bootdemo.entity.Result;
import bootdemo.entity.User;
import bootdemo.service.UserService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by huang on 2017/5/21.
 */
@RestController
@RequestMapping(value ="/user")
public class UserController {

    @Autowired
    UserService service;



    @RequestMapping(value = "register",method = RequestMethod.POST)
    public Result register(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) throws Exception{
        return ResultUtils.getSuccessResult(service.register(userName,password));
    }

    @RequestMapping(value = "login",method = RequestMethod.POST)
    public Result login(@RequestParam(value = "userName") String userName, @RequestParam(value = "password") String password) throws Exception{
        return ResultUtils.getSuccessResult(service.login(userName,password));
    }

    @RequestMapping(value = "update",method = RequestMethod.GET)
    public Result update(User user) throws Exception{
        return ResultUtils.getSuccessResult(service.update(user));
    }

    @RequestMapping(value = "list/{pageNum}/{pageSize}",method = RequestMethod.GET)
    public Result getUserList(@PathVariable int pageNum,@PathVariable int pageSize) throws Exception{
        return ResultUtils.getSuccessResult(service.getUserList(pageNum,pageSize));
    }
}
