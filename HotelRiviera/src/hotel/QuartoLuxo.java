package hotel;

public class QuartoLuxo extends Quarto{
	
	private TiposDeQuarto tipoDeQuarto;
	private final double PRECO_LUXO_SIMPLES = 520.0;
	private final double PRECO_LUXO_DUPLO = 570.0;
	private final double PRECO_LUXO_TRIPLO = 620.0;
	 
	public QuartoLuxo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto) {
		super(temCamaExtra);
		this.tipoDeQuarto = tipoDeQuarto;
	}

	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return preco = PRECO_LUXO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return preco = PRECO_LUXO_DUPLO;
		} else {
			return preco = PRECO_LUXO_TRIPLO;
		}
	}
}
