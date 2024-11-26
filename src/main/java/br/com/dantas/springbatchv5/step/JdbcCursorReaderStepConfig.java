package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class JdbcCursorReaderStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public JdbcCursorReaderStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step jdbcCursorReaderStep(JdbcCursorItemReader<Customer> itemReader, ItemWriter<Customer> itemWriter) {
        return new StepBuilder("jdbcCursorReaderStep",jobRepository)
                .<Customer,Customer>chunk(1,platformTransactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}
