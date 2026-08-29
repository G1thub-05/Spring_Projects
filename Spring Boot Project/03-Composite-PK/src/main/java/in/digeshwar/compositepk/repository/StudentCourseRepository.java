package in.digeshwar.compositepk.repository;
import in.digeshwar.compositepk.entity.StudentCourse;
import in.digeshwar.compositepk.entity.StudentCourseId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseRepository
		extends JpaRepository<StudentCourse, StudentCourseId> {
}