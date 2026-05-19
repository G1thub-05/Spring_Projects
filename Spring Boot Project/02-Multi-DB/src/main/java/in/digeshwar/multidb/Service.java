package in.digeshwar.multidb;

import in.digeshwar.multidb.entity.h2.Product;
import in.digeshwar.multidb.entity.mysql.Employee;
import in.digeshwar.multidb.repository.h2.ProductRepository;
import in.digeshwar.multidb.repository.mysql.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    private EmployeeRepository empRepo;

    @Autowired
    private ProductRepository prodRepo;

    public void saveInMySQL(){
        empRepo.save(new Employee(1L, "Laptop", "IT"));
    }

    public void saveInH2(){
        prodRepo.save(new Product(2L, "Laptop", 70000));
    }

}
