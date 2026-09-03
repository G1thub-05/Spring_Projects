package Level_4_Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Product {

	int id;
	String name;

	Product(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}

public class ListToMap {

	public static void main(String[] args) {

		List<Product> products = new ArrayList<>();

		products.add(new Product(1, "Laptop"));
		products.add(new Product(2, "Mobile"));
		products.add(new Product(3, "Tablet"));

		Map<Integer, Product> productMap =
				products.stream()
						.collect(Collectors.toMap(p -> p.id, p -> p));

		System.out.println(productMap);
	}
}