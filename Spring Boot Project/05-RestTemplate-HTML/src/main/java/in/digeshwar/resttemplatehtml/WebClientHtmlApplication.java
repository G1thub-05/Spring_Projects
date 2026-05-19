package in.digeshwar.resttemplatehtml;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebClientHtmlApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebClientHtmlApplication.class, args);
    }

    // http://localhost:8080/products   →  open this url after run the application
}
