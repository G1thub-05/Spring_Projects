package in.digeshwar.controller;
import in.digeshwar.entity.Classroom;
import in.digeshwar.repository.ClassroomRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

	private final ClassroomRepository classroomRepository;
	public ClassroomController(ClassroomRepository classroomRepository) {
		this.classroomRepository = classroomRepository;
	}

	@GetMapping
	public List<Classroom> getAllClassrooms() {
		return classroomRepository.findAll();
	}

	@GetMapping("/{id}")
	public Classroom getClassroom(@PathVariable Long id) {
		return classroomRepository.findById(id).orElseThrow();
	}
}