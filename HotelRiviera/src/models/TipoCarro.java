package models;

import java.io.Serializable;

public enum TipoCarro implements Serializable {
	LUXO("Luxo", 100), EXECUTIVO("Executivo", 60);

	private String nome;
	private double preco;

	TipoCarro(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}
	
	public double getPreco() {
		return preco;
	}
}