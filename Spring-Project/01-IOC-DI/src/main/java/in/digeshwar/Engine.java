package in.digeshwar;
public class Engine {

    // Call when Obj is created using IoC in XML
    public Engine() {
        System.out.println("No-arg Cons: Engine obj is created Automatically by IoC Container ");
    }
    public void start() {
        System.out.println("Engine started!");
    }
}