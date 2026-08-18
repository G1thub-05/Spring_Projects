package in.digeshwar.studentpagination.dto;



import in.digeshwar.studentpagination.entity.Student;

import java.util.List;

public class StudentPageResponseDTO {

	private List<Student> students;
	private long totalElements;
	private int totalPages;
	private int currentPage;
	private boolean first;
	private boolean last;

	private boolean hasNext;
	private boolean hasPrevious;

	public StudentPageResponseDTO(
			List<Student> students,
			long totalElements,
			int totalPages,
			int currentPage,
			boolean first,
			boolean last,
			boolean hasNext,
			boolean hasPrevious) {

		this.students = students;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.first = first;
		this.last = last;
		this.hasNext = hasNext;
		this.hasPrevious = hasPrevious;

	}

	public List<Student> getStudents() {
		return students;
	}
	public long getTotalElements() {return totalElements;}
	public int getTotalPages() {return totalPages;}
	public int getCurrentPage() {return currentPage;}
	public boolean isFirst() {return first;}
	public boolean isLast() {return last;}
	public boolean isHasNext() {return hasNext;}
	public boolean isHasPrevious() {return hasPrevious;}
}