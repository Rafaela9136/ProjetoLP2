package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EstrategiaSaoJoao implements EstrategiaAplicavel{
	
	private final int DIA_INICIO = 23;
	private final int DIA_FIM = 29;
	private double porcentagemAAplicar = 1.5; // 50% a mais no valor das despesas totais do cliente.
	
	
	public double getPorcentagemAAplicar() {
		return porcentagemAAplicar;
	}

	public void setPorcentagemAAplicar(double porcentagemAAplicar) {
		this.porcentagemAAplicar = porcentagemAAplicar;
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataInicio() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.JUNE, this.DIA_INICIO);
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataFim() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.JUNE, this.DIA_FIM);
	}

	@Override
	public double aplicaPorcentagem(double despesasTotais) {
		return despesasTotais * porcentagemAAplicar;
	}

}