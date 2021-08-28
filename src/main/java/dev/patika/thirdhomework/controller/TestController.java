package dev.patika.thirdhomework.controller;


import dev.patika.thirdhomework.dao.CourseService;
import dev.patika.thirdhomework.dao.InstructorService;
import dev.patika.thirdhomework.dao.StudentService;
import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.Student;
import dev.patika.thirdhomework.utils.RandomCourseGenerator;
import dev.patika.thirdhomework.utils.RandomInstructorGenerator;
import dev.patika.thirdhomework.utils.RandomStudentGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class TestController {
    StudentService studentService;
    InstructorService instructorService;
    CourseService courseService;

    private int studentCount=1000;
    private int instructorCount=50;
    private int courseCount=120;

    @Autowired
    public TestController(StudentService studentService, InstructorService instructorService, CourseService courseService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        RandomCourseGenerator courseGenerator=new RandomCourseGenerator();
        RandomInstructorGenerator instructorGenerator=new RandomInstructorGenerator();
        RandomStudentGenerator studentGenerator=new RandomStudentGenerator(10,35);


        List<Student> students=new ArrayList<>();
        for (int i=0;i<studentCount;i++){
            students.add(studentGenerator.generateRandomStudent());
        }

        List<Instructor> instructors=new ArrayList<>();
        for (int i=0;i<instructorCount;i++){
            instructors.add(instructorGenerator.generateInstructor());
        }

        List<Course> courses=new ArrayList<>();
        for (int i=0;i<courseCount;i++){
            courses.add(courseGenerator.generateCourse());
        }

        for (Student student:students)
            studentService.save(student);
        for (Instructor instructor:instructors)
            instructorService.save(instructor);
        for (Course course:courses)
            courseService.save(course);

        studentGenerator.setCourses(students,courses);
        instructorGenerator.setCourses(instructors,courses);

        for (Student student:students)
            studentService.update(student);
        for (Instructor instructor:instructors)
            instructorService.update(instructor);
        for (Course course:courses)
            courseService.update(course);

        return ResponseEntity.ok("Random test entities added to data base and relations are connected\n" +
                "student count: "+studentService.findAll().size()+"\n" +
                "instructor count: "+instructorService.findAll().size()+"\n" +
                "course count: "+courseService.findAll().size());
    }
}
