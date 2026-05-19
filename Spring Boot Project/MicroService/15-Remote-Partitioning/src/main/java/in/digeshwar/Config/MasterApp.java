package in.digeshwar.Config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.batch.integration.partition.MessageChannelPartitionHandler;
import org.springframework.messaging.PollableChannel;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableIntegration
public class MasterApp {

    @Bean
    public Job partitionJob(JobRepository jobRepository, Step masterStep) {
        return new JobBuilder("remotePartitionJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(masterStep)
                .build();
    }

    @Bean
    public Step masterStep(JobRepository jobRepository,
                           Partitioner partitioner,
                           PartitionHandler partitionHandler) {

        return new StepBuilder("masterStep", jobRepository)
                .partitioner("workerStep", partitioner)
                .partitionHandler(partitionHandler)
                .build();
    }

    @Bean
    public Partitioner partitioner() {
        return gridSize -> {
            Map<String, ExecutionContext> partitions = new HashMap<>();

            int range = 1000;
            for (int i = 0; i < gridSize; i++) {
                ExecutionContext context = new ExecutionContext();
                context.putInt("minId", i * range);
                context.putInt("maxId", (i + 1) * range);
                partitions.put("partition" + i, context);
            }
            return partitions;
        };
    }

    @Bean
    public PartitionHandler partitionHandler(
            MessageChannel requests,
            PollableChannel replies,
            JobExplorer jobExplorer) {

        MessageChannelPartitionHandler handler = new MessageChannelPartitionHandler();
        handler.setMessagingOperations(new MessagingTemplate(requests));
        handler.setStepName("workerStep");
        handler.setGridSize(4);
        handler.setReplyChannel(replies);
        handler.setJobExplorer(jobExplorer);

        return handler;
    }

    // Channels
    @Bean
    public DirectChannel requests() {
        return new DirectChannel();
    }

    @Bean
    public PollableChannel replies() {
        return new QueueChannel();
    }
}