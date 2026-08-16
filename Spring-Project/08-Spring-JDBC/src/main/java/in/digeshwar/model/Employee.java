package in.digeshwar.model;
import lombok.Data;

@Data
public class Employee {
    private int id;
    private String name;
    private String department;
    private double salary;

    public Employee() {
    }

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }
}