package bootdemo.controller;

import bootdemo.entity.Order;
import bootdemo.entity.Result;
import bootdemo.entity.Student;
import bootdemo.entity.Teacher;
import bootdemo.service.StudentService;
import bootdemo.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * Created by David on 2017/5/18.
 */
@RestController
public class StudentController {


    @Autowired
    private StudentService studentService;


    @RequestMapping(value = {"/student/add"}, method = RequestMethod.GET)
    public Result addStudent(Student student,@RequestParam(value = "tid") int tid)throws Exception {
        return ResultUtils.getSuccessResult(studentService.addStudent(student,tid));
    }



    @RequestMapping(value = {"/teacher/add"}, method = RequestMethod.GET)
    public Result addTeacher(Teacher teacher)throws Exception {
        return ResultUtils.getSuccessResult(studentService.addTeacher(teacher));
    }

    @RequestMapping(value = {"/order/add"}, method = RequestMethod.GET)
    public Result addOrder(Order order, @RequestParam(value = "sid") int sid)throws Exception {
        return ResultUtils.getSuccessResult(studentService.addOrder(order,sid));
    }

    @RequestMapping(value = {"/card/add"}, method = RequestMethod.GET)
    public Result addCard(@RequestParam(value = "sid") int sid)throws Exception {
        return ResultUtils.getSuccessResult(studentService.addCard(sid));
    }

    @RequestMapping(value = {"/teacher/find"}, method = RequestMethod.GET)
    public Result findTeacher(@RequestParam(value = "id") int id)throws Exception {
        return ResultUtils.getSuccessResult(studentService.findTeacher(id));
    }

    @RequestMapping(value = {"/student/find"}, method = RequestMethod.GET)
    public Result findStudent(@RequestParam(value = "id") int id)throws Exception {
        return ResultUtils.getSuccessResult(studentService.findStudent(id));
    }

    @RequestMapping(value = {"/student/findByName"}, method = RequestMethod.GET)
    public Result findStudentByName(@RequestParam(value = "name") String name)throws Exception {
        return ResultUtils.getSuccessResult(studentService.findStudent(name));
    }

    @RequestMapping(value = {"/order/find"}, method = RequestMethod.GET)
    public Result findOrder(@RequestParam(value = "id") int id)throws Exception {
        return ResultUtils.getSuccessResult(studentService.findOrder(id));
    }
}


