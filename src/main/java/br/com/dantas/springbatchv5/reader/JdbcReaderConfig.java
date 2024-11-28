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

import javax.sql.DataSource;

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
