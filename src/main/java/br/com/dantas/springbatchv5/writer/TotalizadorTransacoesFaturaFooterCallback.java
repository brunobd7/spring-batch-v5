package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FaturaCartaoCredito;
import org.springframework.batch.core.annotation.AfterChunk;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.List;

public class TotalizadorTransacoesFaturaFooterCallback implements FlatFileFooterCallback {

    private BigDecimal total = BigDecimal.ZERO;

    @Override // 3- JUST WRITE the bill total amount
    public void writeFooter(Writer writer) throws IOException {
        writer.append(String.format("\n%121s", "Total : ".concat(NumberFormat.getCurrencyInstance().format(total))));
    }

    @BeforeWrite // 2-  SUM ALL CREDIT CARDS TRANSACTIONS to BILL
    public void beforeWrite(Chunk<FaturaCartaoCredito> chunkFaturas){
        chunkFaturas.forEach( fatura -> total = total.add(fatura.getValorTotalFatura()));
    }

    @AfterChunk // 1- After process new chunk, set totalValue var to zero. Avoiding an inconsistency on transactions sum
    public void afterChunk (ChunkContext chunkContext){
        total = BigDecimal.ZERO;
    }

}
