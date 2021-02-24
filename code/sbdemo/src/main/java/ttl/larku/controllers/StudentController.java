package ttl.larku.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ttl.larku.domain.Student;
import ttl.larku.service.StudentService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    //SOAP --> POST

    //REST -->


    @Autowired
    private StudentService studentService;

    //GET /students
    @GetMapping
    public List<Student> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return students;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable("id") int id) {
        Student student = studentService.getStudent(id);
        if(student != null) {
            return ResponseEntity.ok(student);
        }
        return ResponseEntity.badRequest().body("Student not found with id " + id);
    }

    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        Student newStudent = studentService.createStudent(student);

        URI newUri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newStudent.getId())
                .toUri();

        return ResponseEntity.created(newUri).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.noContent().build();
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(@RequestBody Student student) {
        try {
            studentService.updateStudent(student);
            return ResponseEntity.noContent().build();
        }catch(Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

}
