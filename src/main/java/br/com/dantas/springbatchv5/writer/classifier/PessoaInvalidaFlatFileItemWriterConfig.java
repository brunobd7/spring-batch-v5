package br.com.dantas.springbatchv5.writer.classifier;

import br.com.dantas.springbatchv5.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class PessoaInvalidaFlatFileItemWriterConfig {

    @Bean
    public FlatFileItemWriter<Pessoa> pessoaInvalidaFlatFileItemWriter(){
        return new FlatFileItemWriterBuilder<Pessoa>()
                .name("pessoaInvalidaFlatFileItemWriter")
                .resource( new FileSystemResource("C:\\Users\\Bruno\\IdeaProjects\\spring-batch-v5\\files\\pessoas-invalidas.csv"))
                .delimited()
                .names("id")
                .build();
    }
}
