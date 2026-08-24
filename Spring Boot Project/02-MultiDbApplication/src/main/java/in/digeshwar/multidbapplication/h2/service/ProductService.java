package in.digeshwar.multidbapplication.h2.service;



import in.digeshwar.multidbapplication.h2.entity.Product;
import in.digeshwar.multidbapplication.h2.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	private final ProductRepository productRepository;

	// Constructor Injection
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	// Save product into H2 database
	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}
}