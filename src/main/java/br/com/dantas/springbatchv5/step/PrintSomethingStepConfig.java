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

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public PrintSomethingStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step step(Tasklet printSomethingTasklet) {
        return new StepBuilder("myStep", jobRepository)
                .tasklet(printSomethingTasklet, transactionManager)
                .build();
    }
}
