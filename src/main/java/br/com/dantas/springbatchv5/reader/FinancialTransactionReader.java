package br.com.dantas.springbatchv5.reader;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.item.*;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class FinancialTransactionReader implements ResourceAwareItemReaderItemStream<FinancialTransaction> {

    private final JdbcCursorItemReader<FinancialTransaction> delegateReader;
    private FinancialTransaction currentFinancialTransaction;

    public FinancialTransactionReader(JdbcCursorItemReader<FinancialTransaction> delegateReader) {
        this.delegateReader = delegateReader;
    }

    @Override
    public void setResource(Resource resource) {
//        delegateReader.setResource(resource); // REMOVE COMMENT TO READ FROM FILE
    }

    @Override
    public FinancialTransaction read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        if (currentFinancialTransaction == null)
            currentFinancialTransaction = delegateReader.read();

        FinancialTransaction transactionGroup = (FinancialTransaction) currentFinancialTransaction;
        currentFinancialTransaction = null;

        if(transactionGroup != null){
            FinancialTransaction nextRowTransaction = peekNextRowData();
            while(dataFromSameTransactionType(transactionGroup,nextRowTransaction)){
                transactionGroup.getFinancialItems().add(nextRowTransaction.getTmpItem());
                nextRowTransaction = peekNextRowData();
            }
            transactionGroup.getFinancialItems().add(transactionGroup.getTmpItem()); // ALWAYS CREATE 1*1 transaction x item
        }
        return transactionGroup;
    }

    private boolean dataFromSameTransactionType(FinancialTransaction currentTransaction, FinancialTransaction nextRowTransaction) {
        return nextRowTransaction != null
                &&
                nextRowTransaction.getTransactionTypeId().equals(currentTransaction.getTransactionTypeId());
    }

    private FinancialTransaction peekNextRowData() throws Exception {
        currentFinancialTransaction = delegateReader.read();
        return currentFinancialTransaction;
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
