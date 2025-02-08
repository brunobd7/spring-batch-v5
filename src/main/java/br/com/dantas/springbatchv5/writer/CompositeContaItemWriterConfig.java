package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.CompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CompositeContaItemWriterConfig {

    @Bean
    public CompositeItemWriter<ContaBancaria> compositeContaWriter(
            JdbcBatchItemWriter<ContaBancaria> jdbcContaWriter,
            @Qualifier(value = "contasValidasFileWriter") FlatFileItemWriter<ContaBancaria> fileContaWriter
    ){
        return new CompositeItemWriterBuilder<ContaBancaria>()
                .delegates(jdbcContaWriter, fileContaWriter)
                .build();
    }
}
