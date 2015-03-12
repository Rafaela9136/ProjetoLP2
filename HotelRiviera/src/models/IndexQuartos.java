package models;

import java.io.Serializable;

public enum IndexQuartos implements Serializable {
	EXECUTIVO_SIMPLES(5, "Executivo Simples"), EXECUTIVO_DUPLO(15,
			"Executivo Duplo"), EXECUTIVO_TRIPLO(20, "Executivo Triplo"), LUXO_SIMPLES(
			5, "Luxo Simples"), LUXO_DUPLO(15, "Luxo Duplo"), LUXO_TRIPLO(20,
			"Luxo Triplo"), SUITE_PRESIDENCIAL(5, "Suite Presidencial");

	private int quantidade;
	private String nome;

	private IndexQuartos(int quantidade, String nome) {
		this.quantidade = quantidade;
		this.nome = nome;
	}// construtor

	public int getQuantidade() {
		return quantidade;
	}// getQuantidade

	public String getNome() {
		return nome;
	}// getNome
}// IndexQuartosDesocupados
