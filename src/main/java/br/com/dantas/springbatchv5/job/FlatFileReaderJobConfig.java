package br.com.dantas.springbatchv5.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * The annotation @EnableBatchProcessing is not required since Spring Boot 3
 */
@Configuration
public class FlatFileReaderJobConfig {

    private final JobRepository jobRepository;

    public FlatFileReaderJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

//    @Bean
//    @Primary
    public Job fixedSizeFileJob(Step fixedSizeFileReaderStep) throws Exception {
        return new JobBuilder("fixedSizeFileReaderJob", jobRepository)
                .start(fixedSizeFileReaderStep)
                .incrementer(new RunIdIncrementer()) // INCREMENTED TO ALLOWS BATCH RUNS MANY TIMES AND COULD INDENTIFY ON DATABASE
                .build();
    }

    @Bean
    public Job delimitedFileJob(Step delimitedFileReaderStep){
        return new JobBuilder("delimitedFileReaderJob", jobRepository)
                .start(delimitedFileReaderStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
