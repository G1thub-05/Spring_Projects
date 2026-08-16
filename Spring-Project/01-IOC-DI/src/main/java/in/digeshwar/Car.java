package in.digeshwar;
public class Car {
    private Engine engine;

    // Call when Obj is created using IoC in XML
    public Car() {
        System.out.println("No-arg Cons: Car obj is created Automatically by IoC Container");
    }

    // Constructor Injection
    public Car(Engine c_engine) {
        this.engine = c_engine;
        System.out.println("arg Cons: Engine obj is Inject in Car using Constructor DI ");
    }

    // Setter Injection
    public void setMy_s_engine(Engine engine) {
        this.engine = engine;
        System.out.println("Setter Method: Engine obj is Inject in Car using Setter DI");
    }

    public void drive() {
        if (engine != null) {
            engine.start();
            System.out.println("Car is driving using Engine object...");
        } else {
            System.out.println("No engine found! Car cannot drive.");
        }
    }
}
