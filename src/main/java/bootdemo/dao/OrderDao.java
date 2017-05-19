package bootdemo.dao;

import bootdemo.entity.Order;
import bootdemo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 2017/5/18.
 */
public interface OrderDao extends JpaRepository<Order,Integer>{

}
