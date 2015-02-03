package hotel;

public class SuitePresidencial extends Quarto{
	
	private final static boolean TEM_CAMA_EXTRA = false;
	private final double PRECO_SUITE_PRESIDENCIAL = 1200.0;
	private final int MAX_HOSPEDES = 4;
	private final int TOTAL_DISPONIVEL = 5;
	
	public SuitePresidencial() {
		super(TEM_CAMA_EXTRA);	
	}

	@Override
	public double getPreco() {
		return PRECO_SUITE_PRESIDENCIAL;
	}
	
	public int getMAX_HOSPEDES() {
		return MAX_HOSPEDES;
	}

	public int getTOTAL_DISPONIVEL() {
		return TOTAL_DISPONIVEL;
	}

}