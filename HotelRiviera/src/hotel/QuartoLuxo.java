package hotel;

import java.util.List;

import excecoes.CamaExtraException;

public class QuartoLuxo extends Quarto {
	@Override
	public String toString() {
		return super.toString() +  "QuartoLuxo";
	}

	public static final int MAX_PESSOAS = 3;
	
	public QuartoLuxo(boolean camaExtra, TipoDeQuarto tipo) throws CamaExtraException {
		super(MAX_PESSOAS, camaExtra, tipo);
		// TODO Auto-generated constructor stub
	}

	/*
	 * esses atributos devem ser adicionados na List<String> atributos do quarto
	private String homeTheater = "Home Theater";
	*/

	@Override
	public double getPreco() {
		return getTipoDeQuarto().getPreco();
	}// getPreco
	
		
}// QuartoLuxo
