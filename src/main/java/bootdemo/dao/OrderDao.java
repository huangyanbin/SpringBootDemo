package bootdemo.dao;

import bootdemo.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huang on 2017/5/19.
 */
public interface OrderDao extends JpaRepository<Order,Integer> {
}
