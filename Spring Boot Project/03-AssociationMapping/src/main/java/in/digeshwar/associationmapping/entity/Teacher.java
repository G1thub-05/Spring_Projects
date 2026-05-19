package in.digeshwar.associationmapping.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.util.*;

@Entity
@Data
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @OneToMany( mappedBy = "teacher", cascade = CascadeType.ALL, fetch = FetchType.LAZY )
    private List<Student> students = new ArrayList<>();

    // helper method
    public void addStudent(Student s) {
        students.add(s);
        s.setTeacher(this);
    }
}


