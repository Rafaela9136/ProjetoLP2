package hotel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import excecoes.DataInvalidaException;

public class Carro implements Servico {
	public static final double VALOR_TANQUE_CHEIO = 150;
	public static final double VALOR_DO_SEGURO = 100;
	public static final int MILISSEGUNDOS_EM_UM_DIA = 86400000;

	private TipoCarro tipoDeCarro;
	private boolean isTanqueCheio;
	private boolean isAssegurado;
	private Calendar dataInicio;
	private Calendar dataTermino;
	private int numDeDias;

	public Carro(TipoCarro tipoDeCarro, Calendar dataInicio,
			Calendar dataTermino, boolean isTanqueCheio, boolean isAssegurado)
			throws NullPointerException, DataInvalidaException {
		verificaTipo(tipoDeCarro);
		verificaData(dataInicio, dataTermino);
		this.tipoDeCarro = tipoDeCarro;
		this.isTanqueCheio = isTanqueCheio;
		this.isAssegurado = isAssegurado;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		numDeDias = 0;
	}

	public TipoCarro getTipoDeCarro() {
		return tipoDeCarro;
	}

	public void setDataInicio(Calendar novaDataInicio)
			throws NullPointerException, DataInvalidaException {
		verificaData(novaDataInicio, dataTermino);
		dataInicio = novaDataInicio;
	}

	public void setDataTermino(Calendar novaDataTermino)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataInicio, novaDataTermino);
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
		double preco = numeroDeDias() * tipoDeCarro.getPreco();
		if (isTanqueCheio)
			preco += VALOR_TANQUE_CHEIO;
		if (isAssegurado)
			preco += VALOR_DO_SEGURO;
		return preco;
	}

	public int numeroDeDias() {
		long diaInicial = getDataInicio().getTimeInMillis();
		long diaFinal = getDataTermino().getTimeInMillis();
		numDeDias = (int) ((diaFinal - diaInicial) / MILISSEGUNDOS_EM_UM_DIA);
		return numDeDias;
	}

	private void verificaData(Calendar dataInicio, Calendar dataTermino)
			throws DataInvalidaException {
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

	@Override
	public String toString() {
		List<String> adicionais = new ArrayList<String>();
		if (isTanqueCheio)
			adicionais.add("Tanque cheio = R$" + VALOR_TANQUE_CHEIO);
		if (isAssegurado)
			adicionais.add("Seguro = R$" + VALOR_DO_SEGURO);
		return "SERVIÇO CARRO" + "\nTipo: " + tipoDeCarro.getTipoNome() + "\n"
				+ "Adicionais: " + Arrays.toString(adicionais.toArray())
				+ "\nPreco Total: R$ " + getPreco() + "\nDuração: "
				+ numDeDias + " dias" + "\nData Inicio: "
				+ getDataInicio().get(Calendar.DAY_OF_MONTH) + "/"
				+ (getDataInicio().get(Calendar.MONTH) + 1) + "/"
				+ getDataInicio().get(Calendar.YEAR) + "\nData Termino: "
				+ getDataTermino().get(Calendar.DAY_OF_MONTH) + "/"
				+ (getDataTermino().get(Calendar.MONTH) + 1) + "/"
				+ getDataTermino().get(Calendar.YEAR);
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

		// return (getTipoDeCarro().equals(outroCarro.getTipoDeCarro())
		// && getIsTanqueCheio() == outroCarro.getIsTanqueCheio()
		// && getIsAssegurado() == outroCarro.getIsAssegurado()
		// && getPreco() == outroCarro.getPreco()
		// && getDataInicio().get(Calendar.YEAR) == outroCarro
		// .getDataInicio().get(Calendar.YEAR)
		// && getDataInicio().get(Calendar.DAY_OF_YEAR) == outroCarro
		// .getDataInicio().get(Calendar.DAY_OF_YEAR)
		// && getDataInicio().get(Calendar.HOUR_OF_DAY) == outroCarro
		// .getDataInicio().get(Calendar.HOUR_OF_DAY)
		// && getDataInicio().get(Calendar.MINUTE) == outroCarro
		// .getDataInicio().get(Calendar.MINUTE)
		// && getDataTermino().get(Calendar.YEAR) == outroCarro
		// .getDataTermino().get(Calendar.YEAR)
		// && getDataTermino().get(Calendar.DAY_OF_YEAR) == outroCarro
		// .getDataTermino().get(Calendar.DAY_OF_YEAR)
		// && getDataTermino().get(Calendar.HOUR_OF_DAY) == outroCarro
		// .getDataTermino().get(Calendar.HOUR_OF_DAY) && getDataTermino()
		// .get(Calendar.MINUTE) == outroCarro.getDataTermino().get(
		// Calendar.MINUTE));
	}

}// Carro

