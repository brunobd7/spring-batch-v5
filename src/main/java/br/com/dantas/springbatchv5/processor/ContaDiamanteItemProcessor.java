package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.ContaBancaria;
import br.com.dantas.springbatchv5.domain.TipoConta;
import org.springframework.batch.item.ItemProcessor;

public class ContaDiamanteItemProcessor implements ItemProcessor<Cliente, ContaBancaria> {

    @Override
    public ContaBancaria process(Cliente clienteSobProcessamento) throws Exception {
       ContaBancaria conta = new ContaBancaria();
		conta.setClienteId(clienteSobProcessamento.getEmail());
		conta.setTipoConta(TipoConta.DIAMANTE);
		conta.setLimite(5000.0);
		return conta;
    }
}
