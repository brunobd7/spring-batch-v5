package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.PlatformTransactionManager;

//@Configuration
public class DelimitedFileReaderStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public DelimitedFileReaderStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

//    @Bean
//    @Primary
    public Step delimitedFileReaderStep(ItemReader<Cliente> delimitedItemReader, ItemWriter<Cliente> delimitedItemWriter) {
        return new StepBuilder("delimitedFileReaderStep", jobRepository)
                .<Cliente,Cliente>chunk(1,transactionManager)
                .reader(delimitedItemReader)
                .writer(delimitedItemWriter)
                .build();
    }

}
