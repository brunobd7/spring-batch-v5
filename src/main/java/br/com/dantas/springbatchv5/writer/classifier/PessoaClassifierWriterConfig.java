package br.com.dantas.springbatchv5.writer.classifier;

import br.com.dantas.springbatchv5.domain.Pessoa;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PessoaClassifierWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<Pessoa> pessoaClassifierWriter(
            @Qualifier(value = "pessoaJdbcItemWriter") JdbcBatchItemWriter<Pessoa> pessoaJdbcBatchItemWriter,
            @Qualifier(value = "pessoaInvalidaFlatFileItemWriter") FlatFileItemWriter<Pessoa> pessoaInvalidaWriter) {
        return new ClassifierCompositeItemWriterBuilder<Pessoa>()
                .classifier(pessoa -> {

                    if(pessoa.isValid()) {
                        return pessoaJdbcBatchItemWriter;
                    }else {
                        return pessoaInvalidaWriter;
                    }
                })
                .build();
    }

}
