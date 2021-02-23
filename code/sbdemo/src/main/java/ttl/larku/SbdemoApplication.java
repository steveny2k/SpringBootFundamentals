package ttl.larku;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

//@Configuration
//@ComponentScan
@SpringBootApplication //(scanBasePackages = {"ttl.larku", "ttl.sbdemo"})
public class SbdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SbdemoApplication.class, args);
    }

}

@Component
class MyRunner implements CommandLineRunner
{
    @Autowired
    private StudentService studentService;

    @Override
    public void run(String... args) throws Exception {
        List<Student> students = studentService.getAllStudents();
        System.out.println("students");
        students.forEach(System.out::println);
    }
}
