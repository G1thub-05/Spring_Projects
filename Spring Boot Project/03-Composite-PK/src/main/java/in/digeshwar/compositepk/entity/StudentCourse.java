package in.digeshwar.compositepk.entity;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Table(name = "student_course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourse {

	// Embeds studentId + courseId as the Composite Primary Key
	@EmbeddedId
	private StudentCourseId id;

	@Column(name = "student_name")
	private String studentName;

	@Column(name = "course_name")
	private String courseName;

	@Column(name = "enrollment_date")
	private LocalDate enrollmentDate;
}