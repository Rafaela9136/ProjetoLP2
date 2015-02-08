package hotel;

import excecoes.CamaExtraException;

public class QuartoLuxo extends Quarto{
	
	public static final double DIARIA_LUXO_SIMPLES = 520.0;
	public static final double DIARIA_LUXO_DUPLO = 570.0;
	public static final double DIARIA_LUXO_TRIPLO = 620.0;
	
	public static final String DESCRICAO_EXECUTIVO = Quarto.DESCRICAO + " Os quartos do tipo luxo podem acomodar 1, 2 ou ate 3 hospedes."
			+ " Sao mais espacosos do que os executivos e contam com home theater. Camas extras para criancas menores de 9 anos sao "
			+ "permitidas em quartos do tipo simples e duplo quando o hospede solicitar na reserva.";
	
	private TiposDeQuarto tipoDeQuarto;
	 
	public QuartoLuxo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto) throws NullPointerException, CamaExtraException {
		super(temCamaExtra);
		if (tipoDeQuarto == null) {
			throw new NullPointerException();
		} else if(tipoDeQuarto.isPERMITE_CAMA_EXTRA())
			throw new CamaExtraException();
		this.tipoDeQuarto = tipoDeQuarto;
	}
	
	public TiposDeQuarto getTipoDeQuarto() {
		return tipoDeQuarto;
	}

	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return DIARIA_LUXO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return DIARIA_LUXO_DUPLO;
		} else {
			return DIARIA_LUXO_TRIPLO;
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
