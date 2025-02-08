package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompositeContaWriterConfig {

    @Bean
    public CompositeItemWriter<Cliente> compositeItemWriter(FlatFileItemWriter<Cliente> flatFileItemWriter,
                                                            JdbcBatchItemWriter<Cliente> jdbcBatchItemWriter) {
        return new CompositeItemWriterBuilder<Cliente>()
                .delegates(flatFileItemWriter, jdbcBatchItemWriter)
                .build();
    }
}
