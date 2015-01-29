package hotel;

import java.util.*;


public class EstrategiaNatalReveillon implements EstrategiaAplicavel{
	
	private final int DIA_INICIO = 15;
	private final int DIA_FIM = 5;
	
	
	private double porcentagemAAplicar = 1.2; // 20% a mais no valor das despesas totais do cliente.

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataInicio() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.DECEMBER, this.DIA_INICIO);
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataFim() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.JANUARY, this.DIA_FIM);
	}

	@Override
	public double aplicaPorcentagem(double despesasTotais) {
		return despesasTotais * porcentagemAAplicar;
	}

}
