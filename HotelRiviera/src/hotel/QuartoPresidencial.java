package hotel;

import excecoes.CamaExtraException;

public class QuartoPresidencial extends Quarto {
	public QuartoPresidencial(boolean camaExtra, TipoDeQuarto tipo) throws CamaExtraException {
		super(MAX_PESSOAS, camaExtra, tipo);
		// TODO Auto-generated constructor stub
	}

	public static final int MAX_PESSOAS = 4;

	@Override
	public String toString() {
		return super.toString() + "QuartoPresidencial";
	}


}// Presidencial
