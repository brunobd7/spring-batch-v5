package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static java.lang.String.format;

@Configuration
public class BudgetaryItemWriterConfig {

    @Bean
    public ItemWriter<FinancialTransaction> financialTransactionItemWriter() {
        return itens -> itens.forEach(finTransaction -> {
            System.out.println("---- Demonstrativo orçamentário ----");

            System.out.println(format("[%s] %s - R$ %s", finTransaction.getTransactionTypeId(),
                    finTransaction.getTransactionDescription(), finTransaction.getTotalItensAmount()));

            finTransaction.getFinancialItems().forEach(item -> {
                System.out.println(format("[%s] %s - R$ %s", item.getTransactionDate(),
                        item.getTransactionItem(), item.getTransactionAmount()));
            });

        });
    }
}
