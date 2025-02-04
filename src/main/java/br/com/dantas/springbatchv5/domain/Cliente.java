package br.com.dantas.springbatchv5.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Range;

public class Cliente {
	@NotNull
	@Size(min = 1, max = 100)
	@Pattern(regexp = "[a-zA-Z\\s]+", message = "Nome deve ser alfabético")
	private String nome;
	@NotNull
	@Range(min = 18, max = 200)
	private Integer idade;
	@NotNull
	@Size(min = 1, max = 50)
	@Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$", message = "Email inválido")
	private String email;
	private Double faixaSalarial;

	private ContaBancaria conta;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Double getFaixaSalarial() {
		return faixaSalarial;
	}

	public void setFaixaSalarial(Double faixaSalarial) {
		this.faixaSalarial = faixaSalarial;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ContaBancaria getConta() {
		return conta;
	}

	public void setConta(ContaBancaria conta) {
		this.conta = conta;
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"nome='" + nome + '\'' +
				", idade=" + idade +
				", email='" + email + '\'' +
				", faixaSalarial=" + faixaSalarial +
				", conta=" + conta +
				'}';
	}
}
