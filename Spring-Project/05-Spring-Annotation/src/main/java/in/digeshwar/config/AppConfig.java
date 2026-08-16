package in.digeshwar.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "in.digeshwar")
public class AppConfig {
    public  AppConfig() {
        System.out.println("AppConfig Constructor");
    }
}

