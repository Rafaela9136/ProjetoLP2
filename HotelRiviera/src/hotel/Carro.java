package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import excecoes.DataInvalidaException;

public class Carro implements Servico {
	public static final boolean TANQUE_VAZIO = false;
	public static final boolean NAO_ASSEGURADO = false;

	public static final double VALOR_TANQUE_CHEIO = 150;
	public static final double VALOR_ASSEGURADO = 100;

	private TipoCarro tipoDeCarro;
	private boolean isTanqueCheio;
	private boolean isAssegurado;
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
		return preco;
	}

	public int numeroDeDias() {
		int diaInicial = getDataInicio().get(Calendar.DAY_OF_YEAR);
		int diaFinal = getDataTermino().get(Calendar.DAY_OF_YEAR);
		numDeDias = diaFinal - diaInicial;
		return numDeDias;
	}

	private void calculaPreco() {
		preco = numeroDeDias() * tipoDeCarro.getPreco();
		if (isTanqueCheio)
			preco += VALOR_TANQUE_CHEIO;
		if (isAssegurado)
			preco += VALOR_ASSEGURADO;
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

	@Override
	public String toString() {
		return "Carro [tipoDeCarro=" + tipoDeCarro + ", isTanqueCheio="
				+ isTanqueCheio + ", isAssegurado=" + isAssegurado + ", preco="
				+ preco + ", dataInicio=" + dataInicio + ", dataTermino="
				+ dataTermino + ", numDeDias=" + numDeDias + "]";
	}

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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Carro))
			return false;
		Carro outroCarro = (Carro) obj;
		boolean datasIguais = false;
		if (getDataInicio().compareTo(outroCarro.getDataInicio()) == 0
				&& getDataTermino().compareTo(outroCarro.getDataTermino()) == 0) {
			datasIguais = true;
		}
		return (getTipoDeCarro().equals(outroCarro.getTipoDeCarro())
				&& getIsTanqueCheio() == outroCarro.getIsTanqueCheio()
				&& getIsAssegurado() == outroCarro.getIsAssegurado()
				&& getPreco() == outroCarro.getPreco() && datasIguais);

//		return (getTipoDeCarro().equals(outroCarro.getTipoDeCarro())
//				&& getIsTanqueCheio() == outroCarro.getIsTanqueCheio()
//				&& getIsAssegurado() == outroCarro.getIsAssegurado()
//				&& getPreco() == outroCarro.getPreco()
//				&& getDataInicio().get(Calendar.YEAR) == outroCarro
//						.getDataInicio().get(Calendar.YEAR)
//				&& getDataInicio().get(Calendar.DAY_OF_YEAR) == outroCarro
//						.getDataInicio().get(Calendar.DAY_OF_YEAR)
//				&& getDataInicio().get(Calendar.HOUR_OF_DAY) == outroCarro
//						.getDataInicio().get(Calendar.HOUR_OF_DAY)
//				&& getDataInicio().get(Calendar.MINUTE) == outroCarro
//						.getDataInicio().get(Calendar.MINUTE)
//				&& getDataTermino().get(Calendar.YEAR) == outroCarro
//						.getDataTermino().get(Calendar.YEAR)
//				&& getDataTermino().get(Calendar.DAY_OF_YEAR) == outroCarro
//						.getDataTermino().get(Calendar.DAY_OF_YEAR)
//				&& getDataTermino().get(Calendar.HOUR_OF_DAY) == outroCarro
//						.getDataTermino().get(Calendar.HOUR_OF_DAY) && getDataTermino()
//				.get(Calendar.MINUTE) == outroCarro.getDataTermino().get(
//				Calendar.MINUTE));
	}

}// Carro

