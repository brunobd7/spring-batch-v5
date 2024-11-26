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
public class JdbcReaderJobConfig {

    private final JobRepository jobRepository;

    public JdbcReaderJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job jdbcReadingJob(Step jdbcReaderStep){
        return new JobBuilder("multipleFilesJob", jobRepository)
                .start(jdbcReaderStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
