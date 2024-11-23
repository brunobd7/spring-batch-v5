package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.Transacao;
import org.springframework.batch.item.*;

public class ClienteTransacaoFileReader implements ItemStreamReader<Cliente> {

    public Object currentObject;

    @Override
    public Cliente read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        // Read data case object is null
        if(currentObject == null) {
            currentObject =  // todo read from source
        }

        // Casting to Cliente.class and 'clean' for next data read
        Cliente cliente = (Cliente) currentObject;
        currentObject = null;

        // if the next object on read queue is and verify its type to add Transacao to Cliente array
        if(cliente != null) {

            while( peekNextItem() instanceof Transacao){
                cliente.getTransacoes().add((Transacao) currentObject);
            }
        }
        return cliente;
    }

    private Object peekNextItem() throws Exception {
        currentObject = // read next item on verification purpose
        return currentObject;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        ItemStreamReader.super.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        ItemStreamReader.super.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {

        ItemStreamReader.super.close();
    }
}
