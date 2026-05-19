package in.digeshwar.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Student {

    @Id
    private Long id;
    private String name;
    private Integer marks;
}