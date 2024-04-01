package br.com.dantas.springbatchv5.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {


    @Bean
    public Job job(JobRepository jobRepository, Step myStep){

        return new JobBuilder("myJob", jobRepository)
                .start(myStep)
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager){
        return  new StepBuilder("myStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("Hello world from my spring batch job!");
                    return RepeatStatus.FINISHED;
                },transactionManager).build();
    }

}
