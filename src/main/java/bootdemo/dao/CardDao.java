package bootdemo.dao;

import bootdemo.entity.CardID;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by David on 2017/5/18.
 */
public interface CardDao extends JpaRepository<CardID,Integer>{


}
