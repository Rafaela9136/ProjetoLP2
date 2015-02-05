package hotel;

import java.util.Calendar;
import java.util.List;

import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.FrigobarEmListServicosException;

public class Contrato {
	public static final boolean CONTRATO_ABERTO = true;

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

	public Contrato(Hospede hospedeTitular, List<String> acompanhantes,
			EstrategiaAplicavel estrategia, Calendar dataCheckIn,
			Calendar dataCheckOut, boolean isReserva, List<Servico> servicos)
			throws NullPointerException, ContratoSemQuartoException,
			FrigobarEmListServicosException, DataInvalidaException {

		verificaHospedeTitularValido(hospedeTitular);
		verificaAcompanhantesValidos(acompanhantes);
		verificaServicosValido(servicos);
		verificaDatasValidas(dataCheckIn, dataCheckOut);

		if (estrategia == null)
			throw new NullPointerException();

		this.estrategia = estrategia;
		this.hospedeTitular = hospedeTitular;
		this.isReserva = isReserva;
		this.isAberto = CONTRATO_ABERTO;

		this.acompanhantes = acompanhantes;
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

	public void setIsAberto(boolean estado) {
		isAberto = estado;
	}// setIsAberto

	public void setIsReserva(boolean estado) {
		isReserva = estado;
	}// setIsReserva

	public void setDataCheckOut(Calendar novaData) throws NullPointerException {
		verificaDataNull(novaData);
		dataCheckOut = novaData;
	}// setDataCheckOut

	public void adicionaServico(Servico servico) {
		servicos.add(servico);
	}// adicionaServico

	public boolean removeServico(Servico servico) {
		return servicos.remove(servico);
	}// removeServico

	public Servico pesquisaServico(double precoDoServico) {
		for (Servico servico : servicos) {
			if (servico.getPreco() == precoDoServico)
				return servico;

		}// for
		return null;
	}// pesquisaServico

	public void inicializaOpiniao(float nota, String comentario)
			throws Exception {
		opiniao = new Opiniao(comentario, nota);
	}// setOpiniao

	// Despesas totais nao deve ser somada a conta do restaurante, apenas no
	// final do contrato
	public void adicionaDespesa(float valor) {
		despesasAdicionais += valor;
	}// adicionaDespesa

	public double calculaValorTotalServicos() {
		double valor = 0;
		for (Servico servico : servicos) {
			valor += servico.getPreco();
		}// for
		return valor;
	}// calculaValorTotalRestaurante

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

	private void verificaAcompanhantesValidos(List<String> acompanhantes)
			throws NullPointerException {
		if (acompanhantes == null)
			throw new NullPointerException();
	} // verificaAcompanhantesValidos

	private void verificaDatasValidas(Calendar dataCheckIn,
			Calendar dataCheckOut) throws NullPointerException,
			DataInvalidaException {
		verificaDataNull(dataCheckIn);
		verificaDataNull(dataCheckOut);
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

}// Contrato
