package in.digeshwar.config;

import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryRegistry;
import org.springframework.stereotype.Component;

@Component
public class RetryEvents {

    public RetryEvents(RetryRegistry retryRegistry) {
        System.out.println(">>> RetryEvents initialized");

        // Get the SAME Retry used by @Retry(name="apiRetry")
        Retry retry = retryRegistry.retry("apiRetry");

        retry.getEventPublisher()
                .onRetry(event ->
                        System.out.println(
                                "RETRY ATTEMPT: " +
                                        event.getNumberOfRetryAttempts() +
                                        " | last exception: " +
                                        event.getLastThrowable().getMessage()
                        )
                )
                .onSuccess(event ->
                        System.out.println("RETRY SUCCESS after attempts: " +
                                event.getNumberOfRetryAttempts())
                )
                .onError(event ->
                        System.out.println("RETRY FAILED after attempts: " +
                                event.getNumberOfRetryAttempts())
                );
    }
}
