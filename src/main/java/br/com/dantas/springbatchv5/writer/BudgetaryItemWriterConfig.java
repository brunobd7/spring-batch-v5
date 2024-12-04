package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class BudgetaryItemWriterConfig {

    @Bean
    public ItemWriter<FinancialTransaction> financialTransactionItemWriter() {
        StringBuilder stringBuilder = new StringBuilder();
        return chunk -> chunk.getItems()
                .stream()
                .collect(Collectors.groupingBy(FinancialTransaction::getTransactionTypeId))
                .forEach((key, value) -> {

                    FinancialTransaction financialTransactionType = value.get(0);
                    stringBuilder.append("---- Demonstrativo orçamentário ----\n");
                    stringBuilder.append("[").append(key).append("] ")
                            .append(financialTransactionType.getTransactionDescription())
                            .append(" - R$").append(financialTransactionType.getTransactionAmount()).append("\n");

                    value.forEach(transactionItem -> {
                        stringBuilder.append("  [").append(transactionItem.getTransactionDate()).append("] ")
                                .append(transactionItem.getTransactionItem()).append(" - R$").append(transactionItem.getTransactionAmount())
                                .append("\n");
                    });

                    System.out.println(stringBuilder.toString());
                });
    }
}
