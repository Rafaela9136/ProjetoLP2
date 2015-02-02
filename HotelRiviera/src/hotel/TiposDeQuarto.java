package hotel;

public enum TiposDeQuarto {
	SIMPLES(1, 5, true), DUPLO(2, 15, true), TRIPLO(3, 20, false);
	
	private final int MAX_HOSPEDES;
	private final int TOTAL_DISPONIVEL;
	private final boolean PERMITE_CAMA_EXTRA;
	
	private TiposDeQuarto(int MAX_HOSPEDES, int TOTAL_DISPONIVEL, boolean PERMITE_CAMA_EXTRA) {
		this.MAX_HOSPEDES = MAX_HOSPEDES;
		this.TOTAL_DISPONIVEL = TOTAL_DISPONIVEL;
		this.PERMITE_CAMA_EXTRA = PERMITE_CAMA_EXTRA;
	}

	public int getMAX_HOSPEDES() {
		return MAX_HOSPEDES;
	}

	public int getTOTAL_DISPONIVEL() {
		return TOTAL_DISPONIVEL;
	}

	public boolean isPERMITE_CAMA_EXTRA() {
		return PERMITE_CAMA_EXTRA;
	}
	
}
