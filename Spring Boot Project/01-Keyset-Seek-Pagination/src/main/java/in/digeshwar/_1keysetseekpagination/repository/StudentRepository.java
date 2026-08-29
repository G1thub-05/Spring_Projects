package in.digeshwar._1keysetseekpagination.repository;

import in.digeshwar._1keysetseekpagination.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository
		extends JpaRepository<Student, Long> {

	List<Student> findByIdGreaterThanOrderByIdAsc(
			Long lastSeenId,
			Pageable pageable
	);
}