package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.dominio.Cliente;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.ScriptItemProcessorBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class ProcessadorScriptProcessorConfig {
	@Bean
	@Qualifier(value = "scriptingProcessor")
	ItemProcessor<Cliente, Cliente> processadorScriptProcessor() {
		return new ScriptItemProcessorBuilder<Cliente, Cliente>()
				.language("nashorn") // To run JS on openJDK is required use some version above JDK 15 or adjust to use other js engine as GraalVM JS Engine
				.scriptResource(
						new FileSystemResource("files/script.js"))
				.build();
	}
}
