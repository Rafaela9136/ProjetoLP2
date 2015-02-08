package hotel;

public class SuitePresidencial extends Quarto{
	
	private final static boolean TEM_CAMA_EXTRA = false;
	private final static double DIARIA_SUITE_PRESIDENCIAL = 1200.0;
	public final static int MAX_HOSPEDES = 4;
	
	public final static int TOTAL_DISPONIVEL = 5;
	
	public static final String DESCRICAO_PRESIDENCIAL = Quarto.DESCRICAO + "As suites presidenciais podem comportar ate 4 pessoas "
			+ "em 2 quartos separados e uma sala de jogos e home theater, ideal para familias em ferias.";
	
	public SuitePresidencial() {
		super(TEM_CAMA_EXTRA);	
	}

	@Override
	public double getPreco() {
		return DIARIA_SUITE_PRESIDENCIAL;
	}
		
}
