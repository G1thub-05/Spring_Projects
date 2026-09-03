package Level_5_Java_8;

import java.util.Arrays;
import java.util.List;

class MyProduct {

	int id;
	String name;
	double price;

	MyProduct(int id, String name, double price) {
		this.id = id;
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return name + " - " + price;
	}
}

public class SortProductsByPrice {

	public static void main(String[] args) {

		List<MyProduct> products = Arrays.asList(
				new MyProduct(1, "Laptop", 60000),
				new MyProduct(2, "Mobile", 30000),
				new MyProduct(3, "Tablet", 40000)
		);

		products.sort((p1, p2) -> Double.compare(p1.price, p2.price));

		for (MyProduct product : products) {
			System.out.println(product);
		}
	}
}
