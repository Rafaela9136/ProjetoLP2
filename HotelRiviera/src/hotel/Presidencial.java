package hotel;

import excecoes.CamaExtraException;

public class Presidencial extends Quarto {
	
	public Presidencial(int MAX_PESSOAS, boolean camaExtra, TipoDeQuarto tipo) throws CamaExtraException {
		super(MAX_PESSOAS, camaExtra, tipo);
		// TODO Auto-generated constructor stub
	}

	public static final int MAX_PESSOAS = 4;
	
	/*
	String quartos = "2 quartos separados";
	String salaDeJogos = "Sala de jogos";
	*/
	
	@Override
	public double getPreco() {
		return getTipoDeQuarto().getPreco();
	}// getPreco
	
}// Presidencial
