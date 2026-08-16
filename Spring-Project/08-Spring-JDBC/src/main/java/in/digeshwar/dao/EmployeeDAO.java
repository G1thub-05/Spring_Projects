package in.digeshwar.dao;
import java.util.List;
import in.digeshwar.model.Employee;

public interface EmployeeDAO {
    void save(Employee emp);      // CREATE
    void update(Employee emp);    // UPDATE
    void delete(int id);          // DELETE
    Employee getById(int id);     // READ ONE
    List<Employee> getAll();      // READ ALL
}
