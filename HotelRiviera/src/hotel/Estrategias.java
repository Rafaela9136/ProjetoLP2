package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public enum Estrategias {
	SAO_JOAO_PREMIUM("Sao Joao Premium +10%%", 1, 10, Calendar.JUNE,
			Calendar.JUNE, 1.1, Calendar.getInstance().YEAR), SAO_JOAO(
			"Sao Joao +50%%", 23, 29, Calendar.JUNE, Calendar.JUNE, 1.5,
			Calendar.getInstance().YEAR), NATAL_REVEILLON(
			"Natal/Reveillon 20%%", 15, 5, Calendar.DECEMBER, Calendar.JANUARY,
			1.2, Calendar.getInstance().YEAR + 1);

	private String nomeEstrategia;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private double porcentagem;

	private Estrategias(String nomeEstrategia, int diaInicio, int diaFim,
			int mesInicio, int mesFim, double porcentagem, int anoFinal) {
		this.nomeEstrategia = nomeEstrategia;
		this.dataInicial = new GregorianCalendar(Calendar.getInstance().YEAR,
				mesInicio, diaInicio);
		this.dataFinal = new GregorianCalendar(Calendar.getInstance().YEAR,
				mesFim, diaFim);
		this.porcentagem = porcentagem;
	}// Construtor

	public String getNomeEstrategia() {
		return nomeEstrategia;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public double getPorcentagem() {
		return porcentagem;
	}

	@Override
	public String toString() {
		return "Estrategia=" + nomeEstrategia + "," + "DataInicial="
				+ dataInicial + "," + "DataFinal=" + dataFinal + ","
				+ "Porcentagem=" + porcentagem;
	}

}// Estrategias
