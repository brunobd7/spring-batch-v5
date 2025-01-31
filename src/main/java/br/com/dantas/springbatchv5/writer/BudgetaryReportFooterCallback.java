package br.com.dantas.springbatchv5.writer;

import br.com.dantas.springbatchv5.domain.FinancialTransaction;
import org.springframework.batch.core.annotation.BeforeWrite;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Component
public class BudgetaryReportFooterCallback implements FlatFileFooterCallback {

    private Double totalAmount = 0D ;

    @Override
    public void writeFooter(Writer writer) throws IOException {

        writer.append(String.format("%n"));
        writer.append(String.format("--------------------------- REPORT FOOTER -------------------------------------%n"));
        writer.append(String.format("\t\t\t\t\t\t\t\t\t\t\t\t\tTOTAL AMOUNT => %s %n",
                NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR")).format(totalAmount))
        );
    }

    @BeforeWrite // SPRING COMPONENT LISTENER
    public void beforeWrite(List<FinancialTransaction> transactionList) {
        transactionList.forEach(transaction -> {
            totalAmount += transaction.getTotalItensAmount().doubleValue();
        });
    }

}
