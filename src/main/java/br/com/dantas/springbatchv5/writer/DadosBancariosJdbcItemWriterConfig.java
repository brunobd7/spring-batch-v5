package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.DadosBancarios;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DadosBancariosJdbcItemWriterConfig {

    @Bean
    public JdbcBatchItemWriter<DadosBancarios> dadosBancariosJdbcItemWriter(@Qualifier(value = "appDataSource") DataSource appDataSource) {
        return new JdbcBatchItemWriterBuilder<DadosBancarios>()
                .dataSource(appDataSource)
                .sql("INSERT INTO dados_bancarios VALUES (?,?,?,?)")
                .itemPreparedStatementSetter((entityDadoBancario, ps) -> {
                   ps.setInt(1,entityDadoBancario.getPessoaId());
                   ps.setInt(2,entityDadoBancario.getAgencia());
                   ps.setInt(3,entityDadoBancario.getConta());
                   ps.setInt(4,entityDadoBancario.getBanco());
                })
                .build();

    }
}
