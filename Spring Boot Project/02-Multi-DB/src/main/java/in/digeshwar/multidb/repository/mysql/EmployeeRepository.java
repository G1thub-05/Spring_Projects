package in.digeshwar.multidb.repository.mysql;

import org.springframework.data.jpa.repository.JpaRepository;
import in.digeshwar.multidb.entity.mysql.Employee;
public interface EmployeeRepository extends JpaRepository<Employee, Long> {}
