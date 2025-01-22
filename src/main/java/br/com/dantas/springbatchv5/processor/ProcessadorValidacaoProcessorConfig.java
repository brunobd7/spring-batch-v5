package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.dominio.Cliente;
import br.com.dantas.springbatchv5.dominio.ContaBancaria;
import br.com.dantas.springbatchv5.dominio.TipoConta;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.builder.CompositeItemProcessorBuilder;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ProcessadorValidacaoProcessorConfig {

	@Bean
	@Primary
	ItemProcessor<Cliente, Cliente> procesadorValidacaoProcessor() throws Exception {
		return new CompositeItemProcessorBuilder<Cliente, Cliente>()
				.delegates(
						beanValidatingProcessor(),
						tipoContaValidatingItemProcessor()
				)
				.build();
	}

	private BeanValidatingItemProcessor<Cliente> beanValidatingProcessor() throws Exception {
		BeanValidatingItemProcessor<Cliente> processor = new BeanValidatingItemProcessor<>();
		processor.setFilter(true);
		processor.afterPropertiesSet();
		return processor;
	}

	private ValidatingItemProcessor<Cliente> tipoContaValidatingItemProcessor() {
		ValidatingItemProcessor<Cliente> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		processor.setFilter(true);
		return processor;
	}

	private Validator<Cliente> validator() {
		return new Validator<Cliente>() {

			@Override
			public void validate(Cliente cliente) throws ValidationException {

				ContaBancaria conta = new ContaBancaria();

				if(cliente.getFaixaSalarial() <= 3000D) {
					conta.setClienteId(cliente.getEmail());
					conta.setLimite(500D);
					conta.setTipoConta(TipoConta.PRATA);
				}
				if(cliente.getFaixaSalarial() > 3000D && cliente.getFaixaSalarial() <= 5000D) {
					conta.setClienteId(cliente.getEmail());
					conta.setLimite(1000D);
					conta.setTipoConta(TipoConta.OURO);
				}
				if(cliente.getFaixaSalarial() > 5000D && cliente.getFaixaSalarial() <= 10000D) {
					conta.setClienteId(cliente.getEmail());
					conta.setLimite(2500D);
					conta.setTipoConta(TipoConta.PLATINA);
				}
				if(cliente.getFaixaSalarial() > 10000D) {
					conta.setClienteId(cliente.getEmail());
					conta.setLimite(5000D);
					conta.setTipoConta(TipoConta.DIAMANTE);
				}

				cliente.setContaBancaria(conta);
			}

		};
	}
}
