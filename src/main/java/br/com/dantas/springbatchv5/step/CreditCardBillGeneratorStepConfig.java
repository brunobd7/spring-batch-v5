package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.FaturaCartaoCredito;
import br.com.dantas.springbatchv5.domain.Transacao;
import br.com.dantas.springbatchv5.reader.FaturaCartaoCreditoReader;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class CreditCardBillGeneratorStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public CreditCardBillGeneratorStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step creditCardBillGeneratorStep(ItemStreamReader<Transacao> transacoesReader,
                                            ItemProcessor<FaturaCartaoCredito> carregarDadosClienteProcessor,
                                            ItemWriter<FaturaCartaoCredito> gerarFaturaCartaoCreditoWriter) {
        return new StepBuilder("creditCardBillGeneratorStep", jobRepository)
                .<FaturaCartaoCredito,FaturaCartaoCredito>chunk(1, platformTransactionManager)
                .reader(new FaturaCartaoCreditoReader(transacoesReader))
                .processor(carregarDadosClienteProcessor)
                .writer(gerarFaturaCartaoCreditoWriter)
                .build();
    }
}
