package in.digeshwar.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    private Integer id;
    private String name;
    private Integer age;
}