package hotel;

public enum Estrategias {
	JANEIRO(1.2), CARANAVAL(1.1),
	MARCO(0.9), ABRIL(0.8),
	MAIO(1.05), SAO_JOAO(1.4),
	JULHO(0.95), AGOSTO(0.85),
	SETEMBRO(0.90), OUTUBRO(0.70),
	NOVEMBRO(1.05), NATAL_REVEILION(1.5);
	
	private double porcentagem;
	
	Estrategias(double porcentagem) {
		this.porcentagem = porcentagem;
	}// Construtor
	
	public double getPorcentagem() {
		return porcentagem;
	}// getPorcentagem

}// Estrategias
