package hotel;

import java.util.*;

import excecoes.DataInvalidaException;
import excecoes.NomeVazioException;

public class Baba implements Servico {

	public static final double VALOR_HORA = 25;
	public static final double VALOR_HORA_DOBRADA = 50;
	public static final int INICIO_HORA_DOBRADA = 18; // entre 18h e 7h o preco
														// por hora da baba e
														// dobrado.
	public static final int FIM_HORA_DOBRADA = 7;
	private static final double MILISSEGUNDOS_EM_UMA_HORA = 3600000;

	private Calendar inicioDoServico;
	private Calendar terminoDoServico;
	private int numDeHoras;

	public Baba(Calendar inicioDoServico, Calendar terminoDoServico)
			throws NullPointerException {
		verificaData(inicioDoServico);
		verificaData(terminoDoServico);
		this.inicioDoServico = inicioDoServico;
		this.terminoDoServico = terminoDoServico;
		numDeHoras = 0;
	}// Construtor

	private void verificaData(Calendar data) {
		if (data == null)
			throw new NullPointerException();
	}

	public void setTerminoDoServico(Calendar novaDataTermino)
			throws NullPointerException {
		verificaData(novaDataTermino);
		terminoDoServico = novaDataTermino;
	}// setTerminoDoServico

	public void setInicioDoServico(Calendar novaDataInicio)
			throws NullPointerException {
		verificaData(novaDataInicio);
		inicioDoServico = novaDataInicio;
	}// setInicioDoServico

	@Override
	public double getPreco() {
		return calculaPreco();
	}// getPreco

	public Calendar getInicioDoServico() {
		return inicioDoServico;
	}// getInicioDoServico

	public Calendar getTerminoDoServico() {
		return terminoDoServico;
	}// getTerminoDoServico

	public int getNumeroDeHoras() {
		long tempoInicial = inicioDoServico.getTimeInMillis();
		long tempoFinal = terminoDoServico.getTimeInMillis();
		numDeHoras = (int) Math
				.round(((tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UMA_HORA));
		return numDeHoras;
	}// getNumeroDeDias

	private double calculaPreco() {
		double preco = 0;
		int hora = inicioDoServico.get(Calendar.HOUR_OF_DAY);

		for (int i = 0; i < getNumeroDeHoras(); i++) {
			if (verificaSeEHoraDobrada(hora))
				preco += VALOR_HORA_DOBRADA;
			else
				preco += VALOR_HORA;

			if (hora == 23)
				hora = 0;
			else
				hora++;
		}
		return preco;
	}// calculaPreco

	private boolean verificaSeEHoraDobrada(int hora) {
		if (hora >= INICIO_HORA_DOBRADA || hora <= FIM_HORA_DOBRADA) {
			return true;
		} else {
			return false;
		}
	}// verificaSeEHoraDobrada

	@Override
	public String toString() {
		return "SERVIÇO BABYSITTER"
				+ "Início do serviço: "
				+ inicioDoServico
				+ "Término do serviço: "
				+ terminoDoServico
				+ "Duração do serviço: "
				+ "Valor total do serviço: "
				+ getPreco()
				+ "OBS: Das 18 h às 7 horas o valor do serviço é cobrado em dobro.";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Baba)) {
			return false;
		}
		Baba outro = (Baba) obj;
		return (getInicioDoServico().equals(outro.getInicioDoServico()) && getInicioDoServico()
				.equals(outro.getTerminoDoServico()));
	}

}// Baba
