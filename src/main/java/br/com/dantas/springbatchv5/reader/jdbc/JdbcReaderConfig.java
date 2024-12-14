package br.com.dantas.springbatchv5.reader.jdbc;

import br.com.dantas.springbatchv5.domain.FinancialItem;
import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@Configuration
public class JdbcReaderConfig {

    @Bean
    public JdbcCursorItemReader<FinancialTransaction> financialItemJdbcCursorItemReader(@Qualifier("getAppDataSource") DataSource dataSource) {
        return new JdbcCursorItemReaderBuilder<FinancialTransaction>()
                .name("financialItemJdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select * from lancamento") // point to database mapped on datasource bean
//                .beanRowMapper(FinancialTransaction.class)
                .rowMapper((rs, rowNum) -> {
                    FinancialTransaction ft = new FinancialTransaction();
                    ft.setTransactionTypeId(rs.getObject(1, Integer.class).toString());
                    ft.setTransactionDescription(rs.getObject(2, String.class));

                    FinancialItem item = new FinancialItem();
                    item.setTransactionItem(rs.getObject(3, String.class));
                    item.setTransactionDate(rs.getObject(4, LocalDate.class).toString());
                    item.setTransactionAmount(rs.getObject(5, BigDecimal.class).toString());

                    ft.getFinancialItems().add(item);

                    return ft;
                })
                .build();
    }

}
