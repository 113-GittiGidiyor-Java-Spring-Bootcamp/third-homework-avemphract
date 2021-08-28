package dev.patika.thirdhomework.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

/*@JsonTypeInfo(
        use = JsonTypeInfo.Id.DEDUCTION,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        visible = false)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RegularInstructor.class, name = "constantSalary"),//constantSalary Regular
        @JsonSubTypes.Type(value = GuestInstructor.class, name = "hourlySalary")//hourlySalary Guest
})*/
@JsonTypeInfo(use= JsonTypeInfo.Id.DEDUCTION, defaultImpl = Course.class)
@JsonSubTypes({
        @JsonSubTypes.Type(GuestInstructor.class),
        @JsonSubTypes.Type(RegularInstructor.class)
})
@Entity @Inheritance(strategy = InheritanceType.JOINED)
@EqualsAndHashCode(exclude = "courses")
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;
    private String address;
    private long phoneNumber;
    @OneToMany(mappedBy = "instructor") @JsonManagedReference
    private final Set<Course> courses=new HashSet<>();
}
