package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class MultipleFilesReaderConfig {


    @StepScope
    @Bean
    public FlatFileItemReader<FinancialTransaction> financialTransactionItemReader(@Value("#{jobParameters['sourceDataFile']}") Resource sourceDataFile) {
        return new FlatFileItemReaderBuilder<FinancialTransaction>()
                .name("financialTransactionItemReader")
                .resource(sourceDataFile)
                .delimited()
                .names(new String [] {"transactionTypeId", "transactionDescription", "transactionItem", "transactionDate", "transactionAmount"})
                .targetType(FinancialTransaction.class)
                .build();
    }
}
