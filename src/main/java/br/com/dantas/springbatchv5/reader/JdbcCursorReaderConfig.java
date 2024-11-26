package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Customer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;

@Configuration
public class JdbcCursorReaderConfig {


    @Bean
    public JdbcCursorItemReader<Customer> jdbcCursorItemReader(@Qualifier(value="appDataSource") DataSource appDataSource) {
        return new JdbcCursorItemReaderBuilder<Customer>()
                .name("jdbcCursorItemReader")
                .dataSource(appDataSource)
                .sql("select * from customers")
                .rowMapper(new BeanPropertyRowMapper<Customer>(Customer.class))
                .build();

    }
}
