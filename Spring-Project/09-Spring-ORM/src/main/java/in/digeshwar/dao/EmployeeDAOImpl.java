package in.digeshwar.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import in.digeshwar.model.Employee;

@Repository
@Transactional
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void save(Employee emp) {
        hibernateTemplate.save(emp);
    }

    @Override
    public void update(Employee emp) {
        hibernateTemplate.update(emp);
    }

    @Override
    public void delete(int id) {
        Employee e = hibernateTemplate.get(Employee.class, id);
        if (e != null) {
            hibernateTemplate.delete(e);
        }
    }

    @Override
    public Employee getById(int id) {
        // ✅ Use get() instead of load() to avoid LazyInitializationException
        return hibernateTemplate.get(Employee.class, id);
    }

    @Override
    public boolean existsById(int id) {
        Employee e = hibernateTemplate.get(Employee.class, id);
        return e != null;
    }

    @Override
    public List<Employee> getAll() {
        // ✅ loadAll works fine inside a @Transactional method
        return hibernateTemplate.loadAll(Employee.class);
    }
}
