package testes;

import org.junit.Before;

import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.TipoDeQuarto;

public class TestaQuartoExecutivo {
	QuartoExecutivo quarto1;
	QuartoExecutivo quarto2;
	
	@Before
	public void criaQuartoExecutivo() throws Exception {
		quarto1 = new Quarto(false. TipoDeQuarto.Executivo);
			
		quarto2 = new QuartoExecutivo(false, TipoDeQuarto.EXECUTIVO_SIMPLES);
	}// criaQuartoExecutivo
	

}// TestaQuarto
