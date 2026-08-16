package in.digeshwar;

public class Car {
    private Engine engine;

    // Default constructor
    public Car() {
        System.out.println("Car object created...");
    }

    // Constructor Injection
    public Car(Engine engine) {
        this.engine = engine;
    }

    // Setter method for DI
    public void setEngine(Engine engine) {
        this.engine = engine;
        System.out.println("Engine injected via Setter");
    }

    public void drive() {
        engine.start();
        System.out.println("🚘 Car is running smoothly...");
    }
}
