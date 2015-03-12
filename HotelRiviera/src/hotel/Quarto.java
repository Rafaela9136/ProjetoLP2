package hotel;

import java.io.Serializable;
import java.util.Calendar;

import excecoes.DataInvalidaException;
import excecoes.ValorNegativoException;

/*
 * Representa um quarto do hotel. Ha tres tipos de quartos: executivo, luxo e presidencial.
 * Quartos do tipo executivo e luxo existem nas categorias simples, duplo e triplo.
 */
public abstract class Quarto implements Servico, Serializable {

	public static final String DESCRICAO = "As acomodacoes do hotel sao todas novas, equipadas com TV LCD 42'', split, frigobar, cofre.";
	private boolean camaExtra;
	private Frigobar frigobar;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;

	/**
	 * Construtor da classe Quarto.
	 * 
	 * @param camaExtra
	 *            Um boolean que indica se o cliente solicitou uma cama extra
	 *            para o quarto.
	 */
	public Quarto(boolean camaExtra, Calendar dataCheckIn, Calendar dataCheckOut)
			throws NullPointerException, DataInvalidaException {
		verificaDatasValidas(dataCheckIn, dataCheckOut);

		this.dataCheckIn = dataCheckIn;
		this.dataCheckOut = dataCheckOut;
		this.camaExtra = camaExtra;
		this.frigobar = new Frigobar();
	}// Construtor

	/**
	 * Recupera o frigobar do quarto.
	 * 
	 * @return Retorna o frigobar do quarto
	 */
	public Frigobar getFrigobar() {
		return frigobar;
	}// getFrigobar

	@Override
	public Calendar getDataCheckIn() {
		return dataCheckIn;
	}// getDataCheckIn

	@Override
	public Calendar getDataCheckOut() {
		return dataCheckOut;
	}// getDataCheckOut

	/**
	 * Estabelece o frigobar do quarto.
	 * 
	 * @param frigobar
	 *            Frigobar a ser estabelecido.
	 */
	public void setFrigobar(Frigobar frigobar) {
		this.frigobar = frigobar;
	}// setFrigobar

	/**
	 * Calcula e deve retornar o preco da diario do quarto.
	 */
	public abstract double getPreco();

	/**
	 * Informa se o cliente solicitou uma cama extra no quarto.
	 * 
	 * @return Retorna true se o cliente solicitou uma cama extra no quarto.
	 */
	public boolean isCamaExtra() {
		return camaExtra;
	}
	
	public void somaPrecoFrigobar(double valor) throws ValorNegativoException {
		frigobar.somaPreco(valor);
	}

	/**
	 * Define um novo valor para a variavel "camaExtra", que informa se o
	 * hospede solicitou ou nao uma cama extra para o quarto.
	 * 
	 * @param camaExtra
	 *            O novo valor (true/false) a ser definido para a camaExtra.
	 */
	public void setCamaExtra(boolean temCamaExtra) {
		this.camaExtra = temCamaExtra;
	}

	/**
	 * Compara dois quartos e informa se se sao iguais ou nao.
	 * 
	 * @return true se os quartos comparados forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Quarto)) {
			return false;
		}
		Quarto outro = (Quarto) obj;
		return (this.getPreco() == outro.getPreco()
				&& this.isCamaExtra() == outro.isCamaExtra()
				&& this.getDataCheckIn().equals(outro.getDataCheckIn()) && this
				.getDataCheckOut().equals(outro.getDataCheckOut()));
	}
	
	private String resultadoCamaExtra() {
		if(camaExtra)
			return "Sim";
		return "Nao";
	}// resultadoCamaExtra

	/**
	 * Retorna uma representacao em String do objeto.
	 */
	@Override
	public String toString() {
		return "\nCamaExtra: " + resultadoCamaExtra()
				+ "\nFrigobar: " + frigobar.getPreco()
				+ "\nData Inicial: " + dataCheckIn.get(Calendar.DAY_OF_MONTH)
				+ "/" + (dataCheckIn.get(Calendar.MONTH) + 1)
				+ "/" + dataCheckIn.get(Calendar.YEAR) 
				+ "\nData Final: " + dataCheckOut.get(Calendar.DAY_OF_MONTH)
				+ "/" + (dataCheckOut.get(Calendar.MONTH) + 1)
				+ "/" + dataCheckOut.get(Calendar.YEAR);
	}

	// adicionar testes referente a essas excecoes
	private void verificaDatasValidas(Calendar dataCheckIn,
			Calendar dataCheckOut) throws DataInvalidaException,
			NullPointerException {
		if (dataCheckIn == null || dataCheckOut == null)
			throw new NullPointerException();
		if (dataCheckOut.before(dataCheckIn)
				|| dataCheckIn.before(Calendar.getInstance()))
			throw new DataInvalidaException();
	}// verificaDatasValidas



}
