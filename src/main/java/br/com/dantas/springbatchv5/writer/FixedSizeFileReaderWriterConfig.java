package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.PathResource;

//@Configuration
public class FixedSizeFileReaderWriterConfig {

    // @StepScope //We use just when our bean need to access jobParameters to get some metadata
//    @Bean
//    @Primary
    public FlatFileItemWriter<Cliente> fixedSizeFileWriter() {
        return new FlatFileItemWriterBuilder<Cliente>()
                .name("fixedSizeFileWriter")
                .resource(new PathResource("files/new_fixed_file_length_from_writer.txt")) // OUTPUT FILE
                .formatted()
                .format("%-9s %-9s %-2s %-19s") // STRING FORMAT
                .names("nome", "sobrenome", "idade", "email")
                .build();
    }

}
