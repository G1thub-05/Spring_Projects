package in.digeshwar.jackson;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

class Employee {
	public int id;
	public String name;

	public Employee() {}
	public Employee(int id, String name) { this.id = id; this.name = name; }
}

public class JacksonCSVDataBinding {
	public static void main(String[] args) throws Exception {
		CsvMapper mapper = new CsvMapper();
		CsvSchema schema = mapper.schemaFor(Employee.class).withHeader();

		// Java → CSV
		Employee e = new Employee(1, "Digeshwar");
		String csv = mapper.writer(schema).writeValueAsString(e);
		System.out.println("CSV:\n" + csv);

		// CSV → Java
		Employee read = mapper.readerFor(Employee.class)
				.with(schema)
				.readValue(csv);
		System.out.println("Employee: " + read.id + " " + read.name);
	}
}
