package in.digeshwar.studentpagination.service;

import in.digeshwar.studentpagination.dto.StudentPageResponseDTO;
import in.digeshwar.studentpagination.entity.Student;
import in.digeshwar.studentpagination.repository.StudentRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public StudentPageResponseDTO getStudents(
			int pageNum,
			int pageSize) {

		PageRequest pageRequest =
				PageRequest.of(
						pageNum - 1,
						pageSize,
						Sort.by("id").descending()
				);

		Page<Student> page =
				studentRepository.findAll(pageRequest);

		return new StudentPageResponseDTO(

				// Current page students
				page.getContent(),

				// Total records
				page.getTotalElements(),

				// Total pages
				page.getTotalPages(),

				// Current page → convert 0-based to 1-based
				page.getNumber() + 1,

				// Is first page?
				page.isFirst(),

				// Is last page?
				page.isLast()
		);
	}
}