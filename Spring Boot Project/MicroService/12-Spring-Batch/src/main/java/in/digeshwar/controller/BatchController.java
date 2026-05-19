package in.digeshwar.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/batch")
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job importStudentJob;

    public BatchController(JobLauncher jobLauncher, Job importStudentJob) {
        this.jobLauncher = jobLauncher;
        this.importStudentJob = importStudentJob;
    }

    @PostMapping("/run")
    public String runBatch() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()

                // Job Instance = Job Name + Job Parameters
                // Spring → Spring will NOT allow same Job Instance to run again.
                .addString("fileName", "students.csv")
                .addLong("startAt", System.currentTimeMillis())
                .toJobParameters();

        // importStudentJob + startAt=1
        // importStudentJob + startAt=2
        JobExecution execution = jobLauncher.run(importStudentJob, jobParameters);
        return "Status: Batch Job Started ! " + execution.getStatus();
    }
}