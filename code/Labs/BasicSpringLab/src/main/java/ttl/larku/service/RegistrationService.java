package ttl.larku.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ttl.larku.domain.ScheduledClass;
import ttl.larku.domain.Student;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

//TODO - Need to make this into a bean
//@Service
public class RegistrationService {

    //TODO - something required here
    private final CourseService courseService;
    private final StudentService studentService;
    private final ClassService classService;

//    @Autowired
    public RegistrationService(CourseService courseService,
                               StudentService studentService,
                               ClassService classService) {
        this.courseService = courseService;
        this.studentService = studentService;
        this.classService = classService;


        List<Student> students = studentService.getAllStudents();
        //Do something with them
    }

    public RegistrationService(CourseService courseService) {
        this.courseService = courseService;
        studentService = new StudentService();
        classService = new ClassService();
    }

    public RegistrationService() {
        courseService = new CourseService();
        studentService = new StudentService();
        classService = new ClassService();

    }

//    @PostConstruct
//    public void init() {
//        List<Student> students = studentService.getAllStudents();
//        System.out.println(students);
//
//    }


    public ScheduledClass addNewClassToSchedule(String courseCode, String startDate, String endDate) {
        ScheduledClass sClass = classService.createScheduledClass(courseCode, startDate, endDate);
        return sClass;
    }

    public void registerStudentForClass(int studentId, String courseCode, String startDate) {
        Student student = studentService.getStudent(studentId);
        List<ScheduledClass> classes = classService.getScheduledClassesByCourseCode(courseCode);
        for (ScheduledClass sc : classes) {
            if (sc.getStartDate().equals(startDate)) {
                sc.addStudent(student);
                student.addClass(sc);
                break;
            }
        }
    }

    public void dropStudentFromClass(int studentId, String courseCode, String startDate) {
        Student student = studentService.getStudent(studentId);
        List<ScheduledClass> classes = classService.getScheduledClassesByCourseCode(courseCode);
        for (ScheduledClass sc : classes) {
            if (sc.getStartDate().equals(startDate)) {
                sc.removeStudent(student);
                student.dropClass(sc);
                break;
            }
        }
    }

    public List<Student> getStudentsForClass(String courseCode, String startDate) {
        List<ScheduledClass> classes = classService.getScheduledClassesByCourseCode(courseCode);
        for (ScheduledClass sc : classes) {
            if (sc.getStartDate().equals(startDate)) {
                return sc.getStudents();
            }
        }
        return new ArrayList<Student>();
    }

    public List<ScheduledClass> getScheduledClasses() {
        return classService.getAllScheduledClasses();
    }


    public CourseService getCourseService() {
        return courseService;
    }


//    public void setCourseService(CourseService courseService) {
//        this.courseService = courseService;
//    }


    public StudentService getStudentService() {
        return studentService;
    }


//    public void setStudentService(StudentService studentService) {
//        this.studentService = studentService;
//    }


    public ClassService getClassService() {
        return classService;
    }


//    public void setClassService(ClassService classService) {
//        this.classService = classService;
//    }
}
