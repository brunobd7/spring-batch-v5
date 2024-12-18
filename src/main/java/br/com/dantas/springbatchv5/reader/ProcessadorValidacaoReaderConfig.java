package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.dominio.Cliente;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class ProcessadorValidacaoReaderConfig {
	@StepScope
	@Bean
	FlatFileItemReader<Cliente> processadorValidacaoReader(
			@Value("file:files/clientes.txt") Resource arquivoClientes) {
		return new FlatFileItemReaderBuilder<Cliente>()
				.name("processadorValidacaoReader")
				.resource(arquivoClientes)
				.delimited()
				.names("nome", "idade", "email")
				.targetType(Cliente.class)
				.build();
	}
}
