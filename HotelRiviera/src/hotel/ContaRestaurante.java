package hotel;

import java.util.Calendar;

import excecoes.ValorNegativoException;

public class ContaRestaurante implements Servico {
	private Calendar data;
	private double valor;
	
	public ContaRestaurante (double valor) throws Exception {
		verificaValorValido(valor);
		this.valor = valor;
		data = Calendar.getInstance();
	}// Construtor
	
	private void verificaValorValido(double valor) throws ValorNegativoException {
		if(valor < 0)
			throw new ValorNegativoException();
	}// verificaValorValido
	
	public Calendar getData() {
		return data;
	}// getData
	
	@Override
	public double getPreco() {
		return valor;
	}// getValor
	
	
}// ContaRestaurante
