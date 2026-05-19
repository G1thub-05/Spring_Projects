package in.digeshwar.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
@EnableDiscoveryClient
@EnableFeignClients
@RequestMapping("/order")
public class OrderController {

    private final PaymentClient paymentClient;
    public OrderController(PaymentClient paymentClient) {
        this.paymentClient = paymentClient;
    }

    @Value("${app.message}")
    private String message;

    @Value("${server.port}")
    private String port;

    @GetMapping("/msg")
    public String msg() {
        return message + " at " + port;
    }

    @Autowired
    private Environment env;
    @GetMapping("/place")
    public String placeOrder() {
        String payment = paymentClient.makePayment();
        String port = env.getProperty("server.port");
        return "Order placed & " + payment + " running on port " + port;

    }
}
