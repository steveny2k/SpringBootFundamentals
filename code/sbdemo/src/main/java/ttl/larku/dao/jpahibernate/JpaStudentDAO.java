package ttl.larku.dao.jpahibernate;

import org.springframework.transaction.annotation.Transactional;
import ttl.larku.dao.BaseDAO;
import ttl.larku.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
public class JpaStudentDAO implements BaseDAO<Student> {

    @PersistenceContext
    private EntityManager manager;

    private String from;

    public JpaStudentDAO(String from) {
        this.from = from + ": ";
    }

    public JpaStudentDAO() {
        this("JPA");
    }

    public void update(Student updateObject) {
        manager.merge(updateObject);
//        if (students.containsKey(updateObject.getId())) {
//            students.put(updateObject.getId(), updateObject);
//        }
    }

    public void delete(Student student) {
        manager.remove(student);
    }

    public Student create(Student newObject) {
        //Put our Mark
        newObject.setName(from + newObject.getName());

        manager.persist(newObject);

        return newObject;
    }

    public Student get(int id) {
        return manager.find(Student.class, id);
    }

    public List<Student> getAll() {
        TypedQuery<Student> query = manager.createQuery("Select s from Student s", Student.class);
        List<Student> students = query.getResultList();

        return students;
    }

//    public void deleteStore() {
//        students = null;
//    }
//
//    public void createStore() {
//        students = new HashMap<Integer, Student>();
//    }
//
//    public Map<Integer, Student> getStudents() {
//        return students;
//    }
}
