package hotel;

import java.io.Serializable;

/**
 * Enum contendo os indices do array que possui a quantidade de quartos desocupados. Possui tambem o nome dos quartos e
 * a quantidade que ha de cada quarto no hotel.
 * @author Grupinho da Alegria
 *
 */
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
	
	/**
	 * Recupera a quantidade de quartos desse tipo no hotel.
	 * @return Quantidade de quartos desse tipo no hotel.
	 */
	public int getQuantidade() {
		return quantidade;
	}// getQuantidade
	
	/**
	 * Recupera o nome do quarto.
	 * @return Nome do quarto.
	 */
	public String getNome() {
		return nome;
	}// getNome
}// IndexQuartosDesocupados
