package br.com.dantas.springbatchv5.domain;

public class ContaBancaria {

    private Integer id;
    private TipoConta tipoConta;
    private Double limite;
    private String clienteId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public Double getLimite() {
        return limite;
    }

    public void setLimite(Double limite) {
        this.limite = limite;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "ContaBancaria{" +
                "id=" + id +
                ", tipoConta=" + tipoConta +
                ", limite=" + limite +
                ", clienteId='" + clienteId + '\'' +
                '}';
    }
}
