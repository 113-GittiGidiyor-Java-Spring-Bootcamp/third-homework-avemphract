package dev.patika.thirdhomework.controller;

import dev.patika.thirdhomework.dao.CourseService;
import dev.patika.thirdhomework.dao.StudentService;
import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.Student;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController implements BaseController<Student> {
    StudentService studentService;
    CourseService courseService;

    @Autowired
    public StudentController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService=courseService;
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> findAll(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> findById(@PathVariable int id){
        return new ResponseEntity<>(studentService.findById(id),HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<Student> save(@RequestBody Student student){
        return new ResponseEntity<>(studentService.save(student),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Student> update(Student student) {
        return new ResponseEntity<>(studentService.update(student),HttpStatus.OK);
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<Student> deleteById(@PathVariable int id){
        Student student=studentService.findById(id);
        for (Course course:student.getCourses()){
            course.getStudents().remove(student);
            courseService.update(course);
        }
        ;
        return new ResponseEntity<>(studentService.deleteById(id),HttpStatus.OK);
    }

    @DeleteMapping("/students/delete")
    public ResponseEntity<Student> deleteByName(@RequestParam String name){
        return ResponseEntity.ok(studentService.deleteByName(name));
    }

    @GetMapping("/students/get")
    public ResponseEntity<Student> getByName(@RequestParam String name){
        return ResponseEntity.ok(studentService.getByName(name));
    }

    @GetMapping("/students/group_by_address")
    public ResponseEntity<?> groupByAddress(){
        return ResponseEntity.ok(studentService.groupByAddress());
    }

    @GetMapping("/students/group_by_gender")
    public ResponseEntity<?> groupByGender(){
        return ResponseEntity.ok(studentService.groupByGender());
    }

}
