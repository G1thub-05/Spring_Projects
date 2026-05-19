package in.digeshwar.config;


import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;


@Component
public class JobMetricsListener implements JobExecutionListener {

    private final MeterRegistry meterRegistry;

    public JobMetricsListener(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {

        jobExecution.getStepExecutions().forEach(stepExecution -> {
            meterRegistry.counter("batch.read.count").increment(stepExecution.getReadCount());
            meterRegistry.counter("batch.write.count").increment(stepExecution.getWriteCount());
        });
    }
}


//Now you can expose metrics to:
    // Prometheus
    // Grafana
    // Actuator endpoint
//That’s real enterprise-level monitoring.