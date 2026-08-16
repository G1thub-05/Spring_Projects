package in.digeshwar;

import org.springframework.stereotype.Component;

@Component   // Spring will auto-detect this class as a bean
public class Engine {
    public void start() {
        System.out.println("Engine started...");
    }
}
