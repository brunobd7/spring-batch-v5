package br.com.dantas.springbatchv5.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailSalesJobConfig {

    private final JobRepository jobRepository;

    public EmailSalesJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job emailSalesJob(Step step){
        return new JobBuilder("emailSalesJob", jobRepository)
                .start(step)
                .incrementer(new RunIdIncrementer())
                .build();
    }


}
