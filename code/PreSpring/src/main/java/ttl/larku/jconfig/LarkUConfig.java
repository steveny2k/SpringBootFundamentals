package ttl.larku.jconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ttl.larku.dao.BaseDAO;
import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

@Configuration
@ComponentScan({"ttl.larku.dao", "ttl.larku.service"})
public class LarkUConfig {
//    <bean id="studentDAO" class="ttl.larku.dao.inmemory.InMemoryStudentDAO"/>

    @Bean
    public BaseDAO<Student> studentDAO() {
        return new InMemoryStudentDAO();
    }

//    <bean id="studentService" class="ttl.larku.service.StudentService" >
//        <property name="studentDAO" ref="inmemoryStudentDAO"/>
//    </bean>

    @Bean
    public StudentService studentService() {
        StudentService ss = new StudentService();
        BaseDAO<Student> bd = studentDAO();
        ss.setStudentDAO(bd);

        return ss;
    }

}