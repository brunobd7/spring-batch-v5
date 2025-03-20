package br.com.dantas.springbatchv5.writer;

import org.springframework.batch.item.file.FlatFileFooterCallback;

import java.io.IOException;
import java.io.Writer;

public class TotalizadorTransacoesFaturaFooterCallback implements FlatFileFooterCallback {

    @Override
    public void writeFooter(Writer writer) throws IOException { // TODO IMPLEMENTS TOTALIZATION LOGIC

    }
}
