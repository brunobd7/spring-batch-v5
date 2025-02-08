package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CriacaoContasStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    public CriacaoContasStepConfig(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        this.jobRepository = jobRepository;
        this.transactionManager = transactionManager;
    }

    @Bean
    public Step criacaoContasStep(ItemReader<Cliente> clientesReader,
                                  ItemProcessor leitorClientesProcessor,
                                  ItemWriter<ContaBancaria> contasWriter) {
        return new StepBuilder("criacaoContasStep", jobRepository)
                .chunk(1, transactionManager)
                .reader(clientesReader)
                .processor(leitorClientesProcessor)
                .writer(contasWriter)
                .build();

    }


}
