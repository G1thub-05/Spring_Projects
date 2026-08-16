package in.digeshwar;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App
{
    public static void main(String[] args) {
        // Load configuration (AppConfig instead of XML)
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Get Car bean created with Constructor Injection
        Car car1 = context.getBean("carByConstructor", Car.class);
        System.out.println("\n--- Using Constructor Injection ---");
        car1.drive();

        // Get Car bean created with Setter Injection
        Car car2 = context.getBean("carBySetter", Car.class);
        System.out.println("\n--- Using Setter Injection ---");
        car2.drive();

    }
}
