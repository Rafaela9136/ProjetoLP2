package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EstacaoSaoJoaoPremium implements Estacao {
	private final int DIA_INICIO = 1;
	private final int DIA_FIM = 10;
	private double porcentagemAAplicar = 1.1; // 10% a mais no valor das
												// despesas totais do cliente.

	public double getPorcentagemAAplicar() {
		return porcentagemAAplicar;
	}

	public void setPorcentagemAAplicar(double porcentagemAAplicar) {
		this.porcentagemAAplicar = porcentagemAAplicar;
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataInicio() {
		return new GregorianCalendar(Calendar.getInstance().YEAR,
				Calendar.JUNE, this.DIA_INICIO);
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataFim() {
		return new GregorianCalendar(Calendar.getInstance().YEAR,
				Calendar.JULY, this.DIA_FIM);
	}

}
