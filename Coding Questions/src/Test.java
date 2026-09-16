import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test {
	public static void main(String[] args) {
		Student std1 = new Student ("Aditya", 21);
		Student std2 = new Student ("Digeshwar", 22);
		Student std3 = new Student ("Rahul", 23);

		List<Student> list = new ArrayList<>();
		list.add(std2);
		list.add(std3);
		list.add(std1);

		System.out.println(list);
		list.sort(Comparator.comparing(Student::getName));
		System.out.println(list);

	}
}
class Student implements Comparator<Student> {
	String name;
	int age;

	public Student(String name, int age) {
		this.name = name;
		 this.age = age;
	}
	public String getName() {
		return name;
	}

	@Override
	public int compare(Student o1, Student o2) {
		return 0;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + "]";
	}

}