package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.Cliente;
import br.com.dantas.springbatchv5.domain.Transacao;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class ClienteTransacaoFileReader implements ItemStreamReader<Cliente>, ResourceAwareItemReaderItemStream<Cliente> {

    public Object currentObject;

    // Using Delegate pattern to passa read resposibility to existing file reader;
    public FlatFileItemReader<Object> delegateReader;

    public ClienteTransacaoFileReader(FlatFileItemReader<Object> delegateReader) {
        this.delegateReader = delegateReader;
    }

    @Override
    public Cliente read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        // Read data case object is null
        if(currentObject == null) {
            currentObject = delegateReader.read();
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
        currentObject = delegateReader.read(); // read next item on verification purpose
        return currentObject;
    }

    @Override
    public void open(ExecutionContext executionContext) throws ItemStreamException {
        delegateReader.open(executionContext);
    }

    @Override
    public void update(ExecutionContext executionContext) throws ItemStreamException {
        delegateReader.update(executionContext);
    }

    @Override
    public void close() throws ItemStreamException {
        delegateReader.close();
    }

    @Override
    public void setResource(Resource resource) {
        delegateReader.setResource(resource);
    }
}
