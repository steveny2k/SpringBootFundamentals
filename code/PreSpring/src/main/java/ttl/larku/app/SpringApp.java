package ttl.larku.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.MyFactory;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;
import ttl.larku.jconfig.LarkUConfig;
import ttl.larku.service.CourseService;
import ttl.larku.service.StudentService;

import java.util.List;

public class SpringApp {

    int value;

    static Logger logger = LoggerFactory.getLogger(SpringApp.class);

    public static void main(String[] args) {
        //ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //ApplicationContext context = new AnnotationConfigApplicationContext(LarkUConfig.class);

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("ttl.larku.dao", "ttl.larku.service");
        //context.register(LarkUConfig.class);
        context.refresh();

//        BaseDAO<Student> studentDAO = context.getBean("inmemoryStudentDAO", BaseDAO.class);
//
//        System.out.println(studentDAO);

//        StudentService studentService = context.getBean("studentService", StudentService.class);
//        init(studentService);
//
//        StudentService studentService2 = context.getBean("studentService", StudentService.class);
////
//        List<Student> students = studentService.getAllStudents();
//        System.out.println("students: " + students);


        CourseService cs = context.getBean("courseService", CourseService.class);
        init(cs);

        List<Course> courses = cs.getAllCourses();
        System.out.println(courses);


    }


    public static void init(StudentService ss) {
        ss.createStudent("Manoj", "282 939 9944", Student.Status.FULL_TIME);
        ss.createStudent("Charlene", "282 898 2145", Student.Status.FULL_TIME);
        ss.createStudent("Firoze", "228 678 8765", Student.Status.HIBERNATING);
        ss.createStudent("Joe", "3838 678 3838", Student.Status.PART_TIME);
    }

    public static void init(CourseService cs) {
        cs.createCourse("Math-101", "Intro To Math");
        cs.createCourse("Math-201", "More Math");
        cs.createCourse("Phys-101", "Baby Physics");
    }

}
