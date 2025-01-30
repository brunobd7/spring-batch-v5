package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

@Configuration
public class DelimitedFileReaderWriterConfig {

    @Bean
//    @Primary
    public FlatFileItemWriter<Cliente> delimitedFileReaderWriter() {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("delimitedFileReaderWriter")
                .resource(new PathResource("files/new_delimited_file_from_writer.txt")) // PATH AND NAME OF DESIRED OUTPUT FILE
                .delimited()
                .delimiter(";")
                .names("nome", "sobrenome", "idade", "email" )
                .build();
    }
}
