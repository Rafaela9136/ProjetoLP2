package models;

import java.io.Serializable;

public enum TiposDeQuarto implements Serializable {
	SIMPLES(1, 5, true, "Simples"), DUPLO(2, 15, true, "Duplo"), TRIPLO(3, 20, false, "Triplo");
	
	private int MAX_HOSPEDES;
	private int TOTAL_DISPONIVEL;
	private boolean PERMITE_CAMA_EXTRA;
	private String nome;
	
	private TiposDeQuarto(int MAX_HOSPEDES, int TOTAL_DISPONIVEL, boolean PERMITE_CAMA_EXTRA, String nome) {
		this.MAX_HOSPEDES = MAX_HOSPEDES;
		this.TOTAL_DISPONIVEL = TOTAL_DISPONIVEL;
		this.PERMITE_CAMA_EXTRA = PERMITE_CAMA_EXTRA;
		this.nome = nome;
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
	
	public String getNome() {
		return nome;
	}
	
}
