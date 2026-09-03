package Level_6_OOP_Real_Java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Teacher {

	int id;
	String name;
	String subject;

	Teacher(int id, String name, String subject) {
		this.id = id;
		this.name = name;
		this.subject = subject;
	}

	@Override
	public String toString() {
		return "ID: " + id +
				", Name: " + name +
				", Subject: " + subject;
	}
}

public class TeacherManagementSystem {

	static List<Teacher> teachers = new ArrayList<>();

	static void addTeacher(Teacher teacher) {
		teachers.add(teacher);
	}

	static void displayTeachers() {
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
	}

	static void findTeacher(int id) {

		for (Teacher teacher : teachers) {

			if (teacher.id == id) {
				System.out.println(teacher);
				return;
			}
		}

		System.out.println("Teacher not found.");
	}

	static void removeTeacher(int id) {

		for (Teacher teacher : teachers) {

			if (teacher.id == id) {
				teachers.remove(teacher);
				System.out.println("Teacher removed.");
				return;
			}
		}

		System.out.println("Teacher not found.");
	}

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		addTeacher(new Teacher(1, "Rahul", "Java"));
		addTeacher(new Teacher(2, "Amit", "Python"));
		addTeacher(new Teacher(3, "Neha", "SQL"));

		System.out.println("All Teachers:");
		displayTeachers();

		System.out.println("\nFind Teacher:");
		findTeacher(2);

		System.out.println("\nRemove Teacher:");
		removeTeacher(2);

		System.out.println("\nAfter Removal:");
		displayTeachers();

		sc.close();
	}
}