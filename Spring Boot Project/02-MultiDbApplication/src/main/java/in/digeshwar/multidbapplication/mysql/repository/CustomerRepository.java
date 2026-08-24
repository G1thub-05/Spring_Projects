package in.digeshwar.multidbapplication.mysql.repository;
import in.digeshwar.multidbapplication.mysql.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository
		extends JpaRepository<Customer, Long> {
}