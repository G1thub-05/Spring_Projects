package in.digeshwar.components;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ReportScheduler {

    private final Counter successCounter;
    private final Counter failureCounter;
    private final Timer executionTimer;
    private volatile LocalDateTime lastRunTime;

    public ReportScheduler(MeterRegistry registry) {
        this.successCounter = registry.counter("report.success.count");
        this.failureCounter = registry.counter("report.failure.count");
        this.executionTimer = registry.timer("report.execution.time");
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void generateMonthlyReport() {

        executionTimer.record(() -> {
            try {
                Thread.sleep(2000);
                successCounter.increment();
            } catch (Exception e) {
                failureCounter.increment();
                throw new RuntimeException(e);
            }
        });
    }



    @Scheduled(fixedRate = 60000)
    public void runJob() {
        lastRunTime = LocalDateTime.now();

        System.out.println("Job executed at: " + lastRunTime);
    }

    public LocalDateTime getLastRunTime() {
        return lastRunTime;
    }
}