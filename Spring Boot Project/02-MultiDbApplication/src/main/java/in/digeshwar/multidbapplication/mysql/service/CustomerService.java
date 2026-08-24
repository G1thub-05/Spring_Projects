package in.digeshwar.multidbapplication.mysql.service;
import in.digeshwar.multidbapplication.mysql.entity.Customer;
import in.digeshwar.multidbapplication.mysql.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

	private final CustomerRepository customerRepository;

	// Constructor Injection
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	// Save customer into MySQL database
	public Customer saveCustomer(Customer customer) {
		return customerRepository.save(customer);
	}
}