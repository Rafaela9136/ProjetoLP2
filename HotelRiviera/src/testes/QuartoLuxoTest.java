package testes;

import org.junit.*;

import excecoes.*;
import hotel.*;

public class QuartoLuxoTest {

	private Quarto quarto1, quarto2, quarto3;

	private final boolean TEM_CAMA_EXTRA = true;
	private final boolean NAO_TEM_CAMA_EXTRA = false;

	@Before
	public void criaObjetos() throws NullPointerException, CamaExtraException {
		quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO);
		quarto3 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);

	}// criaObjetos

	@Test
	public void testCriaQuartoExecutivo() throws CamaExtraException {
		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);
		} catch (CamaExtraException e) {
			Assert.assertTrue(true);
		}
	}// testCriaQuartoExecutivo

	@Test
	public void testaGetPreco() {
		Assert.assertEquals(quarto1.getPreco(), QuartoLuxo.DIARIA_LUXO_SIMPLES,
				0.001);
		Assert.assertEquals(quarto2.getPreco(), QuartoLuxo.DIARIA_LUXO_DUPLO,
				0.001);
		Assert.assertEquals(quarto3.getPreco(), QuartoLuxo.DIARIA_LUXO_TRIPLO,
				0.001);
	}

	@Test
	public void testEquals() throws NullPointerException, CamaExtraException {
		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		Assert.assertTrue(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO);
		Assert.assertFalse(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		Assert.assertFalse(quarto1.equals(quarto2));
	}// testEquals

}// TestQuartoExecutivo

