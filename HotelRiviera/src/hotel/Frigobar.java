package hotel;

import excecoes.ValorNegativoException;


public class Frigobar implements Servico {
	private double preco;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(preco);
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
		Frigobar other = (Frigobar) obj;
		if (Double.doubleToLongBits(preco) != Double
				.doubleToLongBits(other.preco))
			return false;
		return true;
	}

	@Override
	public double getPreco() {
		return preco;
	}// getPreco
	
	public void setPreco(double valor) throws ValorNegativoException {
		if(valor < 0)
			throw new ValorNegativoException();
		preco = valor;
	}// setPreco


}// Frigobar
