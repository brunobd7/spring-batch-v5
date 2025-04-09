package br.com.dantas.springbatchv5.domain;

public class InteresseClienteProduto {

    private Cliente cliente;
    private Produto produto;

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
