package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.Pessoa;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Timestamp;

@Configuration
public class PessoaJdbcItemWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Pessoa> pessoaJdbcItemWriter(@Qualifier("appDataSource") DataSource appDataSource) {
        return new JdbcBatchItemWriterBuilder<Pessoa>()
                .dataSource(appDataSource)
                .sql("INSERT INTO pessoa VALUES (?,?,?,?,?)")
                .itemPreparedStatementSetter((entityPessoa, ps) -> {
                    ps.setString(1,  entityPessoa.getNome());
                    ps.setString(2, entityPessoa.getEmail());
                    ps.setTimestamp(3, Timestamp.valueOf(entityPessoa.getDataNascimento().atStartOfDay()));
                    ps.setInt(4, entityPessoa.getIdade());
                })
                .build();
    }
}
