package hotel;

import excecoes.CamaExtraException;

public class QuartoExecutivo extends Quarto {
	public static final int MAX_PESSOAS = 3;

	public QuartoExecutivo(boolean camaExtra, TipoDeQuarto tipo) throws CamaExtraException {
		super(MAX_PESSOAS, camaExtra, tipo);
		// TODO Auto-generated constructor stub
	}// Construtor

	@Override
	public String toString() {
		return super.toString() + "QuartoExecutivo";
	}

	@Override
	public double getPreco() {
		return getTipoDeQuarto().getPreco();
	}// getPreco

}// QuartoExecutivo
