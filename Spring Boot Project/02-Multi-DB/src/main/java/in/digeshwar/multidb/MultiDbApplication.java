package in.digeshwar.multidb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MultiDbApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(MultiDbApplication.class, args);
		Service service = ctx.getBean(Service.class);

		service.saveInMySQL();
		service.saveInH2();
		System.out.println("✅ Employee saved in MySQL and Product saved in H2 Database!");
	}
}
