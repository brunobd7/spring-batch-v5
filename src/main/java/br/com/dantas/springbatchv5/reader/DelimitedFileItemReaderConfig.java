package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

//@Configuration
public class DelimitedFileItemReaderConfig {

    // TODO COME BACK AND SOLVE , PATH DID NOT SOLVED BY ENV VARIABLE AND ANNOTATION @Value("#{jobParameters['arquivoClientes']}") String customersFile
//    @StepScope
//    @Bean
    public FlatFileItemReader<Cliente> delimitedFileItemReader() {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("delimitedFileItemReader")
                .resource(new PathResource("files/clientes_delimited.txt"))
                .delimited() // DEFINE THE TYPE OF FLAT FILE WILL BE READ
                .names(new String[] { "nome", "sobrenome", "idade", "email" })
                .targetType(Cliente.class)
                .build();
    }
}
