package in.digeshwar._1keysetseekpagination.controller;

import in.digeshwar._1keysetseekpagination.entity.Student;
import in.digeshwar._1keysetseekpagination.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentService studentService;
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public List<Student> getStudents(
			@RequestParam(required = false) Long lastSeenId,
			@RequestParam(defaultValue = "3")  int pageSize) {
		return studentService.getStudents(lastSeenId, pageSize
		);
	}
}