package br.com.dantas.springbatchv5.step;

import br.com.dantas.springbatchv5.domain.Pessoa;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class MigraPessoaStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public MigraPessoaStepConfig(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean
    public Step migraDadosPessoaStep(@Qualifier(value = "arquivoPessoaFlatFileItemReader") ItemReader<Pessoa> arquivoPessoaItemReader,
                                     @Qualifier(value = "pessoaClassifierWriter") ClassifierCompositeItemWriter<Pessoa> pessoaClassifierCompositeItemWriter,
                                     @Qualifier(value = "pessoaInvalidaFlatFileItemWriter") FlatFileItemWriter<Pessoa>  pessoaInvalidaWriter) {
        return new StepBuilder("migraDadosPessoaStep",jobRepository)
                .<Pessoa,Pessoa>chunk(1, platformTransactionManager)
                .reader(arquivoPessoaItemReader)
                .writer(pessoaClassifierCompositeItemWriter)
                .stream(pessoaInvalidaWriter) // Must be included because the classifier do not implement ItemStream interface to control I/O around the resources.
                .build();
    }
}
