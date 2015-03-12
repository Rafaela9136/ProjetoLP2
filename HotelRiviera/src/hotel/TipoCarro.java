package hotel;

import java.io.Serializable;

/**
 * Enum contendo os tipos de carros e seus respectivos precos que ha no hotel.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public enum TipoCarro implements Serializable {
	LUXO("Luxo", 100), EXECUTIVO("Executivo", 60);

	private String nome;
	private double preco;

	private TipoCarro(String nome, double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	/**
	 * Recupera o nome do carro.
	 * 
	 * @return Nome do carro.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Recuper ao preco do carro.
	 * 
	 * @return Preco do carro.
	 */
	public double getPreco() {
		return preco;
	}
}