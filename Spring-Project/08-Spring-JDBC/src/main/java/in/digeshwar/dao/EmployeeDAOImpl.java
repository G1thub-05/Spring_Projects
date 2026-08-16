package in.digeshwar.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import in.digeshwar.model.Employee;
import org.springframework.stereotype.Repository;


@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Employee emp) {
        String sql = "INSERT INTO employee(name, department, salary) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, emp.getName(), emp.getDepartment(), emp.getSalary());
    }

    @Override
    public void update(Employee emp) {
        String sql = "UPDATE employee SET name=?, department=?, salary=? WHERE id=?";
        jdbcTemplate.update(sql, emp.getName(), emp.getDepartment(), emp.getSalary(), emp.getId());
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM employee WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public Employee getById(int id) {
        String sql = "SELECT * FROM employee WHERE id = ?";
        List<Employee> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Employee.class), id );
        return list.isEmpty() ? null : list.get(0);
    }


    @Override
    public List<Employee> getAll() {
        String sql = "SELECT * FROM employee";
        return jdbcTemplate.query(sql, new EmployeeRowMapper());
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setName(rs.getString("name"));
            e.setDepartment(rs.getString("department"));
            e.setSalary(rs.getDouble("salary"));
            return e;
        }
    }
}