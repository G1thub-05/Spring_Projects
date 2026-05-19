package in.digeshwar.associationmapping.entity;


import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    private String fullName;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")   // OWNER (FK is here)
    private Teacher teacher;
}
