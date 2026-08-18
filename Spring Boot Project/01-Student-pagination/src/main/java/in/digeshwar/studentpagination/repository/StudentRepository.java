package in.digeshwar.studentpagination.repository;

import in.digeshwar.studentpagination.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository
		extends JpaRepository<Student, Long> {
		Slice<Student> findByCourse(String course, Pageable pageable);
}