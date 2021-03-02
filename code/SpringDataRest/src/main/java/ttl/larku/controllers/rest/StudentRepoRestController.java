//package ttl.larku.controllers.rest;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//import ttl.larku.dao.repository.StudentRepo;
//import ttl.larku.domain.RestResult;
//import ttl.larku.domain.StudentPhoneSummary;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/adminrest/student")
//public class StudentRepoRestController {
//
//    @Autowired
//    private StudentRepo sRepo;
//
//    @GetMapping("phoneSummary")
//    public ResponseEntity<?> getPhoneSummaries() {
//        List<StudentPhoneSummary> students = sRepo.findAllStudentPhoneSummariesBy();
//        return ResponseEntity.ok(new RestResult().entity(students));
//    }
//}
