package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.DadosBancarios;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigraDadosBancariosStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public MigraDadosBancariosStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step migraDadosBancariosStep(@Qualifier(value = "arquivoDadosBancariosFlatFileItemReader") ItemReader<DadosBancarios> dadosBancariosItemReader,
                                        @Qualifier(value = "dadosBancariosJdbcItemWriter") ItemWriter<DadosBancarios> dadosBancariosItemWriter){
        return new StepBuilder("migraDadosBancariosStep",jobRepository)
                .<DadosBancarios,DadosBancarios>chunk(1,platformTransactionManager)
                .reader(dadosBancariosItemReader)
                .writer(dadosBancariosItemWriter)
                .build();
    }

}
