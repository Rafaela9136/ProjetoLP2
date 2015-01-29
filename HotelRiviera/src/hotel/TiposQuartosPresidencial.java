package hotel;

public enum TiposQuartosPresidencial implements TipoDeQuarto{
	PRESIDENCIAL(1200, false, "Presidencial");
	
	private double preco;
	private boolean podeCamaExtra;
	private String nome;
	
	TiposQuartosPresidencial(double preco, boolean podeCamaExtra, String nome) {
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
}// TiposQuartosPresidencial
