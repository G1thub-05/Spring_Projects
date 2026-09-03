package Level_4_Collections;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Student {

	int id;
	String name;
	int marks;

	Student(int id, String name, int marks) {
		this.id = id;
		this.name = name;
		this.marks = marks;
	}

	@Override
	public String toString() {
		return id + " " + name + " " + marks;
	}
}

public class SortCustomObjects {

	public static void main(String[] args) {

		List<Student> students = new ArrayList<>();

		students.add(new Student(1, "Rahul", 75));
		students.add(new Student(2, "Amit", 90));
		students.add(new Student(3, "Neha", 60));

		System.out.println("Sort by marks in Ascending order");
		students.sort(Comparator.comparingInt(s -> s.marks));

		for (Student student : students) {
			System.out.println(student);
		}

		System.out.println("\nSort by marks in Descending order");
		students.sort(Comparator.comparingInt((Student s) -> s.marks).reversed());
		for (Student student : students) {
			System.out.println(student);
		}
	}
}