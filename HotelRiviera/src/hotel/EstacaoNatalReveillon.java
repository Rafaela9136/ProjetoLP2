package hotel;

import java.util.*;

/**
 * A EstacaoNatalReveillon e uma estacao de alta que inicia no dia 15 de Dezembro e termina no dia 5 de Janeiro.
 * Quem se hospedar no hotel durante esse periodo tera um acrescimo de 20% nas despesas totaais do hotel.
 *
 */
public final class EstacaoNatalReveillon implements Estacao {

	private final int DIA_INICIO = 15;
	private final int DIA_FIM = 5;
	private double porcentagemAAplicar = 1.2; // 20% a mais no valor das
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
				Calendar.DECEMBER, this.DIA_INICIO);
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataFim() {
		return new GregorianCalendar(Calendar.getInstance().YEAR,
				Calendar.JANUARY, this.DIA_FIM);
	}
}
