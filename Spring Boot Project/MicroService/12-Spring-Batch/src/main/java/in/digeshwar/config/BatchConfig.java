package in.digeshwar.config;

import in.digeshwar.entity.Student;
import in.digeshwar.repository.StudentRepository;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.Locale;

@Configuration
public class BatchConfig {


    // ItemReader (Read from CSV)
    @Bean
    @StepScope  // Helps to get a fresh reader instance for each step.
    public FlatFileItemReader<Student> studentReader() {
        return new FlatFileItemReaderBuilder<Student>()
                .name("studentReader")
                .resource(new ClassPathResource("students.csv"))
                .delimited()
                .names("id", "name", "marks")
                .targetType(Student.class)
                .linesToSkip(1)
                .build();
    }

    // ItemProcessor for step1 (process on csv)
    @Bean
    public ItemProcessor<Student, Student> marksProcessor() {
        return student -> {
            student.setMarks(student.getMarks() + 10);
            return student;
        };
    }

    // ItemProcessor for step2 (process on csv)
    @Bean
    public ItemProcessor<Student, Student> nameProcessor() {
        return student -> {
            student.setName(student.getName().toLowerCase(Locale.ROOT));
            return student;
        };
    }
    
    // New ItemWriter (JPA Repository writer)
    @Bean
    public ItemWriter<Student> writer(StudentRepository repository) {
        return students -> repository.saveAll(students);
    }

    // New Step Configuration for step1 (Using JPA Repository)
    @Bean
    public Step step1(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      ItemWriter<Student> myjpawriter) {
        return new StepBuilder("step1", jobRepository)
                .<Student, Student>chunk(5, transactionManager)
                .reader(studentReader())
                .processor(marksProcessor())
                .writer(myjpawriter)
                .build();
    }

    // New Step Configuration for step2 (Using JPA Repository)
    @Bean
    public Step step2(JobRepository jobRepository,
                      PlatformTransactionManager transactionManager,
                      ItemWriter<Student> myjpawriter) {
        return new StepBuilder("step2", jobRepository)
                .<Student, Student>chunk(5, transactionManager)
                .reader(studentReader())
                .processor(nameProcessor())
                .writer(myjpawriter)
                .build();
    }

    // Job Configuration
    @Bean
    public Job importStudentJob(JobRepository jobRepository, Step step1, Step step2,
                                JobCompletionNotificationListener listener) {
        return new JobBuilder("importStudentJob", jobRepository)
                .listener(listener)
                .start(step1)
                .next(step2)
                .build();
    }
}
