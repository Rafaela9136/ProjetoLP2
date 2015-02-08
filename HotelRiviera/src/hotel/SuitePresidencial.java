package hotel;

/**
 * Representa uma suite presidencial do hotel. As suites presidenciais nao permitem cama extra como os outros quartos.
 *
 */
public class SuitePresidencial extends Quarto{
	
	private final static boolean TEM_CAMA_EXTRA = false;
	private final static double DIARIA_SUITE_PRESIDENCIAL = 1200.0;
	public final static int MAX_HOSPEDES = 4;	
	public final static int TOTAL_DISPONIVEL = 5;
	public static final String DESCRICAO_PRESIDENCIAL = Quarto.DESCRICAO + "As suites presidenciais podem comportar ate 4 pessoas "
			+ "em 2 quartos separados e uma sala de jogos e home theater, ideal para familias em ferias.";
	
	/**
	 * Construtor da classe SuitePresidencial.
	 */
	public SuitePresidencial() {
		super(TEM_CAMA_EXTRA);	
	}

	/**
	 * Informa o preco da diaria da suite presidencial. Esse preco e unico pois esse quarto nao possui categorias simples, duplo nem triplo..
	 * 
	 * @return O preco da diario da suite presidencial.
	 */
	@Override
	public double getPreco() {
		return DIARIA_SUITE_PRESIDENCIAL;
	}
		
}
