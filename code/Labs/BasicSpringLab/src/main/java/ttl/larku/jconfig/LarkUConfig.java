package ttl.larku.jconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryClassDAO;
import ttl.larku.dao.inmemory.InMemoryCourseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Course;
import ttl.larku.domain.ScheduledClass;
import ttl.larku.domain.Student;
import ttl.larku.service.ClassService;
import ttl.larku.service.CourseService;
import ttl.larku.service.RegistrationService;
import ttl.larku.service.StudentService;

@Configuration
@ComponentScan({"ttl.larku.dao", "ttl.larku.service"})
public class LarkUConfig {

    @Bean
    public BaseDAO<ScheduledClass> classDAO() {
        return new InMemoryClassDAO();
    }

    @Bean
    public BaseDAO<Course> courseDAO() {
        return new InMemoryCourseDAO();
    }

    @Bean
    public BaseDAO<Student> studentDAO() {
        return new InMemoryStudentDAO();
    }

    //TODO - Dependency Injection needed here.
    @Bean
    public CourseService courseService() {
        CourseService cs = new CourseService();
        cs.setCourseDAO(courseDAO());
//        cs.setCourseDAO(new InMemoryCourseDAO());
        return cs;
    }

    @Bean
    public ClassService classService() {
        ClassService cs = new ClassService();
        cs.setClassDAO(classDAO());
        cs.setCourseService(courseService());

        return cs;
    }

    @Bean
    public RegistrationService registrationService(StudentService studentService) {
        RegistrationService rs =
                new RegistrationService(courseService(), studentService, classService());
        return rs;
    }

}

/*
 */
