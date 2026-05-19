package in.digeshwar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class PaymentService {


    @Autowired
    private final RetryTemplate retryTemplate;
    public PaymentService(RetryTemplate retryTemplate) {
        this.retryTemplate = retryTemplate;
    }

    @Scheduled(fixedRate = 5000)
    public void processPayment() {
        retryTemplate.execute(context -> {
            System.out.println("Attempt: " + (context.getRetryCount() + 1));
            callExternalGateway();
            return null;

        }, context -> {
            System.out.println("All retries exhausted. Triggering Recovery callback.");
            System.out.println("This runs after max attempts are exhausted.");
            return null;
        });

    }

    private void callExternalGateway() {
        if (Math.random() < 0.8) { throw new RuntimeException("Gateway temporarily down");}
        System.out.println("Payment successful");
    }
}
