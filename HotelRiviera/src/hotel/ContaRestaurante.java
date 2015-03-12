package hotel;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import excecoes.ValorNegativoException;

/**
 * Classe referente a um servico de restaurante do hotel que pode ser oferecido
 * ao hospede e ter seu valor adicionado na conta.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public class ContaRestaurante implements Servico, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calendar data;
	private double valor;

	/**
	 * @param valor
	 *            Valor da conta.
	 * @throws ValorNegativoException
	 *             Excecao Lancada quando o valor a ser estabelecido e negativo.
	 */
	public ContaRestaurante(double valor) throws ValorNegativoException {
		verificaValorValido(valor);
		this.valor = valor;
		data = Calendar.getInstance();
	}// Construtor

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

	private void verificaValorValido(double valor)
			throws ValorNegativoException {
		if (valor < 0)
			throw new ValorNegativoException();
	}// verificaValorValido

	@Override
	public String toString() {
		DecimalFormat formatovalor = new DecimalFormat("R$ #,##0.00");
		final String FIM_LINHA = System.getProperty("line.separator");
		return "Conta do Restaurante" + FIM_LINHA + "Valor: "
				+ formatovalor.format(valor) + FIM_LINHA + "Data: "
				+ new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
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
		if (!(obj instanceof ContaRestaurante)) {
			return false;
		}
		ContaRestaurante outra = (ContaRestaurante) obj;
		return getPreco() == outra.getPreco();
	}

}// ContaRestaurante
