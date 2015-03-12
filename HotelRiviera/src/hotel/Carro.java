package hotel;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import excecoes.DataInvalidaException;

/**
 * Classe de um Carro que implementa uma interface Servico, pois representa o
 * servico de aluguel de um carro por um determinado tempo, podendo ser do tipo
 * Luxo ou Executivo e tambem ha a possibilidade de escolher Tanque Cheio e/ou
 * Seguro inclusos no servico.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public class Carro implements Servico, Serializable {
	public static final double VALOR_TANQUE_CHEIO = 150;
	public static final double VALOR_DO_SEGURO = 100;
	public static final double MILISSEGUNDOS_EM_UM_DIA = 86400000;

	private TipoCarro tipoDeCarro;
	private boolean isTanqueCheio;
	private boolean isAssegurado;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private int numDeDias;

	/**
	 * Cria um servico de aluguel de Carro, escolhendo um tipo (Luxo/Executivo),
	 * datas de inicio e termino do servico e se o cliente deseja Tanque Cheio
	 * e/ou Seguro.
	 * 
	 * @param tipoDeCarro
	 *            O tipo do carro a ser alugado, podendo apenas ser Luxo ou
	 *            Executivo.
	 * @param dataCheckIn
	 *            A data e hora de inicio do servico.
	 * @param dataCheckOut
	 *            A data e hora de termino do servico.
	 * @param isTanqueCheio
	 *            True para tanque cheio incluso, ou false caso contrario.
	 * @param isAssegurado
	 *            True para seguro incluso, ou false caso contrario.
	 * @throws NullPointerException
	 *             Caso receba valor nulo na criacao do contrato.
	 * @throws DataInvalidaException
	 *             Caso a data inicial inserida seja anterior a data atual ou a
	 *             data de termino seja anterior a data inicial.
	 */
	public Carro(TipoCarro tipoDeCarro, Calendar dataCheckIn,
			Calendar dataCheckOut, boolean isTanqueCheio, boolean isAssegurado)
			throws NullPointerException, DataInvalidaException {
		verificaTipo(tipoDeCarro);
		verificaData(dataCheckIn, dataCheckOut);
		this.tipoDeCarro = tipoDeCarro;
		this.isTanqueCheio = isTanqueCheio;
		this.isAssegurado = isAssegurado;
		this.dataCheckIn = dataCheckIn;
		this.dataCheckOut = dataCheckOut;
	}

	/**
	 * Mostra o tipo do carro que foi alugado.
	 * 
	 * @return LUXO ou EXECUTIVO.
	 */
	public TipoCarro getTipoDeCarro() {
		return tipoDeCarro;
	}

	/**
	 * Altera a data de inicio do servico.
	 * 
	 * @param novaDataInicio
	 *            Nova data e hora do inicio.
	 * @throws NullPointerException
	 *             Se a nova data passada como parametro for nula.
	 * @throws DataInvalidaException
	 *             Se a nova data for antes da data atual e depois da data de
	 *             termino.
	 */
	public void setDataCheckIn(Calendar novaDataInicio)
			throws NullPointerException, DataInvalidaException {
		verificaData(novaDataInicio, dataCheckOut);
		dataCheckIn = novaDataInicio;
	}

	/**
	 * Altera a data de termino do servico.
	 * 
	 * @param novaDataTermino
	 *            Nova data e hora de termino.
	 * @throws NullPointerException
	 *             Se a data passada como parametro for nula.
	 * @throws DataInvalidaException
	 *             Se a data for antes da data de inicio.
	 */
	public void setDataCheckOut(Calendar novaDataTermino)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataCheckIn, novaDataTermino);
		dataCheckOut = novaDataTermino;
	}

	/**
	 * Retorna a data de inicio do servico.
	 * 
	 * @return Data e hora de inicio do servico.
	 */
	@Override
	public Calendar getDataCheckIn() {
		return dataCheckIn;
	}

	/**
	 * Retorna a data de termino do servico.
	 * 
	 * @return Data e hora de termino do servico.
	 */
	@Override
	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}

	/**
	 * Mostra se o carro alugado possui ou nao o servico adicional de tanque
	 * cheio.
	 * 
	 * @return True se possuir tanque cheio, ou False caso contrario.
	 */
	public boolean getIsTanqueCheio() {
		return isTanqueCheio;
	}

	/**
	 * Mostra se o carro alugado possui ou nao o servico adicional de seguro.
	 * 
	 * @return True se possuir seguro, ou False caso contrario.
	 */
	public boolean getIsAssegurado() {
		return isAssegurado;
	}

	/**
	 * Retorna o pre�o, a partir do numero de dias e o valor de diaria do
	 * servico de acordo com o tipo de carro e os servicos adicionais (tanque
	 * cheio e seguro).
	 * 
	 * @return O pre�o do servico ja calculado.
	 */
	@Override
	public double getPreco() {
		double preco = getNumeroDeDias() * tipoDeCarro.getPreco();
		if (isTanqueCheio)
			preco += VALOR_TANQUE_CHEIO;
		if (isAssegurado)
			preco += VALOR_DO_SEGURO;
		return preco;
	}

	@Override
	public String getNome() {
		return "Carro";
	}// getNome

	/**
	 * Calcula a duracao do servico a partir do dia final e inicial, em
	 * milissegundos, e esse tempo eh arredondado em um int que representa o
	 * numero de dias.
	 * 
	 * @return A quantidade de dias do servico.
	 */
	public int getNumeroDeDias() {
		long tempoInicial = getDataCheckIn().getTimeInMillis();
		long tempoFinal = getDataCheckOut().getTimeInMillis();
		numDeDias = (int) Math
				.round(((tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UM_DIA));
		return numDeDias != 0 ? numDeDias : 1;
	}

	private void verificaData(Calendar dataInicio, Calendar dataTermino)
			throws NullPointerException, DataInvalidaException {
		if (dataInicio == null || dataTermino == null)
			throw new NullPointerException();
		if (dataInicio.before(new GregorianCalendar())
				|| dataTermino.before(dataInicio))
			throw new DataInvalidaException();
	}

	private void verificaTipo(TipoCarro tipoDeCarro)
			throws NullPointerException {
		if (tipoDeCarro == null)
			throw new NullPointerException();
	}

	/**
	 * Retorna uma representacao completa do servi�o em String, como o valor
	 * total, valor dos adicionais, tipo do automovel alugado e datas de inicio
	 * e termino.
	 * 
	 * @return String de um relatorio do servico.
	 */
	@Override
	public String toString() {
		DecimalFormat formatovalor = new DecimalFormat("R$ #,##0.00");
		final String FIM_LINHA = System.getProperty("line.separator");
		List<String> adicionais = new ArrayList<String>();
		if (isTanqueCheio)
			adicionais.add("Tanque cheio = "
					+ formatovalor.format(VALOR_TANQUE_CHEIO));
		if (isAssegurado)
			adicionais.add("Seguro = " + formatovalor.format(VALOR_DO_SEGURO));
		return "Carro "
				+ tipoDeCarro.getNome()
				+ FIM_LINHA
				+ "Adicionais: "
				+ Arrays.toString(adicionais.toArray())
				+ FIM_LINHA
				+ "Preco Total: "
				+ formatovalor.format(getPreco())
				+ FIM_LINHA
				+ "Duracao: "
				+ getNumeroDeDias()
				+ " dias"
				+ FIM_LINHA
				+ "Data Inicio: "
				+ new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm")
						.format(getDataCheckIn().getTime())
				+ FIM_LINHA
				+ "Data Termino: "
				+ new SimpleDateFormat("dd/MM/yyyy 'as' HH:mm")
						.format(getDataCheckOut().getTime());
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataCheckIn == null) ? 0 : dataCheckIn.hashCode());
		result = prime * result
				+ ((dataCheckOut == null) ? 0 : dataCheckOut.hashCode());
		result = prime * result + (isAssegurado ? 1231 : 1237);
		result = prime * result + (isTanqueCheio ? 1231 : 1237);
		result = prime * result + numDeDias;
		result = prime * result
				+ ((tipoDeCarro == null) ? 0 : tipoDeCarro.hashCode());
		return result;
	}

	/**
	 * Mostra se dois servicos de aluguel de carro sao iguais, a partir do tipo,
	 * se tem tanque cheio e/ou seguro, preco e datas de inicio e termino.
	 * 
	 * @return True caso sejam iguais ou False caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Carro))
			return false;
		Carro outroCarro = (Carro) obj;
		return (getTipoDeCarro().equals(outroCarro.getTipoDeCarro())
				&& getIsTanqueCheio() == outroCarro.getIsTanqueCheio()
				&& getIsAssegurado() == outroCarro.getIsAssegurado()
				&& getPreco() == outroCarro.getPreco()
				&& getDataCheckIn().equals(outroCarro.getDataCheckIn()) && getDataCheckOut()
				.equals(outroCarro.getDataCheckOut()));
	}
}
