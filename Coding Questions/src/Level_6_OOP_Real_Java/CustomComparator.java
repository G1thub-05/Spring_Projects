package Level_6_OOP_Real_Java;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class Product {

	int id;
	String name;
	double price;

	Product(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return name + " - " + price;
	}
}

public class CustomComparator {

	public static void main(String[] args) {

		List<Product> products = Arrays.asList(
				new Product(1, "Laptop", 60000),
				new Product(2, "Mobile", 30000),
				new Product(3, "Tablet", 40000)
		);

		Comparator<Product> priceComparator =
				(p1, p2) -> Double.compare(p1.price, p2.price);

		products.sort(priceComparator);

		for (Product product : products) {
			System.out.println(product);
		}
	}
}