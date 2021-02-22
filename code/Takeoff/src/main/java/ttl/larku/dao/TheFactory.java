package ttl.larku.dao;

import ttl.larku.dao.inmemory.InMemoryStudentDAO;
import ttl.larku.dao.jpa.JpaStudentDAO;
import ttl.larku.service.StudentService;

import java.util.ResourceBundle;

public class TheFactory {


    public static StudentDAO getStudentDAO() {
        ResourceBundle bundle = ResourceBundle.getBundle("larkUContext");
        String profile = bundle.getString("larku.profile.active");
        switch(profile) {
            case "dev" :
                return new InMemoryStudentDAO();
            case "prod":
                return new JpaStudentDAO();
            default:
                throw new RuntimeException("Unknown profile: " + profile);
        }
    }

    public static StudentService getStudentService() {
        StudentDAO  studentDAO = getStudentDAO();
        StudentService ss = new StudentService(studentDAO);

        return ss;
    }
}
