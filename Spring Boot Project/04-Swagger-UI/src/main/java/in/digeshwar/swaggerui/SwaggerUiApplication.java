package in.digeshwar.swaggerui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SwaggerUiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SwaggerUiApplication.class, args);

    }

}
