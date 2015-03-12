package hotel;

import java.io.Serializable;

import excecoes.ValorNegativoException;

/**
 * 
 * @author Grupinho da Alegria
 *
 */
public class Frigobar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
	
	/**
	 * 
	 * @return Nome da Classe
	 */
	public String getNome() {
		return "Frigobar";
	}// getNome
	
	/**
	 * Recuepra a conta do frigobar.
	 * @return  Returna a conta do frigobar.
	 */
	public double getPreco() {
		return preco;
	}// getPreco
	/**
	 * Metodo responsavel por somar algum valor ao preco do frigobar. E possivel somar valores negativos
	 * para que caso o usuario adicione algum valor errado ele possa consertar.
	 * 
	 * @param valor Valor a ser somado.
	 * @throws ValorNegativoException Lanca excecao quando a soma do preco e o valor e menor que zero.
	 */
	public void somaPreco(double valor) throws ValorNegativoException {
		if (valor + preco < 0)
			throw new ValorNegativoException();
		preco += valor;
	}// setPreco
	
	@Override
	public String toString() {
		return "Frigobar" + "\nPreco: " + preco;
	}

}// Frigobar