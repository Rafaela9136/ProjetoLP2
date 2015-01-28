package hotel;

public enum TipoDeQuarto {
	LUXO_SIMPLES(520, true), LUXO_DUPLO(570, true), LUXO_TRIPLO(620, false),
	EXECUTIVO_SIMPLES(360, true), EXECUTIVO_DUPLO(385, true), EXECUTIVO_TRIPLO(440, false),
	PRESIDENCIAL(1200, false);
	
	private double preco;
	private boolean podeCamaExtra;
	
	TipoDeQuarto(double preco, boolean podeCamaExtra) {
		this.preco = preco;
		this.podeCamaExtra = podeCamaExtra;
	}// Construtor
	
	public double getPreco() {
		return preco;
	}// getPreco
	
	public boolean getPodeCamaExtra() {
		return podeCamaExtra;
	}// getPodeCamaExtra
}// PrecoDeQuarto
