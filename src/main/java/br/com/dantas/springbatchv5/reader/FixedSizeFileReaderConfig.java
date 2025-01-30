
package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.transform.Range;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
public class FixedSizeFileReaderConfig {


    // TODO COME BACK AND SOLVE , PATH DID NOT SOLVED BY ENV VARIABLE AND ANNOTATION @Value("#{jobParameters['arquivoClientes']}") String customersFile
    @StepScope
    @Bean
    @Primary
    public FlatFileItemReader<Cliente> fixedSizeFileReader() throws IOException {
        return new FlatFileItemReaderBuilder<Cliente>()
                .name("fixedSizeFileReader")
                .resource(new PathResource("files/clientes_fixed_length.txt"))
                .fixedLength()
                .columns(new Range[]{
                        new Range(1, 10), new Range(11, 20), new Range(21, 23), new Range(24, 43)
                })
                .names( new String[]{"nome", "sobrenome", "idade", "email"})
                .targetType(Cliente.class)
                .build();
    }
}


