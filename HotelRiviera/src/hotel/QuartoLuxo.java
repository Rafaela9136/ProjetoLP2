package hotel;

import excecoes.CamaExtraException;

public class QuartoLuxo extends Quarto {
	@Override
	public String toString() {
		return super.toString() + "QuartoLuxo";
	}

	public static final int MAX_PESSOAS = 3;

	public QuartoLuxo(boolean camaExtra, TiposQuartosLuxo tipo)
			throws CamaExtraException {
		super(MAX_PESSOAS, camaExtra, tipo);
		// TODO Auto-generated constructor stub
	}

}// QuartoLuxo
