package in.digeshwar.studentpagination.repository;

import in.digeshwar.studentpagination.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
		extends JpaRepository<Student, Long> {
}