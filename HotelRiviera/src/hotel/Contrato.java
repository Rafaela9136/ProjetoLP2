package hotel;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import excecoes.DataInvalidaException;

public class Contrato {
	private Hospede hospedeTitular;
	private List<Hospede> acompanhantes;
	private List<Servico> servicos;
	private Opiniao opiniao;
	private List<Quarto> quartos;
	private double contaRestaurante;

	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private float despesasTotais;
	private boolean isReserva;

	public Contrato(Hospede hospedeTitular, List<Hospede> acompanhantes,
			int diaInicial, int mesInicial, int anoInicial, int diaFinal,
			int mesFinal, int anoFinal, boolean isReserva, List<Quarto> quartos)
			throws Exception {

		verificaHospedeTitularValido(hospedeTitular);
		verificaAcompanhantesValidos(acompanhantes);
		verificaQuartosValidos(quartos);
		verificaParametrosDeDataValidos(diaInicial, mesInicial, anoInicial);
		verificaParametrosDeDataValidos(diaFinal, mesFinal, anoFinal);

		dataCheckIn.set(Calendar.YEAR, anoInicial);
		dataCheckIn.set(Calendar.MONTH, mesInicial);
		dataCheckIn.set(Calendar.DAY_OF_MONTH, diaInicial);

		dataCheckOut.set(Calendar.YEAR, anoFinal);
		dataCheckOut.set(Calendar.MONTH, mesFinal);
		dataCheckOut.set(Calendar.DAY_OF_MONTH, diaFinal);

		if (dataCheckIn.compareTo(dataCheckOut) < 0)
			throw new Exception();
		this.hospedeTitular = hospedeTitular;
		this.acompanhantes = acompanhantes;
		this.isReserva = isReserva;
		this.quartos = quartos;

	}// Construtor

	public boolean getIsReserva() {
		return isReserva;
	}// getIsReserva
	
	public List<Hospede> getAcompanhantes() {
		return acompanhantes;
	}// getAcompanhantes
	
	public Opiniao getOpiniao() {
		return opiniao;
	}// getOpiniao
	
	public List<Quarto> getQuartos() {
		return quartos;
	}// getQuartos

	public Calendar getDataCheckIn() {
		return dataCheckOut;
	}// getDataCheckIn

	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}// getDataCheckOut

	public double getDespesasTotais() {
		return despesasTotais;
	}// getDespesasTotais
	
	public double getContaRestaurante() {
		return contaRestaurante;
	}// getContaRestaurante

	public void setIsReserva(boolean estado) {
		isReserva = estado;
	}// setIsReserva
	
	public void setDataCheckOut(int dia, int mes, int ano) throws DataInvalidaException {
		verificaDataCheckOutValida(dia, mes, ano);
		this.dataCheckOut.set(Calendar.YEAR, ano);
		this.dataCheckOut.set(Calendar.MONTH, mes);
		this.dataCheckOut.set(Calendar.DAY_OF_MONTH, dia);
	}// setDataCheckOut
	
	public void adicionaServico(Servico servico) {
		servicos.add(servico);
	}// adicionaServico

	public boolean removeServico(Servico servico) {
		return servicos.remove(servico);
	}// removeServico

	public Servico pesquisaServico(Servico servico) {
		for (int i = 0; i < servicos.size(); i++) {
			if (servicos.get(i).equals(servico))
				return servico;

		}// for
		return null;
	}// pesquisaServico
	
	public void inicializaOpiniao(float nota, String comentario) throws Exception {
		opiniao = new Opiniao(comentario, nota);
	}// setOpiniao

	// Despesas totais n�o deve ser somada a conta do restaurante, apenas no final do contrato
	public void adicionaDespesa(float valor) {
		despesasTotais += valor;
	}// adicionaDespesa

	public void somaContaRestaurante(double valor) {
		contaRestaurante += valor;
	}// contaRestaurante

	public double calculaTotalPorEstrategia(Estrategias estrategia) {
		return (getDespesasTotais() + getContaRestaurante()) * estrategia.getPorcentagem();
	} // calcula o total a pagar de acordo com a epoca
	
	private void verificaParametrosDeDataValidos(int dia, int mes, int ano)
			throws DataInvalidaException {
		if (mes > 12)
			throw new DataInvalidaException();

		else if (dia > 31)
			throw new DataInvalidaException();

		else if (dia == 31) {
			if ((mes % 2 == 0 && mes <= 6) || mes == 9 || mes == 11)
				throw new DataInvalidaException();

		} else if (mes == 2)
			verificaCasoFevereiro(dia, ano);
	}// verificaParametrosDeDataValidos
	

	private void verificaHospedeTitularValido(Hospede hospede) throws NullPointerException {
		if (hospedeTitular == null)
			throw new NullPointerException();
	}// verificaHospedeTitularValido

	private void verificaAcompanhantesValidos(List<Hospede> acompanhantes)
			throws NullPointerException {
		if (acompanhantes == null)
			throw new NullPointerException();
	} // verificaAcompanhantesValidos

	private void verificaQuartosValidos(List<Quarto> quartos) throws NullPointerException {
		if (quartos == null)
			throw new NullPointerException();
	}// verificaQuartosValidos
	
	private void verificaDataCheckOutValida(int dia, int mes, int ano)
			throws DataInvalidaException {
		verificaParametrosDeDataValidos(dia, mes, ano);

		Calendar novaDataCheckOut = Calendar.getInstance();
		novaDataCheckOut.set(Calendar.YEAR, ano);
		novaDataCheckOut.set(Calendar.MONTH, mes);
		novaDataCheckOut.set(Calendar.DAY_OF_MONTH, dia);
		if (dataCheckIn.compareTo(novaDataCheckOut) > 0)
			throw new DataInvalidaException();
	}// verificaDataCheckOutValida
	
	private void verificaCasoFevereiro(int dia, int ano) throws DataInvalidaException {
		if (ano % 100 == 0) {
			if (dia > 28)
				throw new DataInvalidaException();
		} else if (ano % 4 == 0 || ano % 400 == 0) {
			if (dia > 29)
				throw new DataInvalidaException();
		} else {
			if (dia > 28)
				throw new DataInvalidaException();
		}
	}// verificaCasoFevereiro

}// Contrato
