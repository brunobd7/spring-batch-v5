package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FinancialTransactionMultiFileItemReaderConfig {


    @StepScope
    @Bean
    public MultiResourceItemReader<FinancialTransaction> financialTransactionMultiFileItemReader(@Value("file:files/lancamentos*.txt") Resource[] resources,
                                                                                                 FlatFileItemReader<FinancialTransaction> flatFileItemReader) {
        return new MultiResourceItemReaderBuilder<FinancialTransaction>()
                .name("financialTransactionMultiFileItemReader")
                .resources(resources)
                .delegate(new FinancialTransactionReader(flatFileItemReader))
                .build();
    }

}
