package hotel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import excecoes.AddQuartoContratoException;
import excecoes.ComentarioVazioException;
import excecoes.ContratoFechadoException;
import excecoes.ContratoSemOpiniaoException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.EstouroDeCaracteresException;
import excecoes.FrigobarEmListServicosException;
import excecoes.NomeInvalidoException;
import excecoes.NotaInvalidaException;

/**
 * 
 * @author Grupinho da Alegria
 * @version 2.0
 * @see nothing Classe responsavel por criar um contrato contendo hospedes com
 *      suas informacoes, datas de comeco e fim do contrato, opiniao sobre o
 *      servico prestado, entre outras coisas.
 */
public class Contrato {
	public static final boolean CONTRATO_ABERTO = true;
	public static final double MILISSEGUNDOS_EM_UM_DIA = 86400000;

	private List<String> acompanhantes;
	private List<Servico> servicos;

	private Hospede hospedeTitular;
	private Opiniao opiniao;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private Estrategias estrategia;

	private float despesasAdicionais;
	private boolean isReserva;
	private boolean isAberto;

	/**
	 * 
	 * @param hospedeTitular
	 *            Hospede responsavel pelo contrato criado. Nesse objeto estao
	 *            todas as informacoes dele como nome, endereco, numero do
	 *            cartao de credito, CPF, etc.
	 * @param hospedesAcompanhantes
	 *            Nessa lista estao os nomes das outras pessoas que estao
	 *            associadas ao contrato, esta lista pode estar vazia.
	 * @param dataCheckIn
	 *            Data do inicio do contrato usada para calcular o preco dos
	 *            contrato.
	 * @param dataCheckOut
	 *            Data do fim do contrato suada para calcular o preco dos
	 *            contrato
	 * @param servicos
	 *            List de servicos do contrato contendo objetos como Quartos,
	 *            conta do restaurante, etc (Esta lista deve ter pelo menos um
	 *            quarto).
	 * @throws NullPointerException
	 *             Excecao lancada ao receber objetos que nao foram
	 *             inicializados
	 * @throws ContratoSemQuartoException
	 *             Excecao lancada quando a lista de servicos nao possui um
	 *             quarto nela.
	 * @throws FrigobarEmListServicosException
	 *             Excecao lancada quando tentam adicionar um frigobar na lista
	 *             de servicos.
	 * @throws DataInvalidaException
	 *             Excecao lancada quando a data final do contrato esta para
	 *             antes do inicio do contrato, ou quando alguma das duas datas
	 *             esta setada para um dia que ja passou.
	 */
	public Contrato(Hospede hospedeTitular, List<String> hospedesAcompanhantes,
			Calendar dataCheckIn, Calendar dataCheckOut, List<Servico> servicos)
			throws NullPointerException, ContratoSemQuartoException,
			FrigobarEmListServicosException, DataInvalidaException {

		verificaHospedeTitularValido(hospedeTitular);
		verificaAcompanhantesValidos(hospedesAcompanhantes);
		verificaServicosValido(servicos);
		verificaDatasValidas(dataCheckIn, dataCheckOut);

		Calendar dataAtual = new GregorianCalendar();
		dataAtual.set(Calendar.HOUR_OF_DAY, 00);
		dataAtual.set(Calendar.MINUTE, 00);

		if (!(dataCheckIn.get(Calendar.YEAR) == dataAtual.get(Calendar.YEAR)
				&& dataCheckIn.get(Calendar.MONTH) == dataAtual
						.get(Calendar.MONTH) && dataCheckIn
					.get(Calendar.DAY_OF_MONTH) == dataAtual
				.get(Calendar.DAY_OF_MONTH)))
			this.isReserva = true;

		this.estrategia = estrategiaAAplicar(dataCheckIn, dataCheckOut);
		this.hospedeTitular = hospedeTitular;
		this.dataCheckIn = dataCheckIn;
		this.dataCheckOut = dataCheckOut;
		this.isAberto = CONTRATO_ABERTO;
		this.acompanhantes = hospedesAcompanhantes;
		this.servicos = servicos;

	}// Construtor

	/**
	 * @see nothing Recupera a informacao sobre o contrato que diz se ele esta
	 *      aberto ou nao.
	 * @return Retorna true se o contrato estiver aberto e false se nao.
	 */
	public boolean getIsReserva() {
		return isReserva;
	}// getIsReserva

	/**
	 * @see nothing Recupera a estrategia do contrato.
	 * @return Retorna a estrategia a ser aplicada no preco do contrato.
	 */
	public Estrategias getEstrategia() {
		return estrategia;
	}// getEstrategia

	/**
	 * @see nothing Recupera a lista com o nome dos acompanhantes do hospede
	 *      titular.
	 * @return Retorna a lista com o nome dos acompanhantes.
	 */
	public List<String> getAcompanhantes() {
		return acompanhantes;
	}// getAcompanhantes

	/**
	 * @see nothing Recupera a Opiniao do hospede sobre o servico do hotel.
	 * @return Retorna a opiniao do hospede.
	 */
	public Opiniao getOpiniao() {
		return opiniao;
	}// getOpiniao

	/**
	 * @see nothing Recupera em que o contrato come�a a valer e deixa de ser
	 *      uma reserva.
	 * @return Retorna a data inicial do contrato.
	 */
	public Calendar getDataCheckIn() {
		return dataCheckIn;
	}// getDataCheckIn

	/**
	 * @see nothing Recupera a lista com todos os servicos do contrato,
	 *      inclusive os quartos.
	 * @return Retorna a lista dos servicos.
	 */
	public List<Servico> getServicos() {
		return servicos;
	}// getServicos

	/**
	 * @see nothing Recupera a data de termino do contrato.
	 * @return Retorna a data onde acaba o contrato
	 */
	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}// getDataCheckOut

	/**
	 * @see nothing Recupera as despesas adicionais do contrato, como alguma
	 *      despesa por dano a algo do hotel ou por algum servico diferente que
	 *      nao faca parte do programa.
	 * @return Retorna as despesas adicionais.
	 */
	public double getDespesasAdicionais() {
		return despesasAdicionais;
	}// getDespesasTotais

	/**
	 * @see nothing Recupera o estado do contrato, se esta aberto ou nao.
	 * @return Retorna true se o contrato estiver aberto e falso caso contrario.
	 */
	public boolean getIsAberto() {
		return isAberto;
	}// getIsAberto

	/**
	 * @see nothing Recupera o hospede titular do contrato.
	 * @return Retorna o hospede titular no contrato.
	 */
	public Hospede getHospedeTitular() {
		return hospedeTitular;
	}// getHospedeTitular

	public void setHospedeTitular(Hospede hospedeTitular)
			throws NullPointerException {
		if (hospedeTitular == null)
			throw new NullPointerException();
		this.hospedeTitular = hospedeTitular;
	}// setHospedeTitular

	/**
	 * @see Estabelece um valor ao atributo que diz se o contrato esta aberto ou
	 *      nao.
	 * @param estado
	 *            Valor a ser estabelecido.
	 * @throws ContratoFechadoException
	 *             Excecao lancada quando o contrato ja esta fechado e tentam
	 *             alterar esse valor.
	 * @throws ContratoSemOpiniaoException
	 *             Excecao lancada quando tentam fechar o contrato sem ter
	 *             adicionado uma opiniao sobre a estadia no hotel.
	 */
	public void setIsAberto(boolean estado) throws ContratoFechadoException,
			ContratoSemOpiniaoException {
		if (!isAberto)
			throw new ContratoFechadoException();

		if (!estado && opiniao == null)
			throw new ContratoSemOpiniaoException();
		isAberto = estado;
	}// setIsAberto

	/**
	 * @see nothing Metodo responsavel por estabelecer um valor para variavel
	 *      que diz se o contrato e reserva ou nao.
	 * @param estado
	 *            Estado em que o contrato deve estar. True para ser uma reserva
	 *            e falso para deixar de ser uma.
	 */
	public void setIsReserva(boolean estado) {
		isReserva = estado;
	}// setIsReserva

	/**
	 * @see nothing Metodo responsavel por estabelecer uma nova data final para
	 *      o contrato.
	 * @param novaData
	 *            Data a ser estabelecida.
	 * @throws NullPointerException
	 *             Excecao lancada quando a o parametro novaData nao foi
	 *             inicializado.
	 * @throws DataInvalidaException
	 *             Excecao lancada quando a novaData a ser estabelecida ja
	 *             passou ou e menor do que a data inicial.
	 */
	public void setDataCheckOut(Calendar novaData) throws NullPointerException,
			DataInvalidaException {
		verificaDatasValidas(dataCheckIn, novaData);
		dataCheckOut = novaData;
	}// setDataCheckOut

	/**
	 * @see nothing Metodo responsavel por estabelecer uma nova data inicial
	 *      para o contrato.
	 * @param novaData
	 *            Data a ser estabelecida.
	 * @throws NullPointerException
	 *             Excecao lancada quando a o parametro novaData nao foi
	 *             inicializado.
	 * @throws DataInvalidaException
	 *             Excecao lancada quando a novaData a ser estabelecida ja
	 *             passou ou e maior do que a data inicial.
	 */
	public void setDataCheckIn(Calendar novaData) throws NullPointerException,
			DataInvalidaException {
		verificaDatasValidas(novaData, dataCheckOut);
		dataCheckIn = novaData;
	}// setDataCheckIn

	/**
	 * @see nothing Adiciona um acompanante a lista de acompanhantes.
	 * @param acompanhante
	 *            Acompanhante a ser adicionado.
	 * @throws NullPointerException
	 *             Excecao lancada quando o parametro nao foi inicializado.
	 * @throws NomeInvalidoException
	 *             Excecao lancada quando o nome do acompanhante e uma string
	 *             vazia.
	 */
	public void adicionaAcompanhante(String acompanhante)
			throws NullPointerException, NomeInvalidoException {
		verificaAcompanhanteValido(acompanhante);
		acompanhantes.add(acompanhante);
	}// adicionaAcompanhante

	/**
	 * 
	 * @see nothing Remove um acompanante a lista de acompanhantes.
	 * @param acompanhante
	 *            Acompanhante a ser adicionado.
	 * @return Retorna verdadeiro se o acompanhante foi removido e falso caso
	 *         contrario.
	 * @throws NullPointerException
	 *             Excecao lancada quando o parametro nao foi inicializado.
	 * @throws NomeInvalidoException
	 *             Excecao lancada quando o nome do acompanhante e uma string
	 *             vazia.
	 */
	public boolean removeAcompanhante(String acompanhante)
			throws NullPointerException, NomeInvalidoException {
		return acompanhantes.remove(acompanhante);
	}// removeAcompanhante

	/**
	 * @see nothing Metodo responsavel por adicionar novos servicos na lista de
	 *      servicos do contrato.
	 * @param servico
	 *            Servico a ser adicionado a lista de servicos.
	 * @throws FrigobarEmListServicosException
	 *             Excecao lancada quando o servico a ser adicionado e do tipo
	 *             Frigobar, objetos dessa classe estao nos seus proprios
	 *             quartos.
	 * @throws AddQuartoContratoException
	 *             Excecao lancada quando o servico a ser adicionado e um
	 *             quarto, quartos so podem ter adicionados na criacao do
	 *             contrato.
	 */
	public void adicionaServico(Servico servico)
			throws FrigobarEmListServicosException, AddQuartoContratoException {
		if (servico instanceof Frigobar)
			throw new FrigobarEmListServicosException();

		if (servico instanceof Quarto)
			throw new AddQuartoContratoException();
		servicos.add(servico);
	}// adicionaServico

	/**
	 * @see nothing Remove um servico da lista de servicos
	 * @param servico
	 *            Servico a ser removido da lista de servicos
	 * @return Retorna true se o servico foi removido e false caso contrario.
	 */
	public boolean removeServico(Servico servico) {
		return servicos.remove(servico);
	}// removeServico

	/**
	 * @see nothing Metodo responsavel por inicializar o objeto Opiniao do
	 *      contrato que contem uma nota do cliente sobre o que ele achou dos
	 *      servicos e de sua estadia em geral no hotel.
	 * @param nota
	 * @param comentario
	 * @throws NullPointerException
	 * @throws NotaInvalidaException
	 * @throws EstouroDeCaracteresException
	 * @throws ComentarioVazioException
	 */
	public void inicializaOpiniao(float nota, String comentario)
			throws NullPointerException, NotaInvalidaException,
			EstouroDeCaracteresException, ComentarioVazioException {
		opiniao = new Opiniao(comentario, nota);
	}// setOpiniao

	/**
	 * @see nothing Metodo responsavel por adicionar alguma despesa extra ao
	 *      contrato.
	 * @param valor
	 *            Valor a ser adicionado.
	 */
	public void adicionaDespesa(float valor) {
		despesasAdicionais += valor;
	}// adicionaDespesa

	/**
	 * @see nothing Metodo responsavel por calcular o numero de dias que o
	 *      contrato durara.
	 * @return Retorna o numero de dias.
	 */
	public int getNumeroDeDias() {
		long tempoInicial = getDataCheckIn().getTimeInMillis();
		long tempoFinal = getDataCheckOut().getTimeInMillis();
		int numDeDias = (int) Math
				.round(((tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UM_DIA));
		return numDeDias;
	}// getNumeroDeDias

	/**
	 * @see nothing Metodo responsavel por calcular o valor total do contrato.
	 * @return Retorna o valor total.
	 */
	public double calculaValorTotalServicos() {
		double valor = 0;
		for (Servico servico : servicos) {
			if (servico instanceof Quarto) {
				Quarto umQuarto = (Quarto) servico;
				valor += umQuarto.getPreco() * getNumeroDeDias();
				valor += umQuarto.getFrigobar().getPreco();
				continue;
			}// if
			valor += servico.getPreco();
		}// for
		return valor;
	}// calculaValorTotalServicos
	
	/**
	 * @see nothing Metodo responsavel por calcular o valor total do contrato e
	 *      ja multiplicando pela porcentagem da estrategia.
	 * @return Retorna o valor total.
	 */
	public double calculaValorTotalServicosComEstrategia() {
		double valor = 0;
		for (Servico servico : servicos) {
			if (servico instanceof Quarto) {
				Quarto umQuarto = (Quarto) servico;
				valor += umQuarto.getPreco() * getNumeroDeDias();
				valor += umQuarto.getFrigobar().getPreco();
				continue;
			}// if
			valor += servico.getPreco();
		}// for
		return valor * estrategia.getPorcentagem();
	}// calculaValorTotalServicosComEstrategia

	private void verificaHospedeTitularValido(Hospede hospede)
			throws NullPointerException {
		if (hospede == null)
			throw new NullPointerException();
	}// verificaHospedeTitularValido

	@Override
	public String toString() {
		return "\nCONTRATO "
				+ "\nHospedeTitular: "
				+ hospedeTitular
				+ "\nAcompanhantes: "
				+ acompanhantes
				+ "\nServicos: "
				+ servicos
				+ "\nOpiniao: "
				+ opiniao
				+ "\nCheckIn: "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataCheckIn
						.getTime())
				+ "\nCheckOut: "
				+ new SimpleDateFormat("dd/MM/yyyy HH:mm").format(dataCheckOut
						.getTime()) + "\nEstrategia: " + estrategia
				+ "\nDespesas Adicionais: " + despesasAdicionais
				+ "\nReserva: " + isReserva + "\nContrato Aberto: " + isAberto
				+ "\n";
	}

	private Estrategias estrategiaAAplicar(Calendar dataCheckIn,
			Calendar dataCheckOut) {
		Estrategias[] estrategias = Estrategias.values();

		for (Estrategias estrategia : estrategias)
			if (estrategia.getDataInicial().after(dataCheckIn)
					&& estrategia.getDataFinal().before(dataCheckOut))
				return estrategia;
		return Estrategias.DEFAULT;
	}// estrategiaAAplicar

	private void verificaAcompanhantesValidos(List<String> hospedesAcompanhantes)
			throws NullPointerException {
		if (hospedesAcompanhantes == null)
			throw new NullPointerException();
	} // verificaAcompanhantesValidos

	private void verificaDatasValidas(Calendar dataCheckIn,
			Calendar dataCheckOut) throws NullPointerException,
			DataInvalidaException {
		verificaDataNull(dataCheckIn);
		verificaDataNull(dataCheckOut);

		Calendar dataAtual = Calendar.getInstance();

		dataAtual.set(Calendar.HOUR_OF_DAY, 00);
		dataAtual.set(Calendar.MINUTE, 00);

		if (dataCheckIn.compareTo(dataAtual) < 0 || dataCheckOut.compareTo(dataCheckIn) < 0)
			throw new DataInvalidaException();

	}// verificaDataValida

	private void verificaDataNull(Calendar data) throws NullPointerException {
		if (data == null)
			throw new NullPointerException();
	}// verificaDataNull

	private void verificaServicosValido(List<Servico> servicos)
			throws ContratoSemQuartoException, NullPointerException,
			FrigobarEmListServicosException {
		if (servicos == null)
			throw new NullPointerException();

		int quantQuartos = 0;
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i) instanceof Quarto)
				quantQuartos++;
			if (servicos.get(i) instanceof Frigobar)
				throw new FrigobarEmListServicosException();

		}// for
		if (quantQuartos == 0)
			throw new ContratoSemQuartoException();

	}// verificaServicosValido

	private void verificaAcompanhanteValido(String acompanhante)
			throws NullPointerException, NomeInvalidoException {
		if (acompanhante == null)
			throw new NullPointerException();
		if (acompanhante.isEmpty())
			throw new NomeInvalidoException();
	}// verificaAcompananteValido

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((acompanhantes == null) ? 0 : acompanhantes.hashCode());
		result = prime * result
				+ ((dataCheckIn == null) ? 0 : dataCheckIn.hashCode());
		result = prime * result
				+ ((dataCheckOut == null) ? 0 : dataCheckOut.hashCode());
		result = prime * result + Float.floatToIntBits(despesasAdicionais);
		result = prime * result
				+ ((estrategia == null) ? 0 : estrategia.hashCode());
		result = prime * result
				+ ((hospedeTitular == null) ? 0 : hospedeTitular.hashCode());
		result = prime * result + (isAberto ? 1231 : 1237);
		result = prime * result + (isReserva ? 1231 : 1237);
		result = prime * result + ((opiniao == null) ? 0 : opiniao.hashCode());
		result = prime * result
				+ ((servicos == null) ? 0 : servicos.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contrato other = (Contrato) obj;
		if (!acompanhantes.equals(other.acompanhantes))
			return false;
		if (!dataCheckIn.equals(other.dataCheckIn))
			return false;
		if (!dataCheckOut.equals(other.dataCheckOut))
			return false;
		if (Float.floatToIntBits(despesasAdicionais) != Float
				.floatToIntBits(other.despesasAdicionais))
			return false;
		if (!estrategia.equals(other.estrategia))
			return false;
		if (!hospedeTitular.equals(other.hospedeTitular))
			return false;
		if (isAberto != other.isAberto)
			return false;
		if (isReserva != other.isReserva)
			return false;
		if (opiniao == null) {
			if (other.opiniao != null)
				return false;
		} else if (!opiniao.equals(other.opiniao))
			return false;
		if (!servicos.equals(other.servicos))
			return false;
		return true;
	}// equals

}// Contrato
