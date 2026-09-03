package Level_5_Java_8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {

	int id;
	String name;
	String category;

	Product(int id, String name, String category) {
		this.id = id;
		this.name = name;
		this.category = category;
	}

	@Override
	public String toString() {
		return name;
	}
}

public class GroupProducts {

	public static void main(String[] args) {

		List<Product> products = Arrays.asList(
				new Product(1, "Laptop", "Electronics"),
				new Product(2, "Shirt", "Clothing"),
				new Product(3, "Mobile", "Electronics"),
				new Product(4, "Jeans", "Clothing")
		);

		Map<String, List<Product>> groupedProducts =
				products.stream()
						.collect(Collectors.groupingBy(p -> p.category));

		System.out.println(groupedProducts);
	}
}