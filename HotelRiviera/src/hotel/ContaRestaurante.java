package hotel;

import java.io.Serializable;
import java.util.Calendar;

import excecoes.ValorNegativoException;

/**
 * 
 * @author Grupinho da alegria
 * @version Final.
 *
 */
public class ContaRestaurante implements Servico, Serializable {
	private Calendar data;
	private double valor;
	
	/**
	 * @param valor Valor da conta.
	 * @throws ValorNegativoException Excecao Lancada quando o valor a ser estabelecido e negativo.
	 */
	public ContaRestaurante(double valor) throws ValorNegativoException {
		verificaValorValido(valor);
		this.valor = valor;
		data = Calendar.getInstance();
	}// Construtor

	@Override
	public String toString() {
		return "ContaRestaurante" 
				+ "\nValor: " + valor 
				+ "\nData: " + data.get(Calendar.DAY_OF_MONTH)
				+ "/" + (data.get(Calendar.MONTH) + 1)
				+ "/" + data.get(Calendar.YEAR);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ContaRestaurante other = (ContaRestaurante) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}

	private void verificaValorValido(double valor)
			throws ValorNegativoException {
		if (valor < 0)
			throw new ValorNegativoException();
	}// verificaValorValido
	
	@Override
	public Calendar getDataCheckIn() {
		return data;
	}// getDataCheckIn
	
	@Override
	public Calendar getDataCheckOut() {
		return data;
	}// getDataCheckOut

	@Override
	public double getPreco() {
		return valor;
	}// getValor
	
	@Override
	public String getNome() {
		return "Conta do Restaurante";
	}// getNome

}// ContaRestaurante
