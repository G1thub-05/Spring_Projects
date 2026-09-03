package Level_4_Collections;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Employee {

	int id;
	String name;
	String department;

	Employee(int id, String name, String department) {
		this.id = id;
		this.name = name;
		this.department = department;
	}

	@Override
	public String toString() {
		return id + " " + name;
	}
}

public class GroupObjectsUsingStream {

	public static void main(String[] args) {

		List<Employee> employees = new ArrayList<>();

		employees.add(new Employee(1, "Rahul", "IT"));
		employees.add(new Employee(2, "Amit", "HR"));
		employees.add(new Employee(3, "Neha", "IT"));
		employees.add(new Employee(4, "Priya", "HR"));

		Map<String, List<Employee>> groupedEmployees =
				employees.stream()
						.collect(Collectors.groupingBy(e -> e.department));

		System.out.println(groupedEmployees);
	}
}
