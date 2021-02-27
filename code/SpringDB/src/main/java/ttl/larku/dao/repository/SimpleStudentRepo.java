package ttl.larku.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ttl.larku.domain.Student;

/**
 * This is the simplest JpaRepository.  Gives you all the basic
 * crud methods:
 * https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html
 *
 * Example of usage in StudentRepoService.
 *
 * StudentRepo in this package has examples of overriding and adding new methods to the Repository.
 *
 */
@Repository
public interface SimpleStudentRepo extends JpaRepository<Student, Integer> {
}
