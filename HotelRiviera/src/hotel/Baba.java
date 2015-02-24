package hotel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import excecoes.DataInvalidaException;

/**
 * Classe de um servico de Babysitter do Hotel. Pode ser contratado de acordo
 * com a disponibilidade das babas em um determinado periodo que vai ser
 * escolhido pelo cliente. O servico funciona 24hrs por dia e seu valor entre
 * 18:00 e 7:00 eh de R$ 50 e de R$ 25 nas demais horas do dia.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public class Baba implements Servico {
	public static final double VALOR_HORA = 25;
	public static final double VALOR_HORA_DOBRADA = 50;
	public static final int INICIO_HORA_DOBRADA = 18; // entre 18h e 7h o preco
														// por hora da baba e
														// dobrado.
	public static final int FIM_HORA_DOBRADA = 7;
	private static final int MILISSEGUNDOS_EM_UMA_HORA = 3600000;

	private Calendar dataCheckIn;
	private Calendar dataCheckOut;

	/**
	 * Cria o servico de Babysitter com uma data inicial e uma data de termino.
	 * 
	 * @param dataCheckIn
	 *            A data de inicio.
	 * @param dataCheckOut
	 *            A data de termino.
	 * @throws NullPointerException
	 *             Caso alguma das datas tenha valor nulo.
	 * @throws DataInvalidaException
	 *             Caso a data inicial seja antes da data atual ou a data de
	 *             termino seja antes da data de inicio.
	 */
	public Baba(Calendar dataCheckIn, Calendar dataCheckOut)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataCheckIn, dataCheckOut);
		this.dataCheckIn = dataCheckIn;
		this.dataCheckOut = dataCheckOut;
	}

	/**
	 * Retorna a data de inicio do servico.
	 * 
	 * @return A data inicial.
	 */
	@Override
	public Calendar getDataCheckIn() {
		return dataCheckIn;
	}

	/**
	 * Retorna data de termino do servico.
	 * 
	 * @return A data final.
	 */
	@Override
	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}

	/**
	 * Altera a data de termino do servico.
	 * 
	 * @param novaDataTermino
	 *            A nova data de termino.
	 * @throws NullPointerException
	 *             Caso a nova data tenha valor nulo.
	 * @throws DataInvalidaException
	 *             Caso a nova data de termino seja antes da data de inicio.
	 */
	public void setDataCheckOut(Calendar novaDataTermino)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataCheckIn, novaDataTermino);
		dataCheckOut = novaDataTermino;
	}

	/**
	 * Altera a data de inicio do servico.
	 * 
	 * @param novaDataInicio
	 *            A nova data de inicio.
	 * @throws NullPointerException
	 *             Caso a nova data tenha valor nulo.
	 * @throws DataInvalidaException
	 *             Caso a nova data de inicio seja antes da data atual ou seja
	 *             depois da data de termino.
	 */
	public void setDataCheckIn(Calendar novaDataInicio)
			throws NullPointerException, DataInvalidaException {
		verificaData(novaDataInicio, dataCheckOut);
		dataCheckIn = novaDataInicio;
	}

	/**
	 * Realiza o calculo do preco do servico de acordo com a quantidade de
	 * horas.
	 * 
	 * @return O preco final do servico.
	 */
	@Override
	public double getPreco() {
		double preco = 0;
		int hora = dataCheckIn.get(Calendar.HOUR_OF_DAY);

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

	/**
	 * Calcula e retorna a quantidade de horas do servico, a partir da diferenca
	 * em milissegundos da data final e data inicial.
	 * 
	 * @return O numero de horas do servico.
	 */
	public int getNumeroDeHoras() {
		long tempoInicial = dataCheckIn.getTimeInMillis();
		long tempoFinal = dataCheckOut.getTimeInMillis();
		int numDeHoras = (int) Math
				.ceil(((double) (tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UMA_HORA));
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
		if (dataInicio.equals(dataTermino)
				|| dataInicio.before(new GregorianCalendar())
				|| dataTermino.before(dataInicio))
			throw new DataInvalidaException();
	}

	/**
	 * Retorna uma representacao em String do servico contratado contendo o
	 * preco total, a duracao em horas, datas e horas finais e iniciais.
	 * 
	 * @return String contendo todas as informacoes do servico.
	 */
	@Override
	public String toString() {
		return "SERVICO BABYSITTER\n" + "Preco Total: R$ " + getPreco()
				+ "\nDuracao: " + getNumeroDeHoras() + " horas"
				+ "\nData Inicio: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataCheckIn.getTime())
				+ "\nData Termino: " + new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataCheckOut.getTime())
				+ "\nOBS: Das 18h as 7h o valor do servico e cobrado em dobro.";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCheckIn == null) ? 0 : dataCheckIn.hashCode());
		result = prime * result
				+ ((dataCheckOut == null) ? 0 : dataCheckOut.hashCode());
		return result;	
	}

	/**
	 * Verifica se dois servicos de Babas sao iguais, apartir de suas datas e
	 * precos.
	 * 
	 * @return True caso sejam iguais ou False caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Baba)) {
			return false;
		}
		Baba outra = (Baba) obj;
		return (getPreco() == outra.getPreco() 
				&& getDataCheckIn().equals(outra.getDataCheckIn()) && getDataCheckOut()
				.equals(outra.getDataCheckOut()));
	}
	
	@Override
	public String getNome() {
		return "Baba";
	}// getNome
	
}
