package Level_6_OOP_Real_Java;

import java.util.Objects;

class Item {

	int id;
	String name;

	Item(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;

		if (obj == null || getClass() != obj.getClass())
			return false;

		Item item = (Item) obj;

		return id == item.id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Item{id=" + id + ", name='" + name + "'}";
	}
}

public class ObjectMethodsOverriding {

	public static void main(String[] args) {

		Item i1 = new Item(1, "Laptop");
		Item i2 = new Item(1, "Mobile");

		System.out.println(i1.equals(i2));
		System.out.println(i1.hashCode() == i2.hashCode());

		System.out.println(i1);
	}
}