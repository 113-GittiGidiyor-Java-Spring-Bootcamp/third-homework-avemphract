package dev.patika.thirdhomework;

import dev.patika.thirdhomework.controller.CourseController;
import dev.patika.thirdhomework.controller.InstructorController;
import dev.patika.thirdhomework.controller.StudentController;
import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.Student;
import dev.patika.thirdhomework.utils.RandomCourseGenerator;
import dev.patika.thirdhomework.utils.RandomInstructorGenerator;
import dev.patika.thirdhomework.utils.RandomStudentGenerator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableSwagger2
public class ThirdHomeworkAvemphractApplication {

    @Bean
    public RestTemplate getRestTemplateBuilderr(RestTemplateBuilder restTemplateBuilder){
        return restTemplateBuilder.build();
    }

    public static void main(String[] args) {
        SpringApplication.run(ThirdHomeworkAvemphractApplication.class, args);
    }

}
