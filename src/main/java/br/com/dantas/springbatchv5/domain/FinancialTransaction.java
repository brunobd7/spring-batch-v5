package br.com.dantas.springbatchv5.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/*
* Os arquivos a serem lidos possuem o seguinte formato:
*
*  código da natureza da despesa, descrição da natureza de despesa,
*  descrição do item, data, valor**
* */
public class FinancialTransaction {
    private String transactionTypeId;
    private String transactionDescription;
    private List<FinancialItem> financialItems = new ArrayList<>();

    private FinancialItem tmpItem;

    public String getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(String transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

    public List<FinancialItem> getFinancialItems() {
        return financialItems;
    }

    public void setFinancialItems(List<FinancialItem> financialItems) {
        this.financialItems = financialItems;
    }

    public FinancialItem getTmpItem() {
        return tmpItem;
    }

    public void setTmpItem(FinancialItem tmpItem) {
        this.tmpItem = tmpItem;
    }

    public BigDecimal getTotalItensAmount(){
        return this.getFinancialItems().stream()
                .map(financialItem -> new BigDecimal(financialItem.getTransactionAmount()))
                .reduce(BigDecimal::add).get();
    }

    @Override
    public String toString() {
        return "FinancialTransaction{" +
                "transactionTypeId='" + transactionTypeId + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", financialItems=" + financialItems +
                ", tmpItem=" + tmpItem +
                '}';
    }
}
