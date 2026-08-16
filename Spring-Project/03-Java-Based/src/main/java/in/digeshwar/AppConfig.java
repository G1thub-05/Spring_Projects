package in.digeshwar;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration   // tells Spring: this class will provide and contains bean definitions
public class AppConfig {

    @Bean   // Spring will create and manage Engine object
    public Engine engine() {
        return new Engine();
    }

    @Bean  // Spring will create Car and inject Engine into it
    public Car carByConstructor() {
        return new Car(engine()); // constructor injection
    }

    @Bean   // Setter Injection
    public Car carBySetter() {
        Car car = new Car();     // create Car object
        car.setEngine(engine()); // inject Engine using setter
        return car;
    }
}
