package in.digeshwar.config;

import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import org.springframework.stereotype.Component;

@Component
public class ApiRateLimiterEvents {

    public ApiRateLimiterEvents(RateLimiterRegistry registry) {
        System.out.println(">>> ApiRateLimiterEvents initialized");

        RateLimiter rl = registry.rateLimiter("apiRateLimiter");

        rl.getEventPublisher()
                .onSuccess(event ->
                        System.out.println(
                                "RATE LIMIT OK: " + event.getRateLimiterName()
                        )
                )
                .onFailure(event ->
                        System.out.println(
                                "RATE LIMIT BLOCKED: " + event.getRateLimiterName()
                        )
                );
    }
}
