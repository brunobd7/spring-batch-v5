package br.com.dantas.springbatchv5.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
public class DataMigrationJobConfig {

    private final JobRepository jobRepository;

    public DataMigrationJobConfig(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job dataMigrationJob(@Qualifier(value = "migraDadosPessoaStep") Step migratePessoaStep,
                                @Qualifier(value = "migraDadosBancariosStep") Step migrateDadosBancario) {
        return new JobBuilder("dataMigrationJob", jobRepository)
                .start(parallelStepsFlow(migratePessoaStep,migrateDadosBancario))
                .end()
                .incrementer(new RunIdIncrementer())
                .build();
    }

    private Flow parallelStepsFlow(Step stepMigratePessoaStep, Step stepMigrateDadosBancario) {

        Flow migrarDadosBancariosFlow = new FlowBuilder<Flow>("migrarDadosBancariosFlow")
                .start(stepMigrateDadosBancario)
                .build();

        Flow parallelStepsFlow = new FlowBuilder<Flow>("parallelStepsFlow")
                .start(stepMigratePessoaStep)
                .split(new SimpleAsyncTaskExecutor())
                .add(migrarDadosBancariosFlow)
                .build();

        return parallelStepsFlow;
    }
}
