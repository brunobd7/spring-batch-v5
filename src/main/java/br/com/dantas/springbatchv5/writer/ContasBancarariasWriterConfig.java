package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContasBancarariasWriterConfig {

    @Bean
    public ItemWriter<ContaBancaria> contaBancariaItemWriter(){
        return chunk -> chunk.forEach(System.out::println);
    }
}
