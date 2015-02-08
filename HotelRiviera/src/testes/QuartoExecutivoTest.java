package testes;

import org.junit.*;

import excecoes.CamaExtraException;
import excecoes.ValorNegativoException;
import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.TiposDeQuarto;

public class QuartoExecutivoTest {
	private Quarto quarto1;
	private Quarto quarto2;

	private final boolean TEM_CAMA_EXTRA = true;
	private final boolean NAO_TEM_CAMA_EXTRA = false;

	@Before
	public void criaObjetos() throws NullPointerException, CamaExtraException {
		quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);

	}// criaObjetos

	@Test
	public void testCriaQuartoExecutivo() {
		try {
			quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, null);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {
			// TODO: handle exception
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		try {
			quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);
			Assert.fail("Deveria ter lancado CamaExtraException");
		} catch (CamaExtraException e) {
			// TODO: handle exception
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch
	}// testCriaQuartoExecutivo
	
	@Test
	public void testEquals() throws NullPointerException, CamaExtraException {
		quarto2 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		Assert.assertTrue(quarto1.equals(quarto2));
		
		quarto2 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO);
		Assert.assertFalse(quarto1.equals(quarto2));
		
		quarto2 = new QuartoExecutivo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		Assert.assertFalse(quarto1.equals(quarto2));
	}// testEquals

}// TestQuartoExecutivo
