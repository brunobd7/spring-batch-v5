package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BudgetaryItemWriterConfig {

    @Bean
    public ItemWriter<FinancialTransaction> financialTransactionItemWriter() {
        return chunk -> chunk.getItems().forEach(System.out::println);
    }
}
