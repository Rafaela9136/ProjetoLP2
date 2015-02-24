package hotel;

public enum QuantQuartos {
	EXECUTIVO_SIMPLES(5), EXECUTIVO_DUPLO(15), EXECUTIVO_TRIPLO(20),
	LUXO_SIMPLES(5), LUXO_DUPLO(15), LUXO_TRIPLO(20), PRESIDENCIAL(5);
	
	private int quantidade;
	
	private QuantQuartos(int quantidade) {
	
	}// Construtor
}// QuantQuartos
