package br.com.dantas.springbatchv5.domain;

import java.util.Objects;

public class Transacao {

    private String id;
    private String descricao;
    private String valor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "id='" + id + '\'' +
                ", descricao='" + descricao + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
