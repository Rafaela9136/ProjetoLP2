package hotel;

public class QuartoExecutivo extends Quarto{
	
	private final double PRECO_EXECUTIVO_SIMPLES = 360.0;
	private final double PRECO_EXECUTIVO_DUPLO = 385.0;
	private final double PRECO_EXECUTIVO_TRIPLO = 440.0;
	
	public static final String DESCRICAO_EXECUTIVO = Quarto.DESCRICAO + " Os quartos do tipo executivo podem acomodar 1, 2 ou at� 3 h�spedes."
			+ "Camas extras para crian�as menores de 9 anos s�o permitidas em quartos do tipo simples e duplo quando o h�spede solicitar na reserva.";
	
	private TiposDeQuarto tipoDeQuarto;
		 
	public QuartoExecutivo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto) {
		super(temCamaExtra);
		this.tipoDeQuarto = tipoDeQuarto;
	}
		
	public TiposDeQuarto getTipoDeQuarto() {
		return tipoDeQuarto;
	}

	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return PRECO_EXECUTIVO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return PRECO_EXECUTIVO_DUPLO;
		} else {
			return PRECO_EXECUTIVO_TRIPLO;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuartoExecutivo)) {
			return false;
		}	
		QuartoExecutivo outro = (QuartoExecutivo) obj;
		return super.equals(outro) && this.tipoDeQuarto.equals(outro.getTipoDeQuarto());
	}

}
