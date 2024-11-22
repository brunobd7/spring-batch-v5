package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DelimitedFileReaderWriterConfig {

    @Bean
    public ItemWriter<Cliente> fixedSizeFileWriter() {
        return items -> items.forEach(System.out::println);
    }
}
