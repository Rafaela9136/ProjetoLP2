package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import excecoes.AddQuartoContratoException;
import excecoes.ContratoFechadoException;
import excecoes.ContratoSemOpiniaoException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.EstouroDeCaracteresException;
import excecoes.FrigobarEmListServicosException;
import excecoes.NotaInvalidaException;

public class Contrato {
	public static final boolean CONTRATO_ABERTO = true;
	public static final double MILISSEGUNDOS_EM_UM_DIA = 86400000;

	private List<String> acompanhantes;
	private List<Servico> servicos;

	private Hospede hospedeTitular;
	private Opiniao opiniao;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private EstrategiaAplicavel estrategia;

	private float despesasAdicionais;
	private boolean isReserva;
	private boolean isAberto;

	public Contrato(Hospede hospedeTitular, List<String> hospedesAcompanhantes,
			EstrategiaAplicavel estrategia, Calendar dataCheckIn,
			Calendar dataCheckOut, boolean isReserva, List<Servico> servicos)
			throws NullPointerException, ContratoSemQuartoException,
			FrigobarEmListServicosException, DataInvalidaException {

		verificaHospedeTitularValido(hospedeTitular);
		verificaAcompanhantesValidos(hospedesAcompanhantes);
		verificaServicosValido(servicos);
		verificaDatasValidas(dataCheckIn, dataCheckOut);

		if (estrategia == null)
			throw new NullPointerException();

		this.estrategia = estrategia;
		this.hospedeTitular = hospedeTitular;
		this.isReserva = isReserva;
		this.isAberto = CONTRATO_ABERTO;
		this.dataCheckIn = dataCheckIn;
		this.dataCheckOut = dataCheckOut;

		this.acompanhantes = hospedesAcompanhantes;
		this.servicos = servicos;

	}// Construtor

	public boolean getIsReserva() {
		return isReserva;
	}// getIsReserva

	public EstrategiaAplicavel getEstrategia() {
		return estrategia;
	}// getEstrategia

	public List<String> getAcompanhantes() {
		return acompanhantes;
	}// getAcompanhantes

	public Opiniao getOpiniao() {
		return opiniao;
	}// getOpiniao

	public Calendar getDataCheckIn() {
		return dataCheckIn;
	}// getDataCheckIn

	public List<Servico> getServicos() {
		return servicos;
	}

	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}// getDataCheckOut

	public double getDespesasAdicionais() {
		return despesasAdicionais;
	}// getDespesasTotais

	public boolean getIsAberto() {
		return isAberto;
	}// getIsAberto

	public Hospede getHospedeTitular() {
		return hospedeTitular;
	}// getHospedeTitular

	public void setIsAberto(boolean estado) throws ContratoFechadoException, ContratoSemOpiniaoException {
		if(!isAberto)
			throw new ContratoFechadoException();
		
		if(!estado && opiniao == null)
			throw new ContratoSemOpiniaoException();
		isAberto = estado;
	}// setIsAberto

	public void setIsReserva(boolean estado) {
		isReserva = estado;
	}// setIsReserva

	public void setDataCheckOut(Calendar novaData) throws NullPointerException, DataInvalidaException {
		verificaDatasValidas(dataCheckIn, novaData);
		dataCheckOut = novaData;
	}// setDataCheckOut
	
	public void setDataCheckIn(Calendar novaData) throws NullPointerException, DataInvalidaException {
		verificaDatasValidas(novaData, dataCheckOut);
		dataCheckIn = novaData;
	}// setDataCheckIn

	public void adicionaServico(Servico servico)
			throws FrigobarEmListServicosException, AddQuartoContratoException {
		if (servico instanceof Frigobar)
			throw new FrigobarEmListServicosException();

		if (servico instanceof Quarto)
			throw new AddQuartoContratoException();
		servicos.add(servico);
	}// adicionaServico

	public boolean removeServico(Servico servico) {
		return servicos.remove(servico);
	}// removeServico

	public void inicializaOpiniao(float nota, String comentario)
			throws NullPointerException, NotaInvalidaException,
			EstouroDeCaracteresException {
		opiniao = new Opiniao(comentario, nota);
	}// setOpiniao

	// Despesas totais nao deve ser somada a conta do restaurante, apenas no
	// final do contrato
	public void adicionaDespesa(float valor) {
		despesasAdicionais += valor;
	}// adicionaDespesa

	public int getNumeroDeDias() {
		long tempoInicial = getDataCheckIn().getTimeInMillis();
		long tempoFinal = getDataCheckOut().getTimeInMillis();
		int numDeDias = (int) Math
				.round(((tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UM_DIA));
		return numDeDias;
	}// getNumeroDeDias

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
		return valor * estrategia.getPorcentagemAAplicar();
	}// calculaValorTotalServicos

	/*
	 * public double calculaTotalPorEstrategia() { return
	 * (getDespesasAdicionais() + calculaValorTotalServicos())
	 * getEstrategia().getPorcentagem(); } // calcula o total a pagar de acordo
	 * com a epoca
	 */// � o contrato que vai calcular isso? Devia ser o hotel, n�o?

	private void verificaHospedeTitularValido(Hospede hospede)
			throws NullPointerException {
		if (hospede == null)
			throw new NullPointerException();
	}// verificaHospedeTitularValido

	@Override
	public String toString() {
		return "\nContrato \nAcompanhantes: " + acompanhantes + "\nServicos: "
				+ servicos + "\nHospedeTitular: " + hospedeTitular
				+ "\nOpiniao: " + opiniao + "\nCheckIn: " + dataCheckIn
				+ "\nCheckOut: " + dataCheckOut + "\nEstrategia: " + estrategia
				+ "\nDespesas Adicionais: " + despesasAdicionais
				+ "\nReserva: " + isReserva + "\nContrato Aberto: " + isAberto
				+ "\n";
	}

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
		
		if (dataCheckIn.before(new GregorianCalendar())
				|| dataCheckOut.before(dataCheckIn))
			throw new DataInvalidaException();
		
		if (dataCheckIn.compareTo(dataCheckOut) > 0)
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
