package br.com.dantas.springbatchv5.domain;

import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

public class Pessoa {

    private Integer id;
    private String nome;
    private String email;
    private LocalDateTime dataNascimento;
    private Integer idade;

    public Pessoa() {}

    public Pessoa(Integer id, String nome, String email, LocalDateTime dataNascimento, Integer idade) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public boolean isValid() {
        return StringUtils.hasText(this.getNome()) && StringUtils.hasText(this.getEmail()) && this.getDataNascimento() != null;
    }

}
