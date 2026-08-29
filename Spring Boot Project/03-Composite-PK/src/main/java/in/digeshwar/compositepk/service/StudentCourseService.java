package in.digeshwar.compositepk.service;
import in.digeshwar.compositepk.entity.StudentCourse;
import in.digeshwar.compositepk.entity.StudentCourseId;
import in.digeshwar.compositepk.repository.StudentCourseRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseService {

	private final StudentCourseRepository repository;
	public StudentCourseService(StudentCourseRepository repository) {
		this.repository = repository;
	}

	public StudentCourse save(StudentCourse studentCourse) {
		return repository.save(studentCourse);
	}
	public StudentCourse getStudentCourse(Long studentId, Long courseId) {

		// Create the Composite Primary Key object
		StudentCourseId id = new StudentCourseId(studentId, courseId);

		// Search using the complete Composite Primary Key
		return repository.findById(id)
				.orElseThrow(() ->
				new RuntimeException(
						"Student course not found with studentId: "
								+ studentId
								+ " and courseId: "
								+ courseId
				)
		);
	}
}