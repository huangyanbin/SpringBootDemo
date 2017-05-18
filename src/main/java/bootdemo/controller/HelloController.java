package bootdemo.controller;

import bootdemo.dao.AccountDao;
import bootdemo.entity.Account;
import bootdemo.entity.Result;
import bootdemo.exception.ResultException;
import bootdemo.properties.GirlProperties;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by David on 2017/5/18.
 */
@RestController
@EnableConfigurationProperties({GirlProperties.class})
@RequestMapping("/account")
public class HelloController {


    @Autowired
    @Qualifier(value = "girl")
    private GirlProperties property;

    @Autowired
    AccountDao accountDao;

    @RequestMapping(value = {"/hello"},method = RequestMethod.GET)
    public String say(){
        return property.getAge()+"";
    }

    @RequestMapping(value = "",method = RequestMethod.GET)
    public Result postAccount(Account account) throws Exception{
        if(account.getMoney() >1000){
            throw new ResultException(2,"金额太大");
        }
        account.setMoney(account.getMoney());
        account.setName(account.getName());
        Account account1 = accountDao.save(account);
        return ResultUtils.getSuccessResult(account1);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public Result updateAccount(@PathVariable(value = "id") int id, @RequestParam(value = "name") String name){
        Account account = new Account();
        account.setName(name);
        Account account1 = accountDao.saveAndFlush(account);
        return ResultUtils.getSuccessResult(account1);
    }

    @RequestMapping(value = "list",method = RequestMethod.GET)
    public Result getAllAccount(){
        List<Account> accountList = accountDao.findAll();
        return ResultUtils.getSuccessResult(accountList);
    }
}
