package br.com.dantas.springbatchv5.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContasBancariasJobConfig {

    private final JobRepository jobRepository;

    public ContasBancariasJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job contasBancariasJob(Step contaBancariaStep) {
        return new JobBuilder("contaBancariaJob", jobRepository)
                .start(contaBancariaStep)
                .incrementer(new RunIdIncrementer())
                .build();
    }

}
