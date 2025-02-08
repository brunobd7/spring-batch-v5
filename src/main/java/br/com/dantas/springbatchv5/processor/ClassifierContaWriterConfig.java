package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClassifierContaWriterConfig {

    @Bean
    public ClassifierCompositeItemWriter<ContaBancaria> classifierContaWriter(
            @Qualifier(value = "clientesInvalidosFileWriter")FlatFileItemWriter<ContaBancaria> clienteInvalidoWriter,
            CompositeItemWriter<ContaBancaria> compositeContaWriter
            ) {
        return new ClassifierCompositeItemWriterBuilder<ContaBancaria>()
                .classifier(classificadorContas(compositeContaWriter, clienteInvalidoWriter))
                .build();
    }

    private Classifier<ContaBancaria, ItemWriter<? super ContaBancaria>> classificadorContas(CompositeItemWriter<ContaBancaria> compositeContaWriter,
                                                                                             FlatFileItemWriter<ContaBancaria> clienteInvalidoWriter) {
        return new Classifier<ContaBancaria, ItemWriter<? super ContaBancaria>>() {
            @Override
            public ItemWriter<? super ContaBancaria> classify(ContaBancaria contaBancaria) {
                if(contaBancaria.getTipoConta() != null ) {
                    return compositeContaWriter;
                }else {
                    return clienteInvalidoWriter;
                }
            }
        };
    }
}
