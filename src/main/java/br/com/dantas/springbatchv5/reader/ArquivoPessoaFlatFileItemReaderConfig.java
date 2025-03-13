package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Pessoa;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ArquivoPessoaFlatFileItemReaderConfig {

    @Bean
    public FlatFileItemReader<Pessoa> arquivoPessoaFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<Pessoa>()
                .name("arquivoPessoaFlatFileItemReader")
                .resource( new FileSystemResource("C:\\Users\\Bruno\\IdeaProjects\\spring-batch-v5\\files\\pessoas.csv"))
                .delimited()
                .names("nome","email", "data_nascimento","idade","id")
                .addComment("--") // PATTERN OF COMMENTED LINES ON FILE
                .fieldSetMapper(fieldSet -> {
                    Pessoa pessoa = new Pessoa();
                    pessoa.setNome(fieldSet.readString("nome"));
                    pessoa.setEmail(fieldSet.readString("email"));
                    pessoa.setDataNascimento(
                            LocalDateTime.parse(fieldSet.readString("data_nascimento"),
                                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
                    );
                    pessoa.setIdade(fieldSet.readInt("idade"));
                    return pessoa;
                })
                .build();

    }
}
