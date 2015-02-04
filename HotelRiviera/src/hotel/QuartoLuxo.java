package hotel;

public class QuartoLuxo extends Quarto{
	
	private final double PRECO_LUXO_SIMPLES = 520.0;
	private final double PRECO_LUXO_DUPLO = 570.0;
	private final double PRECO_LUXO_TRIPLO = 620.0;
	
	public static final String DESCRICAO_EXECUTIVO = Quarto.DESCRICAO + " Os quartos do tipo luxo podem acomodar 1, 2 ou ate 3 hospedes."
			+ " Sao mais espacosos do que os executivos e contam com home theater. Camas extras para criancas menores de 9 anos sao "
			+ "permitidas em quartos do tipo simples e duplo quando o hospede solicitar na reserva.";
	
	private TiposDeQuarto tipoDeQuarto;
	 
	public QuartoLuxo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto) throws NullPointerException {
		super(temCamaExtra);
		if (tipoDeQuarto == null) {
			throw new NullPointerException();
		}
		this.tipoDeQuarto = tipoDeQuarto;
	}
	
	public TiposDeQuarto getTipoDeQuarto() {
		return tipoDeQuarto;
	}

	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return PRECO_LUXO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return PRECO_LUXO_DUPLO;
		} else {
			return PRECO_LUXO_TRIPLO;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuartoLuxo)) {
			return false;
		}	
		QuartoLuxo outro = (QuartoLuxo) obj;
		return super.equals(outro) && this.tipoDeQuarto.equals(outro.getTipoDeQuarto());
	}
	
	
}
