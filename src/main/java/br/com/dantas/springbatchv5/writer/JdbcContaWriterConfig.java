package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.ContaBancaria;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Types;

@Configuration
public class JdbcContaWriterConfig {

    @Bean
    public JdbcBatchItemWriter<ContaBancaria> jdbcContaWriter(@Qualifier(value = "appBatchDataBase") DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<ContaBancaria>()
                .dataSource(dataSource)
                .sql("insert into conta (tipo,limite,cliente_id) values (?, ?, ?)")
                .itemPreparedStatementSetter((conta, ps) -> {

                    ps.setObject(1,conta.getTipoConta(), Types.OTHER);
                    ps.setDouble(2,conta.getLimite());
                    ps.setString(3, conta.getClienteId());

                })
                .build();
    }
}
