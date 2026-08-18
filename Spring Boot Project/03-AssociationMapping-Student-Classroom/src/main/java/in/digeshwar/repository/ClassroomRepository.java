package in.digeshwar.repository;
import in.digeshwar.entity.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository
		extends JpaRepository<Classroom, Long> {
}