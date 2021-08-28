package dev.patika.thirdhomework.utils;

import dev.patika.thirdhomework.model.Course;
import dev.patika.thirdhomework.model.GuestInstructor;
import dev.patika.thirdhomework.model.Instructor;
import dev.patika.thirdhomework.model.RegularInstructor;

import java.util.*;

public class RandomInstructorGenerator {
    private static final String LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String S_LETTER="ABCDEFGHIJKLMNOPQRSTUVWXYZ".toLowerCase();
    private static final String NUMBERS="0123456789";
    private static final List<String> ADDRESSES= Arrays.asList("İstanbul", "Ankara", "Bursa", "İzmir", "Antalya", "Diyarbakır", "Gaziantep", "Şanlıurfa", "Trabzon", "Adana", "Konya", "Manisa", "Muğla", "Siirt", "Ağrı", "Van", "Erzurum", "Sivas", "Elazığ", "Malatya", "Rize");


    private final Random random=new Random();

    private int getHourlySalary(){
        return 20+random.nextInt(60);
    }
    private int getRegularSalary(){
        return 1500+random.nextInt(3000);
    }
    private String getAddress(){
        return ADDRESSES.get(random.nextInt(ADDRESSES.size()));
    }

    private String getName(){
        String result="";
        result+=LETTER.charAt(random.nextInt(LETTER.length()));
        for (int i=1;i<4+random.nextInt(8);i++){
            result+=S_LETTER.charAt(random.nextInt(S_LETTER.length()));
        }
        result+=" "+LETTER.charAt(random.nextInt(LETTER.length()));
        for (int i=1;i<5+random.nextInt(10);i++){
            result+=S_LETTER.charAt(random.nextInt(S_LETTER.length()));
        }
        return result;
    }

    private long getPhoneNumber(){
        long result=0;
        for (int i=0;i<10;i++){
            result+=NUMBERS.charAt(random.nextInt(NUMBERS.length()))*Math.pow(10,i-1);
        }
        result+=9*Math.pow(10,11);
        return result;
    }

    public Instructor generateInstructor(){
        Instructor instructor;
        if (random.nextBoolean()){
            instructor=new GuestInstructor();
            ((GuestInstructor)instructor).setHourlySalary(getHourlySalary());
        }
        else {
            instructor=new RegularInstructor();
            ((RegularInstructor)instructor).setConstantSalary(getRegularSalary());
        }
        instructor.setAddress(getAddress());
        instructor.setName(getName());
        instructor.setPhoneNumber(getPhoneNumber());
        return instructor;
    }

    public void setCourses(List<Instructor> instructors, List<Course> courses){
        int limit=courses.size()/instructors.size()+1;
        Map<Course,Integer> map=new HashMap<>();
        for (Course course:courses){
            map.put(course,0);
        }
        for (Instructor instructor:instructors){
            for (int i=0;i<limit;i++){
                Course r= (Course) map.keySet().toArray()[random.nextInt(map.size())];
                map.put(r,map.get(r)+1);
                instructor.getCourses().add(r);
                r.setInstructor(instructor);
                if (map.get(r)>limit)
                    map.remove(r);
            }
        }
    }

}
