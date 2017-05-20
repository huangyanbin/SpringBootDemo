package bootdemo.controller;

import bootdemo.entity.Student;
import bootdemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by huang on 2017/5/20.
 */
@Controller
public class MakerController {

    @Autowired
    private RedisService service;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public ModelAndView hello() throws Exception{
        ModelAndView model = new ModelAndView("hehe");
        service.set("name","huangshiping");
        ModelMap map = model.getModelMap();
        map.put("time", new Date());
        Student student = new Student();
        student.setName(service.get("name"));
        student.setAge(27);
        map.put("student",student);
        map.put("message","lishiping");
        return model;
    }
}
