package hotel;

import java.util.Calendar;

import excecoes.DataInvalidaException;

public class Carro implements Servico {
	public static final boolean TANQUE_VAZIO = false;
	public static final boolean NAO_ASSEGURADO = false;

	public static final double VALOR_TANQUE_CHEIO = 150;
	public static final double VALOR_ASSEGURADO = 100;

	private boolean isTanqueCheio;
	private boolean isAssegurado;
	private PrecoCarro preco;
	private Calendar dataInicial;
	private Calendar dataDeTermino;

	public Carro(PrecoCarro preco, boolean isTanqueCheio, boolean isAssegurado) {
		this.preco = preco;
		this.isTanqueCheio = isTanqueCheio;
		this.isAssegurado = isAssegurado;

		if (isTanqueCheio)
			this.preco.somaPreco(VALOR_TANQUE_CHEIO);
		if (isAssegurado)
			this.preco.somaPreco(VALOR_ASSEGURADO);
		dataInicial = Calendar.getInstance();

	}// Construtor

	public Carro(PrecoCarro preco) {
		this(preco, TANQUE_VAZIO, NAO_ASSEGURADO);
	}// Construtor (Default)

	public void setDataInicial(int dia, int ano, int mes) throws DataInvalidaException {
		verificaDataValida(dia, mes, ano);
		dataInicial.set(ano, mes, dia);
	}// setDataInicial

	public void setDataDeTermino(int ano, int mes, int dia) throws DataInvalidaException {
		verificaDataValida(dia, mes, ano);
		dataDeTermino.set(ano, mes, dia, 23, 59);

	}// getDataTerminoDoServico

	public Calendar getDataInicial() {
		return dataInicial;
	}// getDataInicial

	public Calendar getDataDeTermino() {
		return dataDeTermino;
	}// getDataDeTermino

	public boolean getIsTanqueCheio() {
		return isTanqueCheio;
	}// getIsTanqueCheio

	public boolean getIsAssegurado() {
		return isAssegurado;
	}// getIsAssegurado

	@Override
	public double getPreco() {
		return calculaPreco();
	}// getPreco
	

	private double calculaPreco() {
		double preco = 0;
		if (isTanqueCheio)
			preco += VALOR_TANQUE_CHEIO;
		if (isAssegurado)
			preco += VALOR_ASSEGURADO;

		int diaInicial = getDataInicial().get(Calendar.DAY_OF_YEAR);
		int diaFinal = getDataDeTermino().get(Calendar.DAY_OF_YEAR);

		preco += (diaFinal - diaInicial) * this.preco.getPreco();

		return preco;

	}// calculaPreco

	
	private void verificaDataValida(int dia, int mes, int ano) throws DataInvalidaException {
		if(mes > 12)
			throw new DataInvalidaException();

		else if(dia > 31)
			throw new DataInvalidaException();

		else if(dia == 31) {
			if((mes % 2 == 0 && mes <= 6) || mes == 9 || mes == 11)
				throw new DataInvalidaException();

		} else if(mes == 2)
			verificaCasoFevereiro(dia, ano);
	}// verificaDataValida
	
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


	@Override
	public String toString() {
		return "Carro [isTanqueCheio=" + isTanqueCheio + ", isAssegurado="
				+ isAssegurado + ", preco=" + preco + ", dataInicial="
				+ dataInicial + ", dataDeTermino=" + dataDeTermino + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataDeTermino == null) ? 0 : dataDeTermino.hashCode());
		result = prime * result
				+ ((dataInicial == null) ? 0 : dataInicial.hashCode());
		result = prime * result + (isAssegurado ? 1231 : 1237);
		result = prime * result + (isTanqueCheio ? 1231 : 1237);
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (dataDeTermino == null) {
			if (other.dataDeTermino != null)
				return false;
		} else if (!dataDeTermino.equals(other.dataDeTermino))
			return false;
		if (dataInicial == null) {
			if (other.dataInicial != null)
				return false;
		} else if (!dataInicial.equals(other.dataInicial))
			return false;
		if (isAssegurado != other.isAssegurado)
			return false;
		if (isTanqueCheio != other.isTanqueCheio)
			return false;
		if (preco != other.preco)
			return false;
		return true;
	}


}// Carro

