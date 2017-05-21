package bootdemo.service;

import bootdemo.dao.CardDao;
import bootdemo.dao.OrderDao;
import bootdemo.dao.StudentDao;
import bootdemo.dao.TeacherDao;
import bootdemo.entity.*;
import bootdemo.exception.ResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Autowired
    private CardDao cardDao;


    @CacheEvict(value = {"findStudent","findStudentByName"},allEntries = true)
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
        Student student;
        try {
            student = studentDao.findOne(sId);
        }catch (Exception e){
            throw new ResultException(ResultCode.INSERT_ERROR,"没有找到该学生");
        }
        order.setStudent(student);
        return orderDao.save(order);
    }

    @Cacheable(value = "findStudent")
    public Student findStudent(int id)throws Exception{
        return studentDao.findOne(id);
    }
    @Cacheable(value = "findStudentByName")
    public Student findStudent(String name)throws Exception{
        return studentDao.findByName(name);
    }

    @Cacheable(value = "findTeacher")
    public Teacher findTeacher(int id)throws Exception{
        return teacherDao.findOne(id);
    }
    @Cacheable(value = "findOrder")
    public Order findOrder(int id)throws Exception{
        return orderDao.findOne(id);
    }
    @CacheEvict(value = "findTeacher",allEntries = true)
    public Teacher addTeacher(Teacher teacher)throws Exception{
        return teacherDao.save(teacher);
    }
    @CacheEvict(value = {"findStudent","findStudentByName"},allEntries = true)
    public CardID addCard(int sid)throws Exception{
        Student student = findStudent(sid);
        CardID cardID = new CardID();
        cardID.setStudent(student);
        return cardDao.save(cardID);
    }
}
