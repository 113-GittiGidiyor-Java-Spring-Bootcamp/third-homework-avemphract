package dev.patika.thirdhomework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Entity
@EqualsAndHashCode(exclude = "courses")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private LocalDate birthDate;
    private String address;
    private String gender;

    @ManyToMany(targetEntity = Course.class, mappedBy = "students", cascade = CascadeType.MERGE) @JsonIgnore
    private final Set<Course> courses=new HashSet<>();
}
