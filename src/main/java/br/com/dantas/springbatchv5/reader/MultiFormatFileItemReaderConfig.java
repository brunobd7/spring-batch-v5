package br.com.dantas.springbatchv5.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class MultiFormatFileItemReaderConfig {

    // TODO COME BACK AND SOLVE , PATH DID NOT SOLVED BY ENV VARIABLE AND ANNOTATION @Value("#{jobParameters['arquivoClientes']}") Resource file
    @StepScope
    @Bean
    public FlatFileItemReader multiFormatFileItemReader(LineMapper multiFormatFileLineMapper) {
        return new FlatFileItemReaderBuilder<>()
                .name("multiFormatFileItemReader")
                .resource(new PathResource("files/clientes_multiple_formats.txt"))
                .lineMapper(multiFormatFileLineMapper)
                .build();
    }

}
