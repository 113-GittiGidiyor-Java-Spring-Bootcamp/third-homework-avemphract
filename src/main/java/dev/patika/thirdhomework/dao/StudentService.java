package dev.patika.thirdhomework.dao;

import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.Student;
import dev.patika.thirdhomework.repository.CourseRepository;
import dev.patika.thirdhomework.repository.StudentRepository;
import javafx.util.Pair;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.query.AbstractJpaQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentService implements BaseService<Student> {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override @Transactional(readOnly = true)
    public List<Student> findAll() {
        List<Student> students=new ArrayList<>();
        studentRepository.findAll().forEach(s -> students.add(s));
        return students;
    }

    @Override @Transactional(readOnly = true)
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override @Transactional
    public Student save(Student object) {
        return studentRepository.save(object);
    }

    @Override @Transactional
    public Student deleteById(int id) {
        Student student = studentRepository.findById(id).get();
        deletion(student);
        studentRepository.deleteById(id);
        return student;
    }
    private void deletion(Student student){
        for (Course course:student.getCourses()){
            course.getStudents().remove(student);
            courseRepository.save(course);
        }
        student.getCourses().clear();
    }

    @Override @Transactional
    public Student update(Student object) {
        return studentRepository.save(object);
    }

    @Transactional
    public Student deleteByName(String name){
        Student student=studentRepository.getByName(name);
        deletion(student);
        studentRepository.deleteByName(name);
        return student;
    }

    @Transactional(readOnly = true)
    public Student getByName(String name){
        return studentRepository.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<?> groupByAddress(){
        return studentRepository.groupByAddress();
    }

    @Transactional(readOnly = true)
    public List<?> groupByGender(){
        return studentRepository.groupByGender();
    }
}
