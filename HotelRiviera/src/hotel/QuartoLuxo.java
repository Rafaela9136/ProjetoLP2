package hotel;

import java.util.Calendar;

import excecoes.DataInvalidaException;

public class QuartoLuxo extends Quarto {

	public static final double PRECO_LUXO_SIMPLES = 520.0;
	public static final double PRECO_LUXO_DUPLO = 570.0;
	public static final double PRECO_LUXO_TRIPLO = 620.0;
	
	public static final String DESCRICAO_EXECUTIVO = Quarto.DESCRICAO
			+ " Os quartos do tipo luxo podem acomodar 1, 2 ou ate 3 hospedes."
			+ " Sao mais espacosos do que os executivos e contam com home theater. Camas extras para criancas menores de 9 anos sao "
			+ "permitidas em quartos do tipo simples e duplo quando o hospede solicitar na reserva.";

	private TiposDeQuarto tipoDeQuarto;

	public QuartoLuxo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto,
			Calendar inicioServico, Calendar terminoServico)
			throws NullPointerException, DataInvalidaException {
		super(temCamaExtra, inicioServico, terminoServico);
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
		double precoDoQuarto;
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			precoDoQuarto =  PRECO_LUXO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			precoDoQuarto = PRECO_LUXO_DUPLO;
		} else {
			precoDoQuarto = PRECO_LUXO_TRIPLO;
		}// else
		return precoDoQuarto * getNumeroDeDias() + getFrigobar().getPreco();
	}// getPreco
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuartoLuxo)) {
			return false;
		}
		QuartoLuxo outro = (QuartoLuxo) obj;
		return super.equals(outro)
				&& this.tipoDeQuarto.equals(outro.getTipoDeQuarto());
	}

}
