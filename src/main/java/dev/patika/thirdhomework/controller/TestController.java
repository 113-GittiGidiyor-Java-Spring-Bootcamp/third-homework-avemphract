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

    @Autowired
    public TestController(StudentService studentService, InstructorService instructorService, CourseService courseService) {
        this.studentService = studentService;
        this.instructorService = instructorService;
        this.courseService = courseService;
    }

    @GetMapping("/test")
    public void test(){
        RandomCourseGenerator courseGenerator=new RandomCourseGenerator();
        RandomInstructorGenerator instructorGenerator=new RandomInstructorGenerator();
        RandomStudentGenerator studentGenerator=new RandomStudentGenerator(10,35);


        List<Student> students=new ArrayList<>();
        for (int i=0;i<1000;i++){
            students.add(studentGenerator.generateRandomStudent());
        }

        List<Instructor> instructors=new ArrayList<>();
        for (int i=0;i<50;i++){
            instructors.add(instructorGenerator.generateInstructor());
        }

        List<Course> courses=new ArrayList<>();
        for (int i=0;i<120;i++){
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
    }
}
