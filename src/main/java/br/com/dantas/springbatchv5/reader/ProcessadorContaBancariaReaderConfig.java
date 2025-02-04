package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class ProcessadorContaBancariaReaderConfig {
	@StepScope
	@Bean
	JdbcCursorItemReader<Cliente> processadorContaBancariaReader(@Qualifier(value = "appBatchDataBase") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<Cliente>()
				.name("processadorContaBancariaReader")
				.dataSource(dataSource)
				.sql("select * from cliente")
				.rowMapper((rs, rowNum) -> {
					Cliente cliente = new Cliente();

					cliente.setNome(rs.getString("nome"));
					cliente.setIdade(rs.getInt("idade"));
					cliente.setEmail(rs.getString("email"));
					cliente.setFaixaSalarial(rs.getDouble("faixa_salarial"));
					return cliente;
				})
				.build();
	}
}
