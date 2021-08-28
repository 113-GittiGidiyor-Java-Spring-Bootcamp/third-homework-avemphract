package dev.patika.thirdhomework.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@EqualsAndHashCode(exclude = "instructor")
@Data @AllArgsConstructor @NoArgsConstructor
public class Course {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String courseName;
    private String courseCode;
    private double credit;
    @ManyToOne @JsonIgnore
    private Instructor instructor;

    @ManyToMany(targetEntity = Student.class, cascade = CascadeType.MERGE) @JsonIgnore
    private final Set<Student> students=new HashSet<>();

}
