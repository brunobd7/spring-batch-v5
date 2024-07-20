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
public class PrintSomethingJobConfig {

    @Bean
    public Job job(JobRepository jobRepository, Step myStep) {
        return new JobBuilder("myJob", jobRepository)
                .start(myStep)
                .incrementer(new RunIdIncrementer()) // INCREMENTED TO ALLOWS BATCH RUNS MANY TIMES AND COULD INDENTIFY ON DATABASE
                .build();
    }

}
