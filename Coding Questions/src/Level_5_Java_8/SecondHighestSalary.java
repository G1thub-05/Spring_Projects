package Level_5_Java_8;

import java.util.Arrays;
import java.util.List;

class Employee {

	String name;
	double salary;

	Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
}

public class SecondHighestSalary {

	public static void main(String[] args) {

		List<Employee> employees = Arrays.asList(
				new Employee("Rahul", 50000),
				new Employee("Amit", 70000),
				new Employee("Neha", 60000),
				new Employee("Priya", 70000)
		);

		double secondHighestSalary = employees.stream()
				.map(e -> e.salary)
				.distinct()
				.sorted((a, b) -> Double.compare(b, a))
				.skip(1)
				.findFirst()
				.orElse(0.0);

		System.out.println("Second Highest Salary: " + secondHighestSalary);
	}
}