package hotel;

import java.io.Serializable;

/**
 * Enum contendo a quantidade de cada tipo de quarto do hotel.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public enum QuantQuartos implements Serializable {
	EXECUTIVO_SIMPLES(5), EXECUTIVO_DUPLO(15), EXECUTIVO_TRIPLO(20), LUXO_SIMPLES(
			5), LUXO_DUPLO(15), LUXO_TRIPLO(20), PRESIDENCIAL(5);

	private int quantidade;

	private QuantQuartos(int quantidade) {
		this.quantidade = quantidade;
	}// Construtor

	/**
	 * Recupera a quantidade de quartos do tipo que ha no hotel.
	 * 
	 * @return Quantidade de quartos do tipo que ha no hotel.
	 */
	public int getQuantidade() {
		return quantidade;
	}// getQuantidade
}// QuantQuartos
