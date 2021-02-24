package ttl.larku.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    //GET /students
    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        return student;
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);
        return newStudent;
    }

    @DeleteMapping("/{id}")
    public boolean deleteStudent(@PathVariable("id") int id) {
        try {
            studentService.deleteStudent(id);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

    @PutMapping("/{id}")
    public boolean updateStudent(@RequestBody Student student) {
        try {
            studentService.updateStudent(student);
            return true;
        }catch(Exception e) {
            return false;
        }
    }

}
