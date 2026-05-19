package in.digeshwar.associationmapping.repository;
import in.digeshwar.associationmapping.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
public interface StudentRepository extends JpaRepository<Student, Long> {}
