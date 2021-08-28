package dev.patika.thirdhomework.controller;

import dev.patika.thirdhomework.dao.CourseService;
import dev.patika.thirdhomework.model.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController implements BaseController<Course> {
    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);
    CourseService courseService;

    @Value("${developer.name}")
    private String developerName;
    @Value("${developer.email}")
    private String developerEmail;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Override
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> findAll(){
        return new ResponseEntity<>(courseService.findAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> findById(@PathVariable int id){
        return new ResponseEntity<>(courseService.findById(id),HttpStatus.OK);
    }

    @Override
    @PostMapping("/courses")
    public ResponseEntity<Course> save(@RequestBody Course course){
        return new ResponseEntity<>(courseService.save(course),HttpStatus.OK);
    }

    @Override
    @PutMapping("/courses")
    public ResponseEntity<Course> update(Course course) {
        return new ResponseEntity<>(courseService.update(course),HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/courses/{id}")
    public ResponseEntity<Course> deleteById(@PathVariable int id){
        Course course = courseService.findById(id);
        courseService.deleteById(id);
        return new ResponseEntity<Course>(course,HttpStatus.OK);
    }

    @DeleteMapping("/courses/delete")
    public ResponseEntity<Course> deleteByName(@RequestParam String name){
        return ResponseEntity.ok(courseService.deleteByName(name));
    }

    @GetMapping("/courses/get")
    public ResponseEntity<Course> getByName(@RequestParam String name){
        return ResponseEntity.ok(courseService.getByName(name));
    }

}
