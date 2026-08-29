package in.digeshwar.compositepk.entity;
import jakarta.persistence.Embeddable;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

// 🔥 Composite Key Class
public class StudentCourseId implements Serializable {
	private Long studentId; // Part of the Composite Primary Key
	private Long courseId;  // Part of the Composite Primary Key
}