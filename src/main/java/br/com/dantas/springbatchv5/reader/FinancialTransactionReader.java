package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;

public class FinancialTransactionReader implements ResourceAwareItemReaderItemStream<FinancialTransaction> {

    private final FlatFileItemReader<FinancialTransaction> delegateReader;

    public FinancialTransactionReader(FlatFileItemReader<FinancialTransaction> delegateReader) {
        this.delegateReader = delegateReader;
    }


    @Override
    public void setResource(Resource resource) {
        delegateReader.setResource(resource);
    }

    @Override
    public FinancialTransaction read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        Object object = delegateReader.read();

        return (FinancialTransaction) object;
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
}
