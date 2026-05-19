package in.digeshwar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer age;

    public Student() {} // Uses by Hibernate

    public Student(String name, Integer age){
        this.age = age;
        this.name = name;
    }
}
