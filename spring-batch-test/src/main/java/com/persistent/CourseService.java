package com.persistent;

import com.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAll() {
        List<Course> courses = new ArrayList<Course>();
        courseRepository.findAll().forEach(courses::add);
        return courses;
    }
    
    public Optional<Course> getCourseById(String id){
        return courseRepository.findById(id);
    }
    
    public void addCourse(Course course){
        courseRepository.save(course);
    }
    
    public void updateCourse(Course course){
        courseRepository.save(course);
    }
    
    public void deleteCourseById(String id){
        courseRepository.deleteById(id);
    }
    
    public void deleteCourse(Course course){
        courseRepository.delete(course);
    }
    
    public void deleteAll(){
        courseRepository.deleteAll();
    }
}
