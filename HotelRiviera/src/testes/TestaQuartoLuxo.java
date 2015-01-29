package testes;

import hotel.QuartoLuxo;
import hotel.TiposQuartosLuxo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CamaExtraException;
import excecoes.ValorNegativoException;



public class TestaQuartoLuxo {
	QuartoLuxo quarto1;
	QuartoLuxo quarto2;
	
	@Before
	public void criaQuartoExecutivo() throws Exception {
		quarto1 = new QuartoLuxo(false, TiposQuartosLuxo.LUXO_SIMPLES);
			
	}// criaQuartoExecutivo
	
	@Test
	public void testaCriaQuartoExecutivo() {
		try{
			quarto1 = new QuartoLuxo(true, TiposQuartosLuxo.LUXO_TRIPLO);
			Assert.fail("Deveria ter lancado excecao");
		} catch(CamaExtraException e) {
			
		}// try-catch
	
	}// testaCriaQuartoExecutivo
	
	@Test
	public void testaSomaPrecoFrigobar() throws CamaExtraException, ValorNegativoException {
		quarto1 = new QuartoLuxo(false, TiposQuartosLuxo.LUXO_SIMPLES);
		
		quarto1.somaPrecoFrigobar(80);
		Assert.assertEquals(80, quarto1.getPrecoFrigobar(), 0.002);
		
		quarto1.somaPrecoFrigobar(-80);
		Assert.assertEquals(0, quarto1.getPrecoFrigobar(), 0.002);
		
		try {
			quarto1.somaPrecoFrigobar(-1);
		} catch (ValorNegativoException e) {
			
		}// try-catch
		
	}// testaSetPrecoFrigobar
	
	@Test
	public void testaEquals() throws Exception {
		quarto1 = new QuartoLuxo(false, TiposQuartosLuxo.LUXO_SIMPLES);
		
		quarto2 = new QuartoLuxo(false, TiposQuartosLuxo.LUXO_DUPLO);
		
		Assert.assertFalse(quarto1.equals(quarto2));
		
		quarto2 = new QuartoLuxo(false, TiposQuartosLuxo.LUXO_SIMPLES);
		
		Assert.assertTrue(quarto1.equals(quarto2));
	}// testaEquals
	
	

}// TestaQuartoExecutivo
