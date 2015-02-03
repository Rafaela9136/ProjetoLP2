package hotel;

public class QuartoExecutivo extends Quarto{
	
	private TiposDeQuarto tipoDeQuarto;
	private final double PRECO_EXECUTIVO_SIMPLES = 360.0;
	private final double PRECO_EXECUTIVO_DUPLO = 385.0;
	private final double PRECO_EXECUTIVO_TRIPLO = 440.0;
	 
	public QuartoExecutivo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto) {
		super(temCamaExtra);
		this.tipoDeQuarto = tipoDeQuarto;
	}

	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return preco = PRECO_EXECUTIVO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return preco = PRECO_EXECUTIVO_DUPLO;
		} else {
			return preco = PRECO_EXECUTIVO_TRIPLO;
		}
	}
	
}
