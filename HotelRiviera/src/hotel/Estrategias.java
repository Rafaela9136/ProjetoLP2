package hotel;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public enum Estrategias implements Serializable {
	SAO_JOAO_PREMIUM("Sao Joao Premium +50%", 23, 29, Calendar.JUNE,
			Calendar.JUNE, 1.5, Calendar.getInstance().YEAR),
	SAO_JOAO("Sao Joao +10%", 23, 29, Calendar.JUNE, Calendar.JUNE, 1.1,
			Calendar.getInstance().YEAR),
	NATAL_REVEILLON("Natal/Reveillon 20%", 15, 5, Calendar.DECEMBER, Calendar.JANUARY,
			1.2, Calendar.getInstance().YEAR + 1),
	AGOSTO("Mes Agosto -20%", 1, 31, Calendar.AUGUST, Calendar.AUGUST, 0.8, Calendar.getInstance().YEAR),
	// O mes de agosto inteiro e de baixa estacao (20% de desconto).
	DEFAULT("Sem estrategia +0%", 1, 31, Calendar.JANUARY, Calendar.DECEMBER,
			0, Calendar.getInstance().YEAR);

	private String nomeEstrategia;
	private Calendar dataInicial;
	private Calendar dataFinal;
	private double porcentagem;

	private Estrategias(String nomeEstrategia, int diaInicio, int diaFim,
			int mesInicio, int mesFim, double porcentagem, int anoFinal) {
		this.nomeEstrategia = nomeEstrategia;
		this.dataInicial = new GregorianCalendar(Calendar.getInstance().YEAR,
				mesInicio, diaInicio);
		this.dataFinal = new GregorianCalendar(anoFinal, mesFim, diaFim, 23, 59);
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
		return nomeEstrategia + "\nData Inicial da Estrategia: "
				+ dataInicial.get(Calendar.DAY_OF_MONTH)
				+ "/" + (dataInicial.get(Calendar.MONTH) + 1) 
				+ "/" + dataInicial.get(Calendar.YEAR) 
				+ "\nData Final Estrategia: " 
				+ dataFinal.get(Calendar.DAY_OF_MONTH)
				+ "/" + (dataFinal.get(Calendar.MONTH) + 1) 
				+ "/" + dataFinal.get(Calendar.YEAR);
	}// toString

}// Estrategias
