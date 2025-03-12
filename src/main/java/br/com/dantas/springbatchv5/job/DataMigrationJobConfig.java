package br.com.dantas.springbatchv5.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
                .start(migratePessoaStep)
                .next(migrateDadosBancario)
                .incrementer(new RunIdIncrementer())
                .build();
    }
}
