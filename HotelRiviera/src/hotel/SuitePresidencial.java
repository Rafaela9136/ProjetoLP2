package hotel;

import java.util.Calendar;

import excecoes.DataInvalidaException;

public class SuitePresidencial extends Quarto {

	public static final boolean PERMITE_CAMA_EXTRA = false;
	public static final double PRECO_SUITE_PRESIDENCIAL = 1200.0;

	public static final int MAX_HOSPEDES = 4;
	public static final int TOTAL_DISPONIVEL = 5;

	public static final String DESCRICAO_PRESIDENCIAL = Quarto.DESCRICAO
			+ "As suites presidenciais podem comportar ate 4 pessoas "
			+ "em 2 quartos separados e uma sala de jogos e home theater, ideal para familias em ferias.";

	public SuitePresidencial(Calendar inicioServico, Calendar terminoServico)
			throws NullPointerException, DataInvalidaException {
		super(PERMITE_CAMA_EXTRA, inicioServico, terminoServico);
	}// Construtor

	@Override
	public double getPreco() {
		return PRECO_SUITE_PRESIDENCIAL * getNumeroDeDias() + getFrigobar().getPreco();
	}// getPreco

}// SuitePresidencial