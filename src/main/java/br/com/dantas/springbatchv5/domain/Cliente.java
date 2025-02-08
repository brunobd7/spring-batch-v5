package br.com.dantas.springbatchv5.domain;

public class Cliente {
	private String nome;
	private Integer idade;
	private String email;
	private Double faixaSalarial;

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

}
