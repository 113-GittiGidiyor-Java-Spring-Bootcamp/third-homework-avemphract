package dev.patika.thirdhomework.controller;

import dev.patika.thirdhomework.dao.InstructorService;
import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.RegularInstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class InstructorController implements BaseController<Instructor> {
    InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/instructor")
    public ResponseEntity<List<Instructor>> findAll(){
        return new ResponseEntity<>(instructorService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> findById(@PathVariable int id){
        return new ResponseEntity<>(instructorService.findById(id),HttpStatus.OK);
    }

    @PutMapping("/instructor")
    public ResponseEntity<Instructor> update(Instructor instructor) {
        return new ResponseEntity<>(instructorService.update(instructor),HttpStatus.OK);
    }

    @PostMapping("/instructor")
    public ResponseEntity<Instructor> save(@RequestBody Instructor instructor){
        return new ResponseEntity<>(instructorService.save(instructor),HttpStatus.OK);
    }

    @DeleteMapping("/instructor/{id}")
    public ResponseEntity<Instructor> deleteById(@PathVariable int id){
        return new ResponseEntity<>(instructorService.deleteById(id),HttpStatus.OK);
    }

    @DeleteMapping("/instructor/delete")
    public ResponseEntity<Instructor> deleteByName(@RequestParam String name){
        return ResponseEntity.ok(instructorService.deleteByName(name));
    }

    @GetMapping("/instructor/get")
    public ResponseEntity<Instructor> getByName(@RequestParam String name){
        return ResponseEntity.ok(instructorService.getByName(name));
    }

    @GetMapping("/instructor/highest_salary")
    public ResponseEntity<List<Instructor>> getThreeHighestSalaryRegularInstructor(){
        List<Instructor> list=new ArrayList<>();
        list.addAll(instructorService.getThreeHighestSalaryRegularInstructor());
        list.addAll(instructorService.getThreeHighestSalaryGuestInstructor());
        return ResponseEntity.ok(list);
    }
}
