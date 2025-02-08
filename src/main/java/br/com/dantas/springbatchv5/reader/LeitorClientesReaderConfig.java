package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class LeitorClientesReaderConfig {

    @Bean
    public JdbcCursorItemReader<Cliente> leitorClientesReader( @Qualifier(value = "appBatchDataBase") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<Cliente>()
                .name("leitorClientesReader")
                .dataSource(dataSource)
                .sql("select * from cliente")
                .rowMapper(new BeanPropertyRowMapper<>(Cliente.class))
                .build();

    }
}
