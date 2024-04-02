package br.com.dantas.springbatchv5;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * The annotation @EnableBatchProcessing is not required since Spring Boot 3
 */
@Configuration
public class BatchConfig {

    @Bean
    public Job job(JobRepository jobRepository, Step myStep) {
        return new JobBuilder("myJob", jobRepository)
                .start(myStep)
                .incrementer(new RunIdIncrementer()) // INCREMENTED TO ALLOWS BATCH RUNS MANY TIMES AND COULD INDENTIFY ON DATABASE
                .build();
    }

    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("myStep", jobRepository)
                .tasklet(getPrintMessageTasklet(null), transactionManager)
                .build();
    }

    /** We definied as a @Bean and @StepScope to be able to catch parameters from Spring context,
     * the @Value annotation with this pattern is pointing to jobParameters from Spring Batch context catch some executions arguments
     **/
    @Bean
    @StepScope
    public Tasklet getPrintMessageTasklet(@Value("#{jobParameters['parameterToMyBatch']}") String parameterToBatchTasklet) {
        return (contribution, chunkContext) -> {
            System.out.println(String.format("Spring batch job -> %s", parameterToBatchTasklet));
            return RepeatStatus.FINISHED;
        };
    }

}
