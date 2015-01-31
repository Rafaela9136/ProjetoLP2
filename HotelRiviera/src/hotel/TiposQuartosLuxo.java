package hotel;

public enum TiposQuartosLuxo implements TipoDeQuarto {
	LUXO_SIMPLES(520, true, "Luxo Simples"), LUXO_DUPLO(570, true, "Luxo Duplo"), LUXO_TRIPLO(
			620, false, "Luxo Triplo");

	private double preco;
	private boolean podeCamaExtra;
	private String nome;

	TiposQuartosLuxo(double preco, boolean podeCamaExtra, String nome) {
		this.preco = preco;
		this.podeCamaExtra = podeCamaExtra;
		this.nome = nome;
	}// Construtor

	@Override
	public double getPreco() {
		return preco;
	}// getPreco

	@Override
	public boolean getPodeCamaExtra() {
		return podeCamaExtra;
	}// getPodeCamaExtra

	@Override
	public String getNome() {
		return nome;
	}// getNome

}// TiposQuartosLuxo
