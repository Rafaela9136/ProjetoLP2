package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A EstacaoSaoJoao é uma estacao de alta na cidade de Campina Grande, onde esta localizado o hotel Rivieira.
 * Quem se hospedar durante esse periodo  terá um aumento de 10% nas despesas totais no Hotel.
 * Mas se a estadia for entre 23 e 29 de junho, essa porcentagem aumenta para 50%.
 *
 */
public class EstacaoSaoJoao implements Estacao {

	private final int DIA_INICIO = 1;
	private final int DIA_FIM = 10;
	private double porcentagemAAplicar = 1.1; // 50% a mais no valor das
												// despesas totais do cliente.

	public double getPorcentagemAAplicar() {
		return porcentagemAAplicar;
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
