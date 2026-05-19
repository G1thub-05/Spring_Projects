package in.digeshwar.config;

import io.github.resilience4j.timelimiter.TimeLimiter;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.stereotype.Component;

@Component
public class RedisTimeLimiterEvents {

    public RedisTimeLimiterEvents(TimeLimiterRegistry registry) {
        System.out.println(">>> RedisTimeLimiterEvents initialized");

        TimeLimiter tl = registry.timeLimiter("redisTimeLimiter");

        tl.getEventPublisher()
                .onTimeout(event ->
                        System.out.println(
                                "TIME LIMIT EXCEEDED for " + event.getTimeLimiterName()
                        )
                );
    }
}
