package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.DadosBancarios;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ArquivoDadosBancariosFlatFileItemReaderConfig {

    @Bean
    public FlatFileItemReader<DadosBancarios> arquivoDadosBancariosFlatFileItemReader() {
        return  new FlatFileItemReaderBuilder<DadosBancarios>()
                .name("arquivoDadosBancariosFlatFileItemReader")
                .resource(new FileSystemResource("/files/dados_bancarios.csv"))
                .delimited()
                .names("pessoa_id","agencia","conta","banco","id")
                .addComment("--")
                .targetType(DadosBancarios.class)
                .build();
    }
}
