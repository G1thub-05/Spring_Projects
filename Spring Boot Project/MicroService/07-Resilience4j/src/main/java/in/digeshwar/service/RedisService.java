package in.digeshwar.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class RedisService {

    @RateLimiter(name = "apiRateLimiter")
    @Bulkhead(name = "redisBulkhead")
    @TimeLimiter(name = "redisTimeLimiter")
    @CircuitBreaker(
            name = "redisCircuitBreaker",
            fallbackMethod = "redisFallback"
    )

    public CompletableFuture<String> getFromRedis() {
        System.out.println(">>> REDIS METHOD EXECUTED");


        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(2500); // simulate Redis slowness
            throw new RuntimeException("Redis down");
        });
    }

    public CompletableFuture<String> redisFallback(Throwable ex) {
        return CompletableFuture.completedFuture(
                "REDIS_FAILED"
        );
    }

    private void simulateDelay(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
