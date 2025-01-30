package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.FinancialItem;
import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class FinancialTransactionFlatFilesReaderConfig {


    @StepScope
    @Bean
    public FlatFileItemReader<FinancialTransaction> financialTransactionItemReader(@Value("#{jobParameters['sourceDataFile']}") Resource sourceDataFile) {
        return new FlatFileItemReaderBuilder<FinancialTransaction>()
                .name("financialTransactionItemReader")
                .resource(sourceDataFile)
                .delimited()
                .names("transactionTypeId", "transactionDescription", "transactionItem", "transactionDate", "transactionAmount")
//                .targetType(FinancialTransaction.class)
                .fieldSetMapper(getFinancialTransactionFieldSetMapper())
                .build();
    }

    public FieldSetMapper<FinancialTransaction> getFinancialTransactionFieldSetMapper() {
        return fieldSet -> {

            FinancialTransaction transaction = new FinancialTransaction();

            transaction.setTransactionTypeId(fieldSet.readString("transactionTypeId"));
            transaction.setTransactionDescription(fieldSet.readString("transactionDescription"));
            transaction.setTmpItem(new FinancialItem());

            transaction.getTmpItem().setTransactionItem(fieldSet.readString("transactionItem"));
            transaction.getTmpItem().setTransactionDate(fieldSet.readString("transactionDate"));
            transaction.getTmpItem().setTransactionAmount(fieldSet.readString("transactionAmount"));

            return transaction;
        };
    }

}
