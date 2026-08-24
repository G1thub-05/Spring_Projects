package in.digeshwar.multidbapplication.h2.controller;
import in.digeshwar.multidbapplication.h2.entity.Product;
import in.digeshwar.multidbapplication.h2.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

	private final ProductService productService;
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@PostMapping
	public Product saveProduct(@RequestBody Product product) {
		return productService.saveProduct(product);
	}
}