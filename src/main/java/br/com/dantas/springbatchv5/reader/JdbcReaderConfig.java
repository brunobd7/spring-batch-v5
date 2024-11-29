package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Customer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class JdbcReaderConfig {


    @Bean
    @Primary
    public JdbcCursorItemReader<Customer> jdbcCursorItemReader(@Qualifier(value="appDataSource") DataSource appDataSource) {
        return new JdbcCursorItemReaderBuilder<Customer>()
                .name("jdbcCursorItemReader")
                .dataSource(appDataSource)
                .sql("select * from customers")
                .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
                .build();

    }

    @Bean
    public JdbcCursorItemReader<Customer> jdbcCursorItemReaderWithSkipExceptionHandle(@Qualifier(value="appDataSource") DataSource appDataSource) {
        return new JdbcCursorItemReaderBuilder<Customer>()
                .name("jdbcCursorItemReaderWithSkipExceptionHandle")
                .dataSource(appDataSource)
                .sql("select * from customers")
                .rowMapper((rs, rowNum) -> {

                    if(rs.getRow() == 10 || rs.getRow() == 11)  // INDUCING EXCEPTION TO TEST SKIP LIMIT SCENARIOS
                        throw new SQLException(String.format("Execution finished - Invalid customer %s", rs.getString("email") ));

                    Customer customer = new Customer();
                    customer.setNome(rs.getString("nome"));
                    customer.setSobrenome(rs.getString("sobrenome"));
                    customer.setIdade(rs.getString("idade"));
                    customer.setEmail(rs.getString("email"));
                    return customer;
                })
                .build();

    }

    /** WE COULD CREATE A QUERY PROVIDER BEAN TO SEPARATE RESPONSIBILITY TO OTHER BEAN
     * OR USE THE FIELD PROVIDE BY THE BUILDER TO CENTRALIZE SQL CLAUSES ON ONE BEAN
    */
    @Bean
    public JdbcPagingItemReader<Customer> jdbcPagingItemReader(@Qualifier(value="appDataSource") DataSource appDataSource, PagingQueryProvider pagingQueryProvider) {
        return new JdbcPagingItemReaderBuilder<Customer>()
                .name("jdbcPagingItemReader")
                .dataSource(appDataSource)
                .queryProvider(pagingQueryProvider)
                .pageSize(2)
                .rowMapper(new BeanPropertyRowMapper<>(Customer.class))
                .build();
    }

    @Bean
    public SqlPagingQueryProviderFactoryBean queryProvider(@Qualifier(value="appDataSource") DataSource dataSource) {

        SqlPagingQueryProviderFactoryBean queryProvider = new SqlPagingQueryProviderFactoryBean();
        queryProvider.setDataSource(dataSource);
        queryProvider.setSelectClause("select *");
        queryProvider.setFromClause("from customers");
        queryProvider.setSortKey("email");

        return queryProvider;
    }
}
