package br.com.dantas.springbatchv5.writter;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrintResultItemWritterConfig {

    /**
     * The item writter receives a collection proccessed by item processor
     */
    @Bean
    public ItemWriter<String> printResultItemWritter() {
        return chunk -> chunk.forEach(System.out::println);
    }
}
