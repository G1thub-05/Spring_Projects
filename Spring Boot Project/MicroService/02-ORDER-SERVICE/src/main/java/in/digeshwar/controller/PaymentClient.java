package in.digeshwar.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "03-PAYMENT-SERVICE")
public interface PaymentClient {

    @GetMapping("/payment/pay")
    String makePayment();
}
