package hotel;

import excecoes.CamaExtraException;

public class QuartoExecutivo extends Quarto {
	public static final int MAX_PESSOAS = 3;

	public QuartoExecutivo(boolean temCamaExtra, TiposQuartosExecutivo tipo)
			throws CamaExtraException {
		super(MAX_PESSOAS, temCamaExtra, tipo);

	}// Construtor

}// QuartoExecutivo
