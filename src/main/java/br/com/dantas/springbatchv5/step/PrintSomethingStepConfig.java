package br.com.dantas.springbatchv5.step;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class PrintSomethingStepConfig {

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager, Tasklet printSomethingTasklet) {
        return new StepBuilder("myStep", jobRepository)
                .tasklet(printSomethingTasklet, transactionManager)
                .build();
    }
}
