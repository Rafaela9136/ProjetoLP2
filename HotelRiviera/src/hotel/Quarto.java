package hotel;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import excecoes.DataInvalidaException;
import excecoes.ValorNegativoException;

/**
 * * Representa um quarto do hotel. Ha tres tipos de quartos: executivo, luxo e
 * presidencial. Quartos do tipo executivo e luxo existem nas categorias
 * simples, duplo e triplo.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public abstract class Quarto implements Servico, Serializable {

	private static final long serialVersionUID = 1L;
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

	/**
	 * Metodo utilizado para somar um valor na conta do frigobar contido no
	 * quarto. E possivel passar um valor negativo como parametro.
	 * 
	 * @param valor
	 *            Valor a ser somado
	 * @throws ValorNegativoException
	 *             Se a conta do frigobar somado com o valor passado como
	 *             paramtro ficar negativo esta excecao sera lancada.
	 */
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
		if (camaExtra) {
			return "Sim";
		}
		return "Nao";
	}// resultadoCamaExtra

	/**
	 * Retorna uma representacao em String do objeto.
	 */
	@Override
	public String toString() {
		DecimalFormat formatovalor = new DecimalFormat("R$ #,##0.00");
		final String FIM_LINHA = System.getProperty("line.separator");
		return "CamaExtra: "
				+ resultadoCamaExtra()
				+ FIM_LINHA
				+ "Frigobar: "
				+ formatovalor.format(frigobar.getPreco())
				+ FIM_LINHA
				+ "Data Inicial: "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataCheckIn
						.getTime())
				+ FIM_LINHA
				+ "Data Final: "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataCheckOut
						.getTime());
	}

	private void verificaDatasValidas(Calendar dataCheckIn,
			Calendar dataCheckOut) throws DataInvalidaException,
			NullPointerException {
		Calendar dataAtual = new GregorianCalendar();

		dataAtual.set(Calendar.HOUR_OF_DAY, 0);
		dataAtual.set(Calendar.MINUTE, 0);
		dataAtual.set(Calendar.SECOND, 0);
		dataAtual.set(Calendar.MILLISECOND, 0);

		dataCheckIn.set(Calendar.HOUR_OF_DAY, 0);
		dataCheckIn.set(Calendar.MINUTE, 0);
		dataCheckIn.set(Calendar.SECOND, 0);
		dataCheckIn.set(Calendar.MILLISECOND, 0);

		dataCheckOut.set(Calendar.HOUR_OF_DAY, 23);
		dataCheckOut.set(Calendar.MINUTE, 59);

		if (dataCheckIn == null || dataCheckOut == null) {
			throw new NullPointerException();
		}
		if (dataCheckIn.before(dataAtual) || dataCheckOut.before(dataCheckIn)) {
			throw new DataInvalidaException();
		}
	}// verificaDatasValidas
}
