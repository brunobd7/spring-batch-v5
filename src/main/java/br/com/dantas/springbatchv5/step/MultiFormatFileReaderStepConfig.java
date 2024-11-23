package br.com.dantas.springbatchv5.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MultiFormatFileReaderStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public MultiFormatFileReaderStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    /* Using default generic for ItemReader, ItemWriter and chunk because we are working with multiples types for this data processing */
    @Bean
    public Step multiFormatFileReaderStep(ItemReader itemReader, ItemWriter itemWriter) {
        return new StepBuilder("multiFormatFileReaderStep", jobRepository)
                .chunk(1, transactionManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }

}
