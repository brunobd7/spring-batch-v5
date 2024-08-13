package br.com.dantas.springbatchv5.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The annotation @EnableBatchProcessing is not required since Spring Boot 3
 */
@Configuration
public class FixedSizeFileReaderJobConfig {

    private final JobRepository jobRepository;

    public FixedSizeFileReaderJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job job(Step FixedSizeFileReaderStep) throws Exception {
        return new JobBuilder("fixedSizeFileReaderJob", jobRepository)
                .start(FixedSizeFileReaderStep)
                .incrementer(new RunIdIncrementer()) // INCREMENTED TO ALLOWS BATCH RUNS MANY TIMES AND COULD INDENTIFY ON DATABASE
                .build();
    }

}
