package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A EstacaoSaoJoaoPremium é a estacao mais em alta do ano para o Hotel Rivieira. Ela se inicia em 23 de Juhno e
 * termina no dia 29 desse mesmo mes. Quam se hospedar durante esse periodo terá um acréscimo de 50% nas despesas totais do hotel.
 *
 */
public class EstrategiaSaoJoaoPremium implements EstrategiaAplicavel{
	private final int DIA_INICIO = 23;
	private final int DIA_FIM = 29;
	private double porcentagemAAplicar = 1.5; // 10% a mais no valor das
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
				Calendar.JUNE, this.DIA_FIM);
	}

}
