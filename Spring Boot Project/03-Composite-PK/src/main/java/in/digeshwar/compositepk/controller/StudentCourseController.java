package in.digeshwar.compositepk.controller;
import in.digeshwar.compositepk.entity.StudentCourse;
import in.digeshwar.compositepk.service.StudentCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-courses")
public class StudentCourseController {

	private final StudentCourseService service;
	public StudentCourseController(StudentCourseService service) {
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<StudentCourse> save(
			@RequestBody StudentCourse studentCourse) {

		StudentCourse savedStudentCourse =  service.save(studentCourse);
		return ResponseEntity.ok(savedStudentCourse);
	}

	@GetMapping("/{studentId}/{courseId}")
	public ResponseEntity<StudentCourse> getStudentCourse(
			@PathVariable Long studentId,
			@PathVariable Long courseId) {

		return ResponseEntity.ok(
				service.getStudentCourse(studentId, courseId)
		);
	}
}