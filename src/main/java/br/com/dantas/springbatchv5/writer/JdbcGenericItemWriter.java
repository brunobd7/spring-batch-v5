package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Customer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcGenericItemWriter {

    @Bean
    public ItemWriter<Customer> genericItemWriter() {
        return chunkItem -> chunkItem.forEach(System.out::println);
    }
}
