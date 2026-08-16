package in.digeshwar;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import in.digeshwar.config.AppConfig;
import in.digeshwar.dao.EmployeeDAO;
import in.digeshwar.model.Employee;
public class App
{
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployeeDAO dao = context.getBean(EmployeeDAO.class);

        // CREATE
        Employee emp1 = new Employee("Alice", "IT", 50000);
        dao.save(emp1);
        System.out.println("Employee: " + emp1 + " —→ added successfully.");

        System.out.println("\nAll Employees:");
        dao.getAll().forEach(System.out::println); // READ ALL

        Employee emp = dao.getById(1); // READ BY ID
        System.out.println("\nEmployee by ID (1): " + emp);

        emp.setSalary(65000);
        dao.update(emp); // UPDATE
        System.out.println("Employee updated: " + dao.getById(1));



        if(dao.existsById(2)) {
            dao.delete(2); // DELETE
            System.out.println("Employee with id 2 deleted successfully.");
        }
        else {
            System.out.println("Employee with id 2 does not exist.");
        }

        context.close();
    }
}
