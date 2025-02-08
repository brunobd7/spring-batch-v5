package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemProcessorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CriacaoContaProcessorConfig {

    @Bean
    public ItemProcessor<Cliente, ContaBancaria> leitorClientesProcessor() {
        return new ClassifierCompositeItemProcessorBuilder<Cliente, ContaBancaria>()
                .classifier(new GerarContaBancariaClassifier())
                .build();
    }
}
