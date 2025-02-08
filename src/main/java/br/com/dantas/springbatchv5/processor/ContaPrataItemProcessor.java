package br.com.dantas.springbatchv5.processor;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.ContaBancaria;
import br.com.dantas.springbatchv5.domain.TipoConta;
import org.springframework.batch.item.ItemProcessor;

public class ContaPrataItemProcessor implements ItemProcessor<Cliente, ContaBancaria> {

    @Override
    public ContaBancaria process(Cliente clienteSobProcessamento) throws Exception {
       ContaBancaria conta = new ContaBancaria();
		conta.setClienteId(clienteSobProcessamento.getEmail());
		conta.setTipoConta(TipoConta.PRATA);
		conta.setLimite(500.0);
		return conta;
    }
}
