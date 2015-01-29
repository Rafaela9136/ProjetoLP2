package hotel;

import java.util.*;


public class EstrategiaNatalReveillon implements EstrategiaAplicavel{
	
	private Calendar dataInicio;
	private Calendar dataFim;
	private double porcentagemAAplicar = 1.2; // 20% a mais no valor das despesas totais do cliente.

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataInicio() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.DECEMBER, 15);
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataFim() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.JANUARY, 5);
	}

	@Override
	public double aplicaPorcentagem(double despesasTotais) {
		return despesasTotais * porcentagemAAplicar;
	}

}
