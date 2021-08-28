package dev.patika.thirdhomework.utils;

import dev.patika.thirdhomework.model.Course;

import java.util.Random;

public class RandomCourseGenerator {
    private static final String LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS="0123456789";
    private final Random random=new Random();

    private String getCourseCode(){
        String result="";
        for (int i=0;i<3;i++){
            result+=LETTER.charAt(random.nextInt(LETTER.length()));
        }
        result+="-";
        for (int i=0;i<3;i++){
            result+=NUMBERS.charAt(random.nextInt(NUMBERS.length()));
        }
        return result;
    }
    private String getCourseName(){
        String result="";
        for (int i=0;i<10+random.nextInt(10);i++){
            result+=LETTER.charAt(random.nextInt(LETTER.length()));
        }
        return result;
    }

    private double getCredit(){
        return random.nextInt(10);
    }

    public Course generateCourse(){
        Course course=new Course();
        course.setCourseCode(getCourseCode());
        course.setCourseName(getCourseName());
        course.setCredit(getCredit());
        return course;
    }


}
