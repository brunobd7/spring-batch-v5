package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.LineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.*;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import static java.lang.String.format;

@Configuration
public class BudgetaryItemWriterConfig {

    @StepScope
    @Bean
    public FlatFileItemWriter<FinancialTransaction> financialTransactionItemWriter(@Value("#{jobParameters['outputFilePath']}") Resource resourceFile,
                                                                                   FlatFileFooterCallback footerCallback) throws IOException {
        return new FlatFileItemWriterBuilder<FinancialTransaction>()
                .name("financialTransactionItemWriter")
                .resource((WritableResource) resourceFile)
                .lineAggregator(lineAggregator())
                .headerCallback(generateHeaderCallback())
                .footerCallback(footerCallback)
                .build();
    }

    public FlatFileHeaderCallback generateHeaderCallback() {
        return new FlatFileHeaderCallback() {

            @Override
            public void writeHeader(Writer writer) throws IOException {
                writer.append(format("-------------------------------------------------------------------------------%n"));
                writer.append(format("--------------------------- BUDGETARY REPORT ----------------------------------%n"));
                writer.append(format("-------------------------------------------------------------------------------%n"));
                writer.append(format("ID   NAME   AMOUNT %n"));
                writer.append(format("\t DATE   DESCRIPTION   AMOUNT %n"));
                writer.append(format("-------------------------------------------------------------------------------%n"));

            }
        };
    }

    public LineAggregator<FinancialTransaction> lineAggregator() {
        return new LineAggregator<FinancialTransaction>() {
            @Override
            public String aggregate(FinancialTransaction financialTransaction) {

                String financialTransactionGroupFormatted = format("[%s] %s - %s %n",
                        financialTransaction.getTransactionTypeId(),
                        financialTransaction.getTransactionDescription(),
                        NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(financialTransaction.getTotalItensAmount()));

                StringBuilder sb = new StringBuilder();
                financialTransaction.getFinancialItems().forEach(transactionsListItem -> {
                    sb.append(
                            format("[%s] %s - R$ %s %n",
                                    transactionsListItem.getTransactionDate(),
                                    transactionsListItem.getTransactionItem(),
                                    NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
                                            .format(Double.parseDouble(transactionsListItem.getTransactionAmount())))
                    );
                });

                return financialTransactionGroupFormatted.concat(sb.toString());
            }
        };
    }

}
