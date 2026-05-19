package in.digeshwar.repository;

import in.digeshwar.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByNameAndAgeGreaterThan(String name, Integer age);


    // Custom Update Query
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.name = :name WHERE s.id = :id")
    int updateStudentName(@Param("id") Integer id, @Param("name") String name);

    // Custom Delete Query
    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.id = :id")
    int deleteStudentById(@Param("id") Integer id);

}