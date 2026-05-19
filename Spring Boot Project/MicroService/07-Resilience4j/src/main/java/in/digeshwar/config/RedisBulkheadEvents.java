package in.digeshwar.config;

import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import org.springframework.stereotype.Component;

@Component
public class RedisBulkheadEvents {

    public RedisBulkheadEvents(BulkheadRegistry registry) {
        System.out.println(">>> RedisBulkheadEvents initialized");

        Bulkhead bulkhead = registry.bulkhead("redisBulkhead");

        bulkhead.getEventPublisher()
                .onCallPermitted(event ->
                        System.out.println("BULKHEAD SLOT ACQUIRED")
                )
                .onCallRejected(event ->
                        System.out.println("BULKHEAD FULL – REQUEST REJECTED")
                )
                .onCallFinished(event ->
                        System.out.println("BULKHEAD CALL FINISHED")
                );
    }
}
