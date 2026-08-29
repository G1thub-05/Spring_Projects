package in.digeshwar._1keysetseekpagination.service;

import in.digeshwar._1keysetseekpagination.entity.Student;
import in.digeshwar._1keysetseekpagination.repository.StudentRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

	private final StudentRepository studentRepository;
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents(Long lastSeenId, int pageSize) {
		Pageable pageable = PageRequest.of(0, pageSize);
		if (lastSeenId == null) {
			return studentRepository
					.findAll(PageRequest.of(0, pageSize, Sort.by("id").ascending()))
					.getContent();
		}
		return studentRepository.findByIdGreaterThanOrderByIdAsc(lastSeenId, pageable);
	}
}