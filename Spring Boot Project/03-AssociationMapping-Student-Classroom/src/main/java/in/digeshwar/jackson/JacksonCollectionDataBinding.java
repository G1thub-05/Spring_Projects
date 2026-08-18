package in.digeshwar.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.io.File;
import java.util.Arrays;

@Data
class Customer {
	private Integer id;
	private String name;
	private Integer phno;
}

public class JacksonCollectionDataBinding  {
	public static void main(String[] args) throws Exception {
		JacksonCollectionDataBinding a = new JacksonCollectionDataBinding();
		a.convertJavaToJson(); // for java to JSON
		a.convertJsonToJava(); // for JSON to java
	}

	public void convertJavaToJson() throws Exception {
		Customer c1 = new Customer();
		c1.setId(101);
		c1.setName("Digeshwar");
		c1.setPhno(79797979);

		Customer c2 = new Customer();
		c2.setId(102);
		c2.setName("John");
		c2.setPhno(79797979);

		ObjectMapper mapper = new ObjectMapper();
		System.out.println("JSON created in current path with filename customer.json");
		mapper.writeValue(new File("customer.json"), Arrays.asList(c1,c2)); // Write JSON → File
		mapper.writeValue(System.out, Arrays.asList(c1,c2)); // Write JSON → Console
	}

	public void convertJsonToJava() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		Customer[] customers = mapper.readValue(new File("customer.json"), Customer[].class);
		System.out.println("Java object created mapped with Customer.class ");
		for(Customer c : customers){
			System.out.println(c);
		}
	}
}

