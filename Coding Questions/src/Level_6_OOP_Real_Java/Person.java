package Level_6_OOP_Real_Java;

public final class Person {

	private final int id;
	private final String name;

	public Person(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}