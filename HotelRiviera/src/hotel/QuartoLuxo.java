package hotel;

import java.util.Calendar;

import excecoes.CamaExtraException;
import excecoes.DataInvalidaException;

/**
 * Representa um quarto do tipo luxo. Os quartos luxos podem ser simples(1 hospede),
 * duplo (2 hospedes) ou triplo (tres hospedes).
 * 
 */
public class QuartoLuxo extends Quarto{
	
	public static final double DIARIA_LUXO_SIMPLES = 520.0;
	public static final double DIARIA_LUXO_DUPLO = 570.0;
	public static final double DIARIA_LUXO_TRIPLO = 620.0;
		
	public static final String DESCRICAO_EXECUTIVO = Quarto.DESCRICAO + " Os quartos do tipo luxo podem acomodar 1, 2 ou ate 3 hospedes."
			+ " Sao mais espacosos do que os executivos e contam com home theater. Camas extras para criancas menores de 9 anos sao "
			+ "permitidas em quartos do tipo simples e duplo quando o hospede solicitar na reserva.";
	
	private TiposDeQuarto tipoDeQuarto;
	
	/**
	 * Construtor da classe QuartoLuxo.
	 * 
	 * @param camaExtra
	 * 			Um boolean que indica se o cliente solicitou uma cama extra para o quarto.
	 * @param tipoDeQuarto
	 * 			indica o tipo de quarto (simples, duplo ou triplo). Cada tipo tem suas especificacoes.
	 * @throws NullPointerException 
	 * 			Excecao lancada quando algum dos parametros e null.
	 * 
	 * @throws DataInvalidaException Excecao lancada quando uma das datas passadas como parametro e invalida.
	 * 
	 */
	public QuartoLuxo(boolean temCamaExtra, TiposDeQuarto tipoDeQuarto, Calendar dataCheckIn, Calendar dataCheckOut) throws NullPointerException, CamaExtraException, DataInvalidaException {
		super(temCamaExtra, dataCheckIn, dataCheckOut);
		if (tipoDeQuarto == null) {
			throw new NullPointerException();
		} else if(!tipoDeQuarto.isPERMITE_CAMA_EXTRA() && temCamaExtra == true)
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
	}// getTipoDeQuarto
	
	@Override
	public String getNome() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return "Quarto Luxo Simples";
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return "Quarto Luxo Duplo";
		} else {
			return "Quarto Luxo Triplo";
		}
	}// getNome
	
	/**
	 * Informa o preco da diaria do quarto de acordo com o tipo de quarto, sendo o simples o mais barato e o triplo o mais caro.
	 * 
	 * @return O preco da diario do quarto.
	 */
	@Override
	public double getPreco() {
		if (tipoDeQuarto.equals(TiposDeQuarto.SIMPLES)) {
			return DIARIA_LUXO_SIMPLES + getFrigobar().getPreco();
		} else if (tipoDeQuarto.equals(TiposDeQuarto.DUPLO)) {
			return DIARIA_LUXO_DUPLO + getFrigobar().getPreco();
		} else {
			return DIARIA_LUXO_TRIPLO + getFrigobar().getPreco();
		}
	}
	
	/**
	 * Compara dois objetos QuartoLuxo e informa se sao iguais ou nao.
	 * Dois quartos Luxos s�o iguais se forem iguais no preco, na solicitacao de cama extra e no tipo (simples, duplo ou triplo).
	 * 
	 * @return true se os quartos forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof QuartoLuxo)) {
			return false;
		}	
		QuartoLuxo outro = (QuartoLuxo) obj;
		return super.equals(outro) && this.tipoDeQuarto.equals(outro.getTipoDeQuarto());
	}
	
	
}
