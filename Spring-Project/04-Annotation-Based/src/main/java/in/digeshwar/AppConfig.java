package in.digeshwar;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//Tells Spring: This class is a configuration class that sets up the Spring container.”
@Configuration
@ComponentScan(basePackages = "in.digeshwar")  // scan this package for @Component classes
public class AppConfig {
}
