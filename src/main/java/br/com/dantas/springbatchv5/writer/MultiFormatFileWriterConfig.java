package br.com.dantas.springbatchv5.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultiFormatFileWriterConfig {

    @Bean
    public ItemWriter genericFileWriter() {
        return items -> items.forEach(System.out::println);
    }
}
