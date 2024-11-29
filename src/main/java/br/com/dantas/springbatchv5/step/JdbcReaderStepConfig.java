package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.Customer;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

import java.sql.SQLException;

@Configuration
public class JdbcReaderStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public JdbcReaderStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    @Primary
    public Step jdbcCursorReaderStep(JdbcCursorItemReader<Customer> itemReader, ItemWriter<Customer> itemWriter) {
        return new StepBuilder("jdbcCursorReaderStep",jobRepository)
                .<Customer,Customer>chunk(1,platformTransactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

    @Bean
    public Step jdbcCursorSkipExceptionHandlerStep(@Qualifier("jdbcCursorItemReaderWithSkipExceptionHandle") JdbcCursorItemReader<Customer> itemReader, ItemWriter<Customer> itemWriter) {
        return new StepBuilder("jdbcCursorItemReaderWithSkipExceptionHandle", jobRepository)
                .<Customer,Customer>chunk(23,platformTransactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .faultTolerant()
                .skip(Exception.class) // SET WHICH KIND EXCEPTION COULD BE SKIPPED
                .skipLimit(2) // MAX ROWS THAT WILL SKIP WHEN EXCEPTION THROWING
                .build();
    }

    @Bean
    public Step jdbcPagingReaderStep(@Qualifier(value = "jdbcPagingItemReader") JdbcPagingItemReader<Customer> itemReader, ItemWriter<Customer> itemWriter) {
        return new StepBuilder("jdbcPagingReaderStep",jobRepository)
                .<Customer,Customer>chunk(1,platformTransactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }
}
