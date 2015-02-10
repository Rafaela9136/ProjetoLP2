package testes;

import org.junit.*;

import excecoes.CamaExtraException;
import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.TiposDeQuarto;

public class QuartoExecutivoTest {

	private Quarto quarto1, quarto2, quarto3;

	private final boolean TEM_CAMA_EXTRA = true;
	private final boolean NAO_TEM_CAMA_EXTRA = false;

	@Before
	public void criaObjetos() throws NullPointerException, CamaExtraException {
		quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		quarto2 = new QuartoExecutivo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO);
		quarto3 = new QuartoExecutivo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);
	}// criaObjetos

	@Test
	public void testCriaQuartoExecutivo() throws CamaExtraException {
		try {
			quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);
		} catch (CamaExtraException e) {
			Assert.assertTrue(true);
		}
	}// testCriaQuartoExecutivo

	@Test
	public void testaGetPreco() {
		Assert.assertEquals(quarto1.getPreco(),
				QuartoExecutivo.DIARIA_EXECUTIVO_SIMPLES, 0.001);
		Assert.assertEquals(quarto2.getPreco(),
				QuartoExecutivo.DIARIA_EXECUTIVO_DUPLO, 0.001);
		Assert.assertEquals(quarto3.getPreco(),
				QuartoExecutivo.DIARIA_EXECUTIVO_TRIPLO, 0.001);
	}

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
