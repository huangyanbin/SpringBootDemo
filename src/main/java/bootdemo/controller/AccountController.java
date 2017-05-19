package bootdemo.controller;

import bootdemo.dao.AccountDao;
import bootdemo.entity.Account;
import bootdemo.entity.Result;
import bootdemo.exception.ResultException;
import bootdemo.properties.GirlProperties;
import bootdemo.service.AccountService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David on 2017/5/18.
 */
@RestController
@EnableConfigurationProperties({GirlProperties.class})
@RequestMapping("/account")
public class AccountController {


    @Autowired
    @Qualifier(value = "girl")
    private GirlProperties property;


    @Autowired
    AccountService accountService;

    @RequestMapping(value = {"/hello"},method = RequestMethod.GET)
    public String say(){
        return property.getAge()+"";
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result postAccount(Account account) throws Exception{
        return ResultUtils.getSuccessResult(accountService.saveAccount(account));
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result updateAccount(@PathVariable(value = "id") int id, @RequestParam(value = "name") String name){
        Account account = new Account();
        account.setId(id);
        account.setName(name);
        return ResultUtils.getSuccessResult(accountService.updateAccount(account));
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Result getAllAccount(){
        return ResultUtils.getSuccessResult(accountService.getAllAccount());
    }
}
