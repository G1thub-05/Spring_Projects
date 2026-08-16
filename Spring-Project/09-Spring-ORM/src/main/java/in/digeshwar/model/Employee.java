package in.digeshwar.model;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Employee_Info")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ✅ Auto-generate ID using MySQL auto-increment
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "department")
    private String department;

    @Column(name = "salary")
    private double salary;

    // ✅ Default constructor required by Hibernate
    public Employee() {}

    public Employee(String name, String department, double salary) {
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

}