
package in.digeshwar;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy // Optional: Car object will be created only when you call context.getBean(Car.class).
public class Car {

    @Autowired
    private Engine engineField;   // Field injection

    private Engine engineConstructor;
    @Autowired
    public Car(Engine engineConstructor) {
        this.engineConstructor = engineConstructor;
        System.out.println("Car object created via Constructor Injection");
    }

    private Engine engineSetter;
    @Autowired
    public void setEngineSetter(Engine engineSetter) {
        this.engineSetter = engineSetter;
        System.out.println("Engine injected via Setter Injection");
    }

    @PostConstruct
    public void init() {
        System.out.println("Car bean fully initialized!");
    }

    public void drive() {
        engineField.start();  // also using engineSetter.start(); and engineConstructor.start();
        System.out.println("Driving using Field Injection engine...");
    }
}
