package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.FaturaCartaoCredito;
import br.com.dantas.springbatchv5.domain.Transacao;
import org.springframework.batch.item.*;

import java.util.Objects;

public class FaturaCartaoCreditoReader implements ItemStreamReader<FaturaCartaoCredito> {

    private ItemStreamReader<Transacao> itemStreamReaderDelegate;
    private Transacao transacaoAtual;

    public FaturaCartaoCreditoReader(ItemStreamReader<Transacao> itemStreamReaderDelegate) {
        this.itemStreamReaderDelegate = itemStreamReaderDelegate;
    }


    @Override
    public FaturaCartaoCredito read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (transacaoAtual == null)
            transacaoAtual = itemStreamReaderDelegate.read();

        FaturaCartaoCredito faturaCartaoCredito = null;
        Transacao transacao = transacaoAtual;
        transacaoAtual = null;

        if(transacao!=null){
           faturaCartaoCredito = FaturaCartaoCredito
                   .builder()
                   .cartaoCredito(transacao.getCartaoCredito())
                   .cliente(transacao.getCartaoCredito().getCliente())
                   .build();

           faturaCartaoCredito.getTransacoes().add(transacao);

           while(isTransacaoRelacionada(transacao))
               faturaCartaoCredito.getTransacoes().add(transacaoAtual); // OBJETO ATUALIZADO NA VERIFICACAO ACIMA LENDO O PROXIMO ITEM DO CHUNCK
        }

        return faturaCartaoCredito;
    }

    private boolean isTransacaoRelacionada(Transacao transacao) throws Exception {
        return peekNextTransacao() != null
                && Objects.equals(transacao.getCartaoCredito().getNumeroCartaoCredito(),
                                  transacaoAtual.getCartaoCredito().getNumeroCartaoCredito());
    }

    private Transacao peekNextTransacao() throws Exception {
        transacaoAtual = itemStreamReaderDelegate.read();
        return transacaoAtual;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        itemStreamReaderDelegate.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        itemStreamReaderDelegate.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        itemStreamReaderDelegate.close();
    }
}
