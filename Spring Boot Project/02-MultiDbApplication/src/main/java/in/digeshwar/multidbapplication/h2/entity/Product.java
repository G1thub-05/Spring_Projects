package in.digeshwar.multidbapplication.h2.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "products")
public class Product {

	@Id
	private Long id;
	private String name;
	private Double price;

	public Product() {} // Required by JPA
}