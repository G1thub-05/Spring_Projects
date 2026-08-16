package in.digeshwar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        // Start IoC container to create all object (beans) Automatically from XML
        ApplicationContext context = new ClassPathXmlApplicationContext("MyApplicationContext.xml");

        // Get car bean
        // option-1 →  Car car1 = (Car) context.getBean("carConstructor");
        // option-2 →  Car car1 =  context.getBean("carConstructor", Car.class);
        Car car2 = context.getBean("carSetter", Car.class);
        car2.drive();
    }
}
