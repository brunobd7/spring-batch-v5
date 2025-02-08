package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class FileContaWriterConfig {

    @Bean
    public FlatFileItemWriter<Cliente> fileContaWriter(){
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("fileContaWriter")
                .resource(new PathResource("./files/contas.txt"))
                .delimited()
                .fieldExtractor(item -> new ContaBancaria[]{item.getConta()})
                .build();
    }
}
