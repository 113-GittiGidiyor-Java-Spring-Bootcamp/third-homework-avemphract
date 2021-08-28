package dev.patika.thirdhomework.dao;

import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.Student;
import dev.patika.thirdhomework.repository.CourseRepository;
import dev.patika.thirdhomework.repository.InstructorRepository;
import dev.patika.thirdhomework.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CourseService implements BaseService<Course> {
    private static final Logger logger = LoggerFactory.getLogger(CourseService.class);
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final StudentRepository studentRepository;

    @Override @Transactional(readOnly = true)
    public List<Course> findAll() {
        List<Course> courseList=new ArrayList<>();
        courseRepository.findAll().forEach(c -> courseList.add(c));
        return courseList;
    }

    @Override @Transactional(readOnly = true)
    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override @Transactional
    public Course save(Course object) {
        return courseRepository.save(object);
    }


    @Override @Transactional
    public Course deleteById(int id) {
        Course course=courseRepository.findById(id).get();
        deletion(course);
        courseRepository.deleteById(id);
        return course;
    }

    private void deletion(Course course) {
        if (course.getInstructor()!=null) {
            Instructor instructor = course.getInstructor();
            instructor.getCourses().remove(course);
            instructorRepository.save(instructor);
        }

        for (Student student:course.getStudents()){
            student.getCourses().remove(course);
            studentRepository.save(student);
        }
        course.setInstructor(null);
        course.getStudents().clear();
    }

    @Override @Transactional
    public Course update(Course object) {
        return courseRepository.save(object);
    }

    @Transactional
    public Course deleteByName(String name){
        Course course=courseRepository.getByName(name);

        deletion(course);

        courseRepository.deleteByName(name);
        return course;
    }

    @Transactional(readOnly = true)
    public Course getByName(String name){
        return courseRepository.getByName(name);
    }

}
