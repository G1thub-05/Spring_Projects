package in.digeshwar.studentpagination.service;

import in.digeshwar.studentpagination.dto.StudentPageResponseDTO;
import in.digeshwar.studentpagination.entity.Student;
import in.digeshwar.studentpagination.repository.StudentRepository;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	public Slice<Student> getStudents(
			String course,
			Pageable pageable) {

		return studentRepository.findByCourse(
				course,
				pageable
		);
	}

	public StudentPageResponseDTO getStudents(
			String course,
			int pageNum,
			int pageSize) {

		PageRequest pageRequest =
				PageRequest.of(
						pageNum - 1,
						pageSize,
						Sort.by("id").descending()
				);

		Page<Student> page = studentRepository.findAll(pageRequest);
		Slice<Student> slice = studentRepository.findByCourse(course,pageRequest);

		return new StudentPageResponseDTO(
				page.getContent(),       // Current page students
				page.getTotalElements(), // Total records
				page.getTotalPages(),    // Total pages
				page.getNumber() + 1,    // Current page → convert 0-based to 1-based
				page.isFirst(),          // Is first page?
				page.isLast(),           // Is last page?
				slice.hasNext(),         // Has next page?
				slice.hasPrevious()      // Has previous page?
		);
	}
}