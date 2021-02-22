package ttl.larku.dao;

import ttl.larku.domain.Student;

import java.util.List;

public interface StudentDAO {
    void update(Student updateObject);

    void delete(Student student);

    Student create(Student newObject);

    Student get(int id);

    List<Student> getAll();
}
