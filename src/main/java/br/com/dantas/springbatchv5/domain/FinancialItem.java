package br.com.dantas.springbatchv5.domain;


public class FinancialItem {

    private String transactionItem;
    private String transactionDate;
    private String transactionAmount;

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
        return "FinancialItem{" +
                "transactionItem='" + transactionItem + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", transactionAmount='" + transactionAmount + '\'' +
                '}';
    }
}
