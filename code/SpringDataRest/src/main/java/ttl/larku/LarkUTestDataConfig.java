package ttl.larku;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryClassDAO;
import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.ScheduledClass;
import ttl.larku.domain.Student;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@Profile("development")
public class LarkUTestDataConfig {

    @Bean
    public Student student1() {
        Student student = new Student();
        student.setId(1);
        student.setName("Manoj");
        student.setStatus(Student.Status.FULL_TIME);
        student.setPhoneNumber("222 333-4444");
        student.getClasses().add(class1());
        return student;
    }

    @Bean
    public Student student2() {
        Student student = new Student();
        student.setId(2);
        student.setName("Ana");
        student.setStatus(Student.Status.PART_TIME);
        student.setPhoneNumber("222 333-7900");
        return student;
    }

    @Bean
    public Student student3() {
        Student student = new Student();
        student.setId(3);
        student.setName("Roberta");
        student.setStatus(Student.Status.HIBERNATING);
        student.setPhoneNumber("383 343-5879");
        return student;
    }

    @Bean
    public Student student4() {
        Student student = new Student();
        student.setId(4);
        student.setName("Mordhu");
        student.setStatus(Student.Status.PART_TIME);
        student.setPhoneNumber("223 598 8279");
        return student;
    }

    @Bean
    Course course1() {
        Course course = new Course();
        course.setId(1);
        course.setTitle("Intro To BasketWeaving");
        course.setCode("BKTW-101");
        course.setCredits(3);

        return course;
    }

    @Bean
    Course course2() {
        Course course = new Course();
        course.setId(2);
        course.setTitle("Yet More Botany");
        course.setCode("BOT-202");
        course.setCredits(2);

        return course;
    }

    @Bean
    Course course3() {
        Course course = new Course();
        course.setId(3);
        course.setTitle("Intro To Math");
        course.setCode("MATH-101");
        course.setCredits(4);

        return course;
    }

    @Bean
    ScheduledClass class1() {
        ScheduledClass sc = new ScheduledClass();
        sc.setId(1);
        sc.setStartDate("10/10/2012");
        sc.setEndDate("2/20/2013");
        sc.setCourse(course1());

        return sc;
    }

    @Bean
    ScheduledClass class2() {
        ScheduledClass sc = new ScheduledClass();
        sc.setId(2);
        sc.setStartDate("10/10/2012");
        sc.setEndDate("8/10/2013");
        sc.setCourse(course2());

        return sc;
    }

    @Bean
    ScheduledClass class3() {
        ScheduledClass sc = new ScheduledClass();
        sc.setId(3);
        sc.setStartDate("10/10/2012");
        sc.setEndDate("10/10/2013");
        sc.setCourse(course3());

        return sc;
    }

    @Bean
    public BaseDAO<Student> studentDAOWithInitData() {
        InMemoryStudentDAO dao = new InMemoryStudentDAO();

        dao.create(student1());
        dao.create(student2());
        dao.create(student3());
        dao.create(student4());

        BaseDAO<ScheduledClass> classDAO = classDAOWithInitData();
        Student s = dao.get(student1().getId());
        s.getClasses().add(classDAO.get(1));
        s.getClasses().add(classDAO.get(2));

        return dao;

    }

    public Map<Integer, Student> initStudents() {
        Map<Integer, Student> students = new HashMap<>();
        students.put(student1().getId(), student1());
        students.put(student2().getId(), student2());
        students.put(student3().getId(), student3());
        students.put(student4().getId(), student4());

        return students;
    }

    @Bean
    public BaseDAO<Course> courseDAOWithInitData() {
        InMemoryCourseDAO dao = new InMemoryCourseDAO();
        dao.create(course1());
        dao.create(course2());
        dao.create(course3());

        return dao;

    }

    public Map<Integer, Course> initCourses() {
        Map<Integer, Course> courses = new HashMap<>();

        courses.put(course1().getId(), course1());
        courses.put(course2().getId(), course2());
        courses.put(course3().getId(), course3());
        return courses;
    }

    @Bean
    public BaseDAO<ScheduledClass> classDAOWithInitData() {
        InMemoryClassDAO dao = new InMemoryClassDAO();
        dao.create(class1());
        dao.create(class2());
        dao.create(class3());

        return dao;
    }

    public Map<Integer, ScheduledClass> initClasses() {
        Map<Integer, ScheduledClass> classes = new HashMap<>();

        classes.put(class1().getId(), class1());
        classes.put(class2().getId(), class2());
        classes.put(class3().getId(), class3());
        return classes;
    }


    private Date convertToDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(year, month, day);

        return cal.getTime();
    }

}
