package in.digeshwar.Config;

import in.digeshwar.Model.User;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.integration.partition.BeanFactoryStepLocator;
import org.springframework.batch.integration.partition.StepExecutionRequestHandler;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
@EnableIntegration
public class WorkerApp {

    @Autowired
    private DataSource dataSource;

    @Bean
    public IntegrationFlow workerFlow(StepExecutionRequestHandler handler) {
        return IntegrationFlow
                .from("requests")
                .handle(handler)
                .channel("replies")
                .get();
    }

    @Bean
    public StepExecutionRequestHandler stepExecutionRequestHandler(
            JobExplorer jobExplorer,
            BeanFactory beanFactory) {
        StepExecutionRequestHandler handler = new StepExecutionRequestHandler();
        handler.setJobExplorer(jobExplorer);
        BeanFactoryStepLocator locator = new BeanFactoryStepLocator();
        locator.setBeanFactory(beanFactory);
        handler.setStepLocator(locator);
        return handler;
    }

    @Bean
    public Step workerStep(JobRepository jobRepository,
                           PlatformTransactionManager transactionManager,
                           ItemReader<? extends User> reader) {

        return new StepBuilder("workerStep", jobRepository)
                .<User, User>chunk(100, transactionManager)
                .reader(reader)
                .processor(processor())
                .writer(writer())
                .build();
    }

    @Bean
    @StepScope
    public JdbcPagingItemReader<User> reader(
            @Value("#{stepExecutionContext['minId']}") Integer minId,
            @Value("#{stepExecutionContext['maxId']}") Integer maxId) {

        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("select id, name, age");
        provider.setFromClause("from user");
        provider.setWhereClause("where id between " + minId + " and " + maxId);
        provider.setSortKeys(Map.of("id", Order.ASCENDING));

        return new JdbcPagingItemReaderBuilder<User>()
                .name("workerReader")
                .pageSize(100)
                .dataSource(dataSource)
                .queryProvider(provider)
                .rowMapper(new BeanPropertyRowMapper<>(User.class))
                .build();
    }

    @Bean
    public ItemProcessor<User, User> processor() {
        return user -> {
            System.out.println("Processing: " + user.getName());
            return user;
        };
    }

    @Bean
    public ItemWriter<User> writer() {
        return items ->
                items.forEach(u ->
                        System.out.println("Writing: " + u.getName()));
    }
}