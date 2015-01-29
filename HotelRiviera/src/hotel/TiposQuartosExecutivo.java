package hotel;

public enum TiposQuartosExecutivo implements TipoDeQuarto{
	EXECUTIVO_SIMPLES(360, true, "Executivo Simples"), 
	EXECUTIVO_DUPLO(385, true, "Executivo Duplo"), EXECUTIVO_TRIPLO(440, false, "Executivo Triplo");
	
	
	private double preco;
	private boolean podeCamaExtra;
	private String nome;
	
	TiposQuartosExecutivo(double preco, boolean podeCamaExtra, String nome) {
		this.preco = preco;
		this.podeCamaExtra = podeCamaExtra;
		this.nome = nome;
	}// Construtor
	
	public double getPreco() {
		return preco;
	}// getPreco
	
	public boolean getPodeCamaExtra() {
		return podeCamaExtra;
	}// getPodeCamaExtra
	
	public String getNome() {
		return nome;
	}// getNome
}// TiposQuartosExecutivo
