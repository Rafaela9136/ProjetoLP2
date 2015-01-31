package hotel;

public enum TipoCarro {
	LUXO(100), EXECUTIVO(60);

	private double preco;

	TipoCarro(double preco) {
		this.preco = preco;
	}// Construtor

	public double getPreco() {
		return preco;
	}// getPreco

	public void somaPreco(double adicional) {
		preco += adicional;
	}// somaPreco

}// TipoCarro
