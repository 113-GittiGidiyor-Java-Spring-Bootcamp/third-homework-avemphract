package dev.patika.thirdhomework.repository;

import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.Student;
import javafx.util.Pair;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student,Integer> {

    @Query("DELETE FROM Student AS s WHERE s.name= ?1")
    @Modifying
    void deleteByName(String name);

    @Query("SELECT s FROM Student AS s WHERE s.name= ?1")
    Student getByName(String name);

    @Query("SELECT s.address,COUNT(s) FROM Student AS s GROUP BY s.address")
    List<?> groupByAddress();

    @Query("SELECT s.gender,COUNT(s) FROM Student AS s GROUP BY s.gender")
    List<?> groupByGender();

}
