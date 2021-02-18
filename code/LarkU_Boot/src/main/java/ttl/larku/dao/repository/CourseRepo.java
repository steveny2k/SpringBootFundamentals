package ttl.larku.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ttl.larku.domain.Course;
import ttl.larku.domain.Student;

import java.util.List;

@Repository
@Transactional
public interface CourseRepo extends JpaRepository<Course, Integer> {

    @Query("select c from Course c where c.code = :code")
    public List<Student> findByCode(@Param("code") String code);
}
