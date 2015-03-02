package hotel;

import java.io.Serializable;

public enum IndexQuartos implements Serializable {
	EXECUTIVO_SIMPLES(5), EXECUTIVO_DUPLO(15), EXECUTIVO_TRIPLO(20),
	LUXO_SIMPLES(5), LUXO_DUPLO(15), LUXO_TRIPLO(20),
	SUITE_PRESIDENCIAL(5);
	
	private int quantidade;
	
	private IndexQuartos(int quantidade) {
		this.quantidade = quantidade;
	}// construtor
	
	public int getQuantidade() {
		return quantidade;
	}// getQuantidade
}// IndexQuartosDesocupados
