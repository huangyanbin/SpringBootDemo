package bootdemo.dao;

import bootdemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * Created by David on 2017/5/18.
 */
public interface StudentDao extends JpaRepository<Student,Integer>{

}
