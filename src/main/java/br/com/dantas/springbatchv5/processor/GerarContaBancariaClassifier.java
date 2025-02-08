package br.com.dantas.springbatchv5.processor;


import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.ContaBancaria;
import br.com.dantas.springbatchv5.domain.TipoConta;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.classify.Classifier;

import java.util.HashMap;
import java.util.Map;

public class GerarContaBancariaClassifier implements Classifier<Cliente, ItemProcessor<?, ? extends ContaBancaria>> {

    //PATTERN TO AVOID IF's
    private static final Map<TipoConta,ItemProcessor<Cliente,ContaBancaria>> tipoContaprocessors = new HashMap<>(){
        {
            put(TipoConta.PRATA,new ContaPrataItemProcessor());
            put(TipoConta.OURO, new ContaOuroItemProcessor());
            put(TipoConta.PLATINA, new ContaPlatinaItemProcessor());
            put(TipoConta.DIAMANTE, new ContaDiamanteItemProcessor());
            put(TipoConta.INVALIDA, new ContaInvalidaItemProcessor());
        }
    };

    @Override
    public ItemProcessor<Cliente,ContaBancaria> classify(Cliente classifiableCliente) {
        TipoConta tipoConta = TipoConta.fromFaixaSalarial(classifiableCliente.getFaixaSalarial());
        return tipoContaprocessors.get(tipoConta);
    }
}
