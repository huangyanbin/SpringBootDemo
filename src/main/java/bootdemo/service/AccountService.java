package bootdemo.service;

import bootdemo.dao.AccountDao;
import bootdemo.entity.Account;
import bootdemo.entity.ResultCode;
import bootdemo.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by David on 2017/5/19.
 */
@Service
public class AccountService {

    @Autowired
    AccountDao accountDao;

    public Account saveAccount(Account account) throws Exception{

        if(account.getMoney() >1000){
            throw new ResultException(ResultCode.INSERT_ERROR,"金额太大");
        }
        account.setMoney(account.getMoney());
        account.setName(account.getName());
        return accountDao.save(account);
    }

    public Account updateAccount(Account account){
        return accountDao.saveAndFlush(account);
    }

    public List<Account> getAllAccount(){
       return accountDao.findAll();
    }
}
