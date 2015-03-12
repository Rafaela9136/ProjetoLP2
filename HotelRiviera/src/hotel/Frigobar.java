package hotel;

import java.io.Serializable;

import excecoes.ValorNegativoException;

/**
 * Classe de um subservico dos quartos do hotel.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public class Frigobar implements Serializable {

	private static final long serialVersionUID = 1L;
	private double preco;

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Frigobar)) {
			return false;
		}
		Frigobar outro = (Frigobar) obj;
		return getPreco() == outro.getPreco();
	}

	/**
	 * Recupera o noem da classe.
	 * 
	 * @return Nome da Classe
	 */
	public String getNome() {
		return "Frigobar";
	}// getNome

	/**
	 * Recuepra a conta do frigobar.
	 * 
	 * @return Returna a conta do frigobar.
	 */
	public double getPreco() {
		return preco;
	}// getPreco

	/**
	 * Metodo responsavel por somar algum valor ao preco do frigobar. E possivel
	 * somar valores negativos para que caso o usuario adicione algum valor
	 * errado ele possa consertar.
	 * 
	 * @param valor
	 *            Valor a ser somado.
	 * @throws ValorNegativoException
	 *             Lanca excecao quando a soma do preco e o valor e menor que
	 *             zero.
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