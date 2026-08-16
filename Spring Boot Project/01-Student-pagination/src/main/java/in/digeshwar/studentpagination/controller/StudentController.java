package in.digeshwar.studentpagination.controller;



import in.digeshwar.studentpagination.dto.StudentPageResponseDTO;
import in.digeshwar.studentpagination.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/students")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public StudentPageResponseDTO getStudents(
			@RequestParam int pageNum,
			@RequestParam int pageSize) {

		return studentService.getStudents(
				pageNum,
				pageSize
		);
	}
}
