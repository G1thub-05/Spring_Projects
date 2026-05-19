package in.digeshwar.Config;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import in.digeshwar.Model.User;
import org.springframework.batch.core.partition.PartitionHandler;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.step.builder.StepBuilder;

import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.context.annotation.*;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class BatchConfig {


    private final DataSource dataSource;
    public BatchConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public Job partitionJob(JobRepository jobRepository, Step masterStep) {
        return new JobBuilder("partitionJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(masterStep)
                .build();
    }

    @Bean
    public Step masterStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           ItemReader<User> userReader) throws Exception {

        return new StepBuilder("masterStep", jobRepository)
                .partitioner("slaveStep", partitioner())
                .partitionHandler(partitionHandler(jobRepository, transactionManager, userReader))
                .build();
    }

    @Bean
    public PartitionHandler partitionHandler(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager,
            ItemReader<User> userReader) throws Exception {

        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setTaskExecutor(taskExecutor());
        handler.setStep(slaveStep(jobRepository, transactionManager, userReader));
        handler.setGridSize(4);
        return handler;
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(3);
        executor.setQueueCapacity(1);
        executor.setThreadNamePrefix("Partition-thread-");
        executor.initialize();
        return executor;
    }

    // ========================= SLAVE STEP =========================

    @Bean
    public Step slaveStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager,
                          ItemReader<User> userReader) throws Exception {

        return new StepBuilder("SlaveStep", jobRepository)
                .<User, User>chunk(3, transactionManager)
                .reader(userReader)
                .processor(userProcessor())
                .writer(userWriter())
                .faultTolerant()
                .retryLimit(3)
                .retry(Exception.class)
                .build();
    }

    // ========================= PARTITIONER =========================

    @Bean
    public Partitioner partitioner() {

        return gridSize -> {

            Map<String, ExecutionContext> partitions = new HashMap<>();

            final int GLOBAL_MIN = 1;
            final int GLOBAL_MAX = 3; // 1_000_000
            int totalRecords = GLOBAL_MAX - GLOBAL_MIN + 1;
            int range = (totalRecords + gridSize - 1) / gridSize;

            for (int i = 0; i < gridSize; i++) {

                int start = GLOBAL_MIN + (i * range);
                if (start > GLOBAL_MAX) break;

                int end = Math.min(start + range - 1, GLOBAL_MAX);
                ExecutionContext context = new ExecutionContext();
                context.putInt("minId", start);
                context.putInt("maxId", end);
                partitions.put("partition" + i, context);
                System.out.println("Range for Partition-" + i + " is: " + start + " - " + end);
            }
            return partitions;
        };
    }

    // ========================= READER =========================

    @Bean
    @StepScope
    public JdbcPagingItemReader<User> userReader(
            @Value("#{stepExecutionContext['minId']}") Integer minId,
            @Value("#{stepExecutionContext['maxId']}") Integer maxId) {

        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("SELECT id, name, age");
        provider.setFromClause("FROM user");
        provider.setWhereClause("WHERE id BETWEEN " + minId + " AND " + maxId);
        provider.setSortKeys(Map.of("id", Order.ASCENDING));

        return new JdbcPagingItemReaderBuilder<User>()
                .name("userReader")
                .pageSize(100)
                .dataSource(dataSource)
                .queryProvider(provider)
                .rowMapper(new BeanPropertyRowMapper<>(User.class))
                .build();
    }

    @Bean
    public ItemProcessor<User, User> userProcessor() {
        return user -> {

            System.out.println(Thread.currentThread().getName() + " Processing User: " + user);
            if (user.getAge() < 0) {
                throw new RuntimeException("Invalid age");
            }
            return user;
        };
    }

    @Bean
    public ItemWriter<User> userWriter() {
        return users -> {
            for (User user : users) {
                System.out.println(Thread.currentThread().getName() +
                        " Writing User   : " + user);
            }
        };
    }
}