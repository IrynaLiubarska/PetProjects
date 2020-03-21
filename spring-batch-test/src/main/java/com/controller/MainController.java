package com.controller;

import com.model.Course;
import com.persistent.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MainController {

    @Autowired
    private CourseService courseService;
    
    @RequestMapping("/contact")
    public String getContact(){
        return "Our contact address is: Selma-Lagerlöf-Strasse";
    }

    @RequestMapping("/all-about-us")
    public String getAllAboutUs(){
        return "We are the small company in the middle of Europe";
    }

    @RequestMapping("/all-courses")
    public List<Course> getAllCourses(){
        return courseService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addCourse")
    public void addCourse(@RequestBody Course course){
        courseService.addCourse(course);
    }
}
