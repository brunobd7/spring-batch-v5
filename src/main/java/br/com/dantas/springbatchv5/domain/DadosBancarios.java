package br.com.dantas.springbatchv5.domain;


public class DadosBancarios {

    private Integer id;
    private Integer pessoaId;
    private Integer agencia;
    private Integer conta;
    private Integer banco;

    public DadosBancarios() {}

    public DadosBancarios(Integer id, Integer pessoaId, Integer agencia, Integer conta, Integer banco) {
        this.id = id;
        this.pessoaId = pessoaId;
        this.agencia = agencia;
        this.conta = conta;
        this.banco = banco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(Integer pessoaId) {
        this.pessoaId = pessoaId;
    }

    public Integer getAgencia() {
        return agencia;
    }

    public void setAgencia(Integer agencia) {
        this.agencia = agencia;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public Integer getBanco() {
        return banco;
    }

    public void setBanco(Integer banco) {
        this.banco = banco;
    }
}
