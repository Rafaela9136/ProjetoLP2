package testes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CamaExtraException;
import excecoes.ValorNegativoException;
import hotel.QuartoExecutivo;
import hotel.TiposQuartosExecutivo;

public class TestaQuartoExecutivo {
	QuartoExecutivo quarto1;
	QuartoExecutivo quarto2;
	
	@Before
	public void criaQuartoExecutivo() throws Exception {
		quarto1 = new QuartoExecutivo(false, TiposQuartosExecutivo.EXECUTIVO_SIMPLES);
			
		quarto2 = new QuartoExecutivo(false, TiposQuartosExecutivo.EXECUTIVO_DUPLO);
	}// criaQuartoExecutivo
	
	@Test
	public void testaCriaQuartoExecutivo() {
		try{
			quarto1 = new QuartoExecutivo(true, TiposQuartosExecutivo.EXECUTIVO_TRIPLO);
			Assert.fail("Deveria ter lancado excecao");
		} catch(CamaExtraException e) {
			
		}// try-catch
	
	}// testaCriaQuartoExecutivo
	
	@Test
	public void testasomaPrecoFrigobar() throws CamaExtraException, ValorNegativoException {
		quarto1 = new QuartoExecutivo(false, TiposQuartosExecutivo.EXECUTIVO_SIMPLES);
		
		quarto1.somaPrecoFrigobar(80);
		Assert.assertEquals(80, quarto1.getPrecoFrigobar(), 0.002);
		
		quarto1.somaPrecoFrigobar(-80);
		Assert.assertEquals(0, quarto1.getPrecoFrigobar(), 0.002);
		
		try {
			quarto1.somaPrecoFrigobar(-1);
		} catch (ValorNegativoException e) {
			
		}// try-catch
		
	}// testaSetPrecoFrigobar
	
	

}// TestaQuarto
