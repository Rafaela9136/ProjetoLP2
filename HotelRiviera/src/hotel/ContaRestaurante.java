package hotel;

import java.util.Calendar;

import excecoes.ValorNegativoException;

/**
 * 
 * @author Grupinho da alegria
 * @version Final.
 *
 */
public class ContaRestaurante implements Servico {
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
