package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import excecoes.DataInvalidaException;

public class Carro implements Servico {
	public static final boolean TANQUE_VAZIO = false;
	public static final boolean NAO_ASSEGURADO = false;

	public static final double VALOR_TANQUE_CHEIO = 150;
	public static final double VALOR_ASSEGURADO = 100;

	private boolean isTanqueCheio;
	private boolean isAssegurado;
	private TipoCarro tipoDeCarro;
	private double preco;
	private Calendar dataInicio;
	private Calendar dataTermino;
	private int numDeDias;

	public Carro(TipoCarro tipoDeCarro, Calendar dataInicio,
			Calendar dataTermino, boolean isTanqueCheio, boolean isAssegurado)
			throws NullPointerException, DataInvalidaException {
		verificaDataNula(dataInicio);
		verificaDataNula(dataTermino);
		verificaData(dataInicio, dataTermino);
		this.tipoDeCarro = tipoDeCarro;
		this.isTanqueCheio = isTanqueCheio;
		this.isAssegurado = isAssegurado;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		preco = 0;
		numDeDias = 0;

	}// Construtor

	public Carro(TipoCarro tipoDeCarro, Calendar dataInicio,
			Calendar dataTermino) throws NullPointerException,
			DataInvalidaException {
		this(tipoDeCarro, dataInicio, dataTermino, TANQUE_VAZIO, NAO_ASSEGURADO);
	}// Construtor (Default)

	public TipoCarro getTipoDeCarro() {
		return tipoDeCarro;
	}

	public void setDataInicio(Calendar novaDataInicio)
			throws NullPointerException, DataInvalidaException {
		verificaData(novaDataInicio, dataTermino);
		verificaDataNula(novaDataInicio);
		dataInicio = novaDataInicio;
	}

	public void setDataDeTermino(Calendar novaDataTermino)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataInicio, novaDataTermino);
		verificaDataNula(novaDataTermino);
		dataTermino = novaDataTermino;
	}

	public Calendar getDataInicio() {
		return dataInicio;
	}

	public Calendar getDataTermino() {
		return dataTermino;
	}

	public boolean getIsTanqueCheio() {
		return isTanqueCheio;
	}

	public boolean getIsAssegurado() {
		return isAssegurado;
	}

	@Override
	public double getPreco() {
		calculaPreco();
		if (isTanqueCheio)
			preco += VALOR_TANQUE_CHEIO;
		if (isAssegurado)
			preco += VALOR_ASSEGURADO;
		return preco;
	}

	public int numeroDeDias() {
		int diaInicial = getDataInicio().get(Calendar.DAY_OF_YEAR);
		int diaFinal = getDataTermino().get(Calendar.DAY_OF_YEAR);
		numDeDias = diaFinal - diaInicial;
		return numDeDias;
	}

	private void verificaDataNula(Calendar data) throws NullPointerException {
		if (data == null)
			throw new NullPointerException();
	}

	private void verificaData(Calendar dataInicio, Calendar dataTermino)
			throws DataInvalidaException {
		if (dataInicio.before(new GregorianCalendar())
				|| dataTermino.before(dataInicio))
			throw new DataInvalidaException();
	}

	private void calculaPreco() {
		preco = numeroDeDias() * tipoDeCarro.getPreco();
	}

	// @Override
	// public String toString() {
	// return "Carro [isTanqueCheio=" + isTanqueCheio + ", isAssegurado="
	// + isAssegurado + ", preco=" + preco + ", dataInicial="
	// + dataInicial + ", dataDeTermino=" + dataDeTermino + "]";
	// }
	//
	// @Override
	// public int hashCode() {
	// final int prime = 31;
	// int result = 1;
	// result = prime * result
	// + ((dataDeTermino == null) ? 0 : dataDeTermino.hashCode());
	// result = prime * result
	// + ((dataInicial == null) ? 0 : dataInicial.hashCode());
	// result = prime * result + (isAssegurado ? 1231 : 1237);
	// result = prime * result + (isTanqueCheio ? 1231 : 1237);
	// result = prime * result + ((preco == null) ? 0 : preco.hashCode());
	// return result;
	// }
	//
	// @Override
	// public boolean equals(Object obj) {
	// if (this == obj)
	// return true;
	// if (obj == null)
	// return false;
	// if (getClass() != obj.getClass())
	// return false;
	// Carro other = (Carro) obj;
	// if (dataDeTermino == null) {
	// if (other.dataDeTermino != null)
	// return false;
	// } else if (!dataDeTermino.equals(other.dataDeTermino))
	// return false;
	// if (dataInicial == null) {
	// if (other.dataInicial != null)
	// return false;
	// } else if (!dataInicial.equals(other.dataInicial))
	// return false;
	// if (isAssegurado != other.isAssegurado)
	// return false;
	// if (isTanqueCheio != other.isTanqueCheio)
	// return false;
	// if (preco != other.preco)
	// return false;
	// return true;
	// }

}// Carro

