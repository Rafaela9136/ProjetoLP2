package models;

import java.io.Serializable;
import java.util.Calendar;

import exceptions.CamaExtraException;
import exceptions.DataInvalidaException;

/**
 * Representa um quarto do tipo executivo. Os quartos executivos podem ser
 * simples(1 hospede), duplo (2 hospedes) ou triplo (tres hospedes).
 * 
 */
public class QuartoExecutivo extends Quarto {

	public static final double DIARIA_EXECUTIVO_SIMPLES = 360.0;
	public static final double DIARIA_EXECUTIVO_DUPLO = 385.0;
	public static final double DIARIA_EXECUTIVO_TRIPLO = 440.0;

	public static final String DESCRICAO_EXECUTIVO = Quarto.DESCRICAO
			+ " Os quartos do tipo executivo podem acomodar 1, 2 ou ate 3 hospedes."
			+ "Camas extras para criancas menores de 9 anos sao permitidas em quartos do tipo simples e duplo quando o hospede solicitar na reserva.";

	private TiposDeQuarto tipoDeQuarto;

	/**
	 * Construtor da classe QuartoExecutivo.
	 * 
	 * @param camaExtra
	 *            Um boolean que indica se o cliente solicitou uma cama extra
	 *            para o quarto.
	 * @param tipoDeQuarto
	 *            indica o tipo de quarto (simples, duplo ou triplo). Cada tipo
	 *            tem suas especificacoes.
	 * @throws NullPointerException 
	 * 			Excecao lancada quando algum dos parametros e null.
	 * 
	 * @throws DataInvalidaException Excecao lancada quando uma das datas passadas como parametro e invalida.
	 * 
	 */
	public QuartoExecutivo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto,
			Calendar dataCheckIn, Calendar dataCheckOut)
			throws NullPointerException, CamaExtraException, DataInvalidaException {
		super(temCamaExtra, dataCheckIn, dataCheckOut);
		if (tipoDeQuarto == null) {
			throw new NullPointerException();
		} else if (!tipoDeQuarto.isPERMITE_CAMA_EXTRA() && temCamaExtra == true)
			throw new CamaExtraException();

		this.tipoDeQuarto = tipoDeQuarto;
	}

	/**
	 * Informa a categoria do quarto (simples, duplo, triplo).
	 * 
	 * @return a categoria do quarto (simples, duplo, triplo).
	 * 
	 */
	public TiposDeQuarto getTipoDeQuarto() {
		return tipoDeQuarto;
	}

	/**
	 * Informa o preco da diaria do quarto de acordo com o tipo de quarto, sendo
	 * o simples o mais barato e o triplo o mais caro.
	 * 
	 * @return O preco da diario do quarto.
	 */
	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return DIARIA_EXECUTIVO_SIMPLES;
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return DIARIA_EXECUTIVO_DUPLO;
		} else {
			return DIARIA_EXECUTIVO_TRIPLO;
		}
	}// getPreco

	@Override
	public String getNome() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return "Quarto Executivo Simples";
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return "Quarto Executivo Duplo";
		} else {
			return "Quarto Executivo Triplo";
		}
	}// getNome

	/**
	 * Compara dois objetos QuartoExecutivo e informa se sao iguais ou nao. Dois
	 * quartos Executivos s�o iguais se forem iguais no preco, na solicitacao de
	 * cama extra e no tipo (simples, duplo ou triplo).
	 * 
	 * @return true se os quartos forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuartoExecutivo)) {
			return false;
		}
		QuartoExecutivo outro = (QuartoExecutivo) obj;
		return super.equals(outro)
				&& this.tipoDeQuarto.equals(outro.getTipoDeQuarto());
	}
	
	@Override
	public String toString() {
		return "\nQuarto Executivo " + tipoDeQuarto.getNome()+ ": " + getPreco() + super.toString();
	}

}
