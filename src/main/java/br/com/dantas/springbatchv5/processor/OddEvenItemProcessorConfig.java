package br.com.dantas.springbatchv5.processor;

import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OddEvenItemProcessorConfig {

    /**
     * The item processor receives an item of reader collection per time
     */
    @Bean
    public FunctionItemProcessor<Integer,String> oddEvenItemProcessor(){
        return new FunctionItemProcessor<Integer,String>(
                number -> number % 2 == 0
                        ? String.format("Item %s is ODD", number)
                        : String.format("Item %s is EVEN", number));
    }
}
