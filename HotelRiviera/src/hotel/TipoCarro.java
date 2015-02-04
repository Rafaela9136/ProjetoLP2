package hotel;

public enum TipoCarro {
	LUXO(100), EXECUTIVO(60);

	private double preco;

	TipoCarro(double preco) {
		this.preco = preco;
	}

	public double getPreco() {
		return preco;
	}
}