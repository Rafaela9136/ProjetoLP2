package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

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

	public Carro(TipoCarro tipoDeCarro, Calendar dataTermino,
			boolean isTanqueCheio, boolean isAssegurado) {
		verificaDataTermino(dataTermino);
		this.tipoDeCarro = tipoDeCarro;
		this.isTanqueCheio = isTanqueCheio;
		this.isAssegurado = isAssegurado;
		this.dataTermino = dataTermino;
		preco = 0;
		dataInicio = new GregorianCalendar();

		if (isTanqueCheio)
			preco += VALOR_TANQUE_CHEIO;
		if (isAssegurado)
			preco += VALOR_ASSEGURADO;

	}// Construtor

	private void verificaDataTermino(Calendar dataTermino) {
		if (dataTermino == null)
			throw new NullPointerException();
	}

	public Carro(TipoCarro tipoDeCarro, Calendar dataTermino) {
		this(tipoDeCarro, dataTermino, TANQUE_VAZIO, NAO_ASSEGURADO);
	}// Construtor (Default)

	// public void setDataInicial(Calendar novaDataInicio) {
	// dataInicio = novaDataInicio;
	// }// setDataInicial

	public void setDataDeTermino(Calendar novaDataTermino) {
		dataTermino = novaDataTermino;

	}// getDataTerminoDoServico

	public Calendar getDataInicio() {
		return dataInicio;
	}// getDataInicial

	public Calendar getDataTermino() {
		return dataTermino;
	}// getDataDeTermino

	public boolean getIsTanqueCheio() {
		return isTanqueCheio;
	}// getIsTanqueCheio

	public boolean getIsAssegurado() {
		return isAssegurado;
	}// getIsAssegurado

	@Override
	public double getPreco() {
		calculaPreco();
		return preco;
	}// getPreco

	private void calculaPreco() {
		int diaInicial = getDataInicio().get(Calendar.DAY_OF_YEAR);
		int diaFinal = getDataTermino().get(Calendar.DAY_OF_YEAR);
		preco += (diaFinal - diaInicial) * tipoDeCarro.getPreco();

	}// calculaPreco

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

