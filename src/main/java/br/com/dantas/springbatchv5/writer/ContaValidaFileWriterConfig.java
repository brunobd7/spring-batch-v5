package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ContaValidaFileWriterConfig {

    @Bean
    public FlatFileItemWriter<ContaBancaria> contasValidasFileWriter(){
        return new FlatFileItemWriterBuilder<ContaBancaria>()
                .name("contasValidasFileWriter")
                .resource(new FileSystemResource("./files/contasBancariasValidas.txt"))
                .delimited()
                .names("tipoConta", "limite", "clienteId")
                .build();
    }
}
