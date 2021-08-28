package dev.patika.thirdhomework.dao;

import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.GuestInstructor;
import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.RegularInstructor;
import dev.patika.thirdhomework.repository.CourseRepository;
import dev.patika.thirdhomework.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorService implements BaseService<Instructor> {
    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;

    @Override @Transactional(readOnly = true)
    public List<Instructor> findAll() {
        List<Instructor> instructors=new ArrayList<>();
        instructorRepository.findAll().forEach(i -> instructors.add(i));
        return instructors;
    }

    @Override @Transactional(readOnly = true)
    public Instructor findById(int id) {
        return instructorRepository.findById(id).get();
    }

    @Override @Transactional()
    public Instructor save(Instructor object) {
        return instructorRepository.save(object);
    }

    @Override @Transactional()
    public Instructor deleteById(int id) {
        Instructor instructor=instructorRepository.findById(id).get();
        deletion(instructor);
        instructorRepository.deleteById(id);
        return instructor;
    }

    private void deletion(Instructor instructor){
        for (Course course:instructor.getCourses()){
            course.setInstructor(null);
            courseRepository.save(course);
        }
        instructor.getCourses().clear();

    }

    @Override @Transactional()
    public Instructor update(Instructor object) {
        return instructorRepository.save(object);
    }

    @Transactional
    public Instructor deleteByName(String name){
        Instructor instructor=instructorRepository.getByName(name);
        deletion(instructor);

        instructorRepository.deleteByName(name);
        return instructor;
    }

    @Transactional(readOnly = true)
    public Instructor getByName(String name){
        return instructorRepository.getByName(name);
    }

    @Transactional(readOnly = true)
    public List<RegularInstructor> getThreeHighestSalaryRegularInstructor(){
        return instructorRepository.getThreeHighestSalaryRegularInstructor().subList(0,3);
    }

    @Transactional(readOnly = true)
    public List<GuestInstructor> getThreeHighestSalaryGuestInstructor(){
        return instructorRepository.getThreeHighestSalaryGuestInstructor().subList(0,3);
    }

}
