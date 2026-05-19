package in.digeshwar.service;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Service
public class DatabaseService {
    private final Executor dbExecutor;
    public DatabaseService() { this.dbExecutor = Executors.newFixedThreadPool(5);}

    @Bulkhead(
            name = "dbBulkhead",
            type = Bulkhead.Type.THREADPOOL,
            fallbackMethod = "dbFallback"
    )
    @TimeLimiter(name = "dbTimeLimiter")
    public CompletableFuture<String> getFromDatabase() {

        return CompletableFuture.supplyAsync(() -> {
            simulateDelay(3000);
            return "DATA_FROM_DATABASE";
        }, dbExecutor);
    }

    public CompletableFuture<String> dbFallback(Throwable ex) {
        return CompletableFuture.completedFuture(
                "DATABASE_OVERLOADED"
        );
    }

    private void simulateDelay(long ms) {
        try { Thread.sleep(ms); } catch (InterruptedException ignored) {}
    }
}
