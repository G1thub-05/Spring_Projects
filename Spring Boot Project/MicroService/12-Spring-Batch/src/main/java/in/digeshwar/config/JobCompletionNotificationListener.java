package in.digeshwar.config;

import in.digeshwar.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionNotificationListener implements JobExecutionListener {

    private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
    private final JdbcTemplate jdbcTemplate;

    public JobCompletionNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {

            jobExecution.getStepExecutions().forEach(stepExecution -> {
                log.info("!!! JOB FINISHED! Time to verify the results");
                log.info("Step Name: {}", stepExecution.getStepName());
                log.info("Read Count: {}", stepExecution.getReadCount());
                log.info("Write Count: {}", stepExecution.getWriteCount());
                log.info("Commit Count: {}", stepExecution.getCommitCount());
                log.info("Rollback Count: {}", stepExecution.getRollbackCount());
                log.info("Skip Count: {}", stepExecution.getSkipCount());
            });

            jdbcTemplate
                    .query("SELECT id, name, marks FROM student", new BeanPropertyRowMapper<>(Student.class))
                    .forEach(student -> log.info("Found <{}> in the database.", student));

            Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM student", Integer.class);
            log.info("Total records inserted: {}", count);
        }
    }
}


