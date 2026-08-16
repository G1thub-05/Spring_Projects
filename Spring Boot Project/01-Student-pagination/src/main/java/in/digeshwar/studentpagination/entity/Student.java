package in.digeshwar.studentpagination.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
	private String name;
	private String email;
	private String course;
	public Student() {
	}

	public Student(String name, String email, String course) {
		this.name = name;
		this.email = email;
		this.course = course;
	}
}