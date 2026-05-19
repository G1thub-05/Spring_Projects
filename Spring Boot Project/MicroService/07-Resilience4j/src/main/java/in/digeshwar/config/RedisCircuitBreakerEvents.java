package in.digeshwar.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.stereotype.Component;

@Component
public class RedisCircuitBreakerEvents {

    public RedisCircuitBreakerEvents(CircuitBreakerRegistry registry) {
        System.out.println(">>> RedisCircuitBreakerEvents initialized");
        CircuitBreaker cb = registry.circuitBreaker("redisCircuitBreaker");

        cb.getEventPublisher()
                .onSuccess(event ->
                        System.out.println("CB SUCCESS: " + event.getCircuitBreakerName())
                )
                .onError(event ->
                        System.out.println("CB ERROR: " + event.getThrowable().getMessage())
                )
                .onStateTransition(event ->
                        System.out.println(
                                "CB STATE CHANGE: " +
                                        event.getStateTransition().getFromState() +
                                        " -> " +
                                        event.getStateTransition().getToState()
                        )
                )
                .onFailureRateExceeded(event ->
                        System.out.println(
                                "CB FAILURE RATE EXCEEDED: " +
                                        event.getFailureRate()
                        )
                )
                .onCallNotPermitted(event ->
                        System.out.println(
                                "CB BLOCKED CALL (OPEN STATE)"
                        )
                );
    }
}

