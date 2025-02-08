package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ClienteInvalidoWriterConfig {

    @Bean
    public FlatFileItemWriter<ContaBancaria> clientesInvalidosFileWriter(){
        return new FlatFileItemWriterBuilder<ContaBancaria>()
                .name("clientesInvalidosFileWriter")
                .resource(new FileSystemResource("./files/clientesInvalidos.txt"))
                .delimited()
                .names("clienteId")
                .build();
    }
}
