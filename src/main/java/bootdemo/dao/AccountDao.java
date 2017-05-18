package bootdemo.dao;

import bootdemo.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 2017/5/18.
 */
public interface AccountDao extends JpaRepository<Account,Integer>{
}
