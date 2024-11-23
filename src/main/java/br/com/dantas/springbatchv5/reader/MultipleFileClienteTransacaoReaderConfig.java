package br.com.dantas.springbatchv5.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

@Configuration
public class MultipleFileClienteTransacaoReaderConfig {


    // TODO COME BACK AND SOLVE , PATH DID NOT SOLVED BY ENV VARIABLE AND ANNOTATION @Value("#{jobParameters['arquivosClientes']}") [] Resource customersFiles
    @StepScope
    @Bean
    public MultiResourceItemReader multiResourceItemReader( FlatFileItemReader multipleFileItemReader) {
        return new MultiResourceItemReaderBuilder()
                .name("multiResourceItemReader")
                .resources( new PathResource [] {
                        new PathResource("files/clientes_part1.txt"),
                        new PathResource("files/clientes_part2.txt"),
                        new PathResource("files/clientes_part3.txt") }
                ).delegate(new ClienteTransacaoFileReader(multipleFileItemReader))
                .build();
    }

}
