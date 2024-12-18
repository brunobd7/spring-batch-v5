package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.dominio.Cliente;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProcessadorValidacaoWriterConfig {
	@Bean
	ItemWriter<Cliente> processadorValidacaoWriter() {
		return clientes -> clientes.forEach(System.out::println);
	}
}
