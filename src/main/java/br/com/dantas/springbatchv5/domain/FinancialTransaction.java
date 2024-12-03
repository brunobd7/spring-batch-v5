package br.com.dantas.springbatchv5.domain;

/*
* Os arquivos a serem lidos possuem o seguinte formato:
*
*  código da natureza da despesa, descrição da natureza de despesa,
*  descrição do item, data, valor**
* */
public class FinancialTransaction {

    private String transactionTypeId;
    private String transactionDescription;
    private String transactionItem;
    private String transactionDate;
    private String transactionAmount;


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

    public String getTransactionItem() {
        return transactionItem;
    }

    public void setTransactionItem(String transactionItem) {
        this.transactionItem = transactionItem;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    @Override
    public String toString() {
        return "FinancialTransactions{" +
                "transactionTypeId='" + transactionTypeId + '\'' +
                ", transactionDescription='" + transactionDescription + '\'' +
                ", transactionItem='" + transactionItem + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                '}';
    }
}
