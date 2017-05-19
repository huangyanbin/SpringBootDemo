package bootdemo.service;

import bootdemo.dao.OrderDao;
import bootdemo.dao.StudentDao;
import bootdemo.dao.TeacherDao;
import bootdemo.entity.Order;
import bootdemo.entity.ResultCode;
import bootdemo.entity.Student;
import bootdemo.entity.Teacher;
import bootdemo.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by David on 2017/5/19.
 */

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private OrderDao orderDao;



    public Student addStudent(Student student,int tid)throws Exception{
        Teacher teacher = teacherDao.findOne(tid);
        if(teacher == null){
            throw new ResultException(ResultCode.INSERT_ERROR,"没有找到该老师");
        }
        Set<Teacher> teachers = new HashSet<>();
        teachers.add(teacher);
        student.setTeachers(teachers);
        return studentDao.save(student);
    }

    public Order addOrder(Order order, int sId)throws Exception{
        Student student = studentDao.findOne(sId);
        if(student == null){
            throw new ResultException(ResultCode.INSERT_ERROR,"没有找到该学生");
        }
        order.setStudent(student);
        return orderDao.save(order);
    }

    public Student findStudent(int id)throws Exception{
        return studentDao.findOne(id);
    }

    public Teacher findTeacher(int id)throws Exception{
        return teacherDao.findOne(id);
    }

    public Order findOrder(int id)throws Exception{
        return orderDao.findOne(id);
    }

    public Teacher addTeacher(Teacher teacher)throws Exception{
        return teacherDao.save(teacher);
    }
}
