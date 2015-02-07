package hotel;

import java.util.*;

import excecoes.DataInvalidaException;

public class Baba implements Servico {

	public static final double VALOR_HORA = 25;
	public static final double VALOR_HORA_DOBRADA = 50;
	public static final int INICIO_HORA_DOBRADA = 18; // entre 18h e 7h o preco
														// por hora da baba e
														// dobrado.
	public static final int FIM_HORA_DOBRADA = 7;
	private static final double MILISSEGUNDOS_EM_UMA_HORA = 3600000;

	private Calendar dataInicio;
	private Calendar dataTermino;
	private int numDeHoras;

	public Baba(Calendar dataInicio, Calendar dataTermino)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataInicio, dataTermino);
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		numDeHoras = 0;
	}

	public void setTerminoDoServico(Calendar novaDataTermino)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataInicio, novaDataTermino);
		dataTermino = novaDataTermino;
	}

	public void setInicioDoServico(Calendar novaDataInicio)
			throws NullPointerException, DataInvalidaException {
		verificaData(novaDataInicio, dataTermino);
		dataInicio = novaDataInicio;
	}

	@Override
	public double getPreco() {
		double preco = 0;
		int hora = dataInicio.get(Calendar.HOUR_OF_DAY);

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
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public int getNumeroDeHoras() {
		long tempoInicial = dataInicio.getTimeInMillis();
		long tempoFinal = dataTermino.getTimeInMillis();
		numDeHoras = (int) Math
				.round(((tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UMA_HORA));
		return numDeHoras;
	}

	private boolean verificaSeEHoraDobrada(int hora) {
		if (hora >= INICIO_HORA_DOBRADA || hora < FIM_HORA_DOBRADA) {
			return true;
		} else {
			return false;
		}
	}

	private void verificaData(Calendar dataInicio, Calendar dataTermino)
			throws NullPointerException, DataInvalidaException {
		if (dataInicio == null || dataTermino == null)
			throw new NullPointerException();
		if (dataInicio.before(new GregorianCalendar())
				|| dataTermino.before(dataInicio))
			throw new DataInvalidaException();
	}

	@Override
	public String toString() {
		return "SERVICO BABYSITTER\n" + "Preco Total: R$ " + getPreco()
				+ "\nDuracao: " + getNumeroDeHoras() + " horas"
				+ "\nData Inicio: "
				+ getDataInicio().get(Calendar.DAY_OF_MONTH) + "/"
				+ (getDataInicio().get(Calendar.MONTH) + 1) + "/"
				+ getDataInicio().get(Calendar.YEAR) + " as "
				+ getDataInicio().get(Calendar.HOUR_OF_DAY) + ":"
				+ getDataInicio().get(Calendar.MINUTE) + "\nData Termino: "
				+ getDataTermino().get(Calendar.DAY_OF_MONTH) + "/"
				+ (getDataTermino().get(Calendar.MONTH) + 1) + "/"
				+ getDataTermino().get(Calendar.YEAR) + " as "
				+ getDataTermino().get(Calendar.HOUR_OF_DAY) + ":"
				+ getDataTermino().get(Calendar.MINUTE)
				+ "\nOBS: Das 18h as 7h o valor do servico e cobrado em dobro.";
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Baba)) {
			return false;
		}
		Baba outra = (Baba) obj;
		return (getPreco() == outra.getPreco()
				&& getDataInicio().equals(outra.getDataInicio()) && getDataTermino()
				.equals(outra.getDataTermino()));
	}

}// Baba
