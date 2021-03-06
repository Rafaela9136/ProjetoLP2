package testes;

import hotel.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.*;

import excecoes.*;

public class QuartoLuxoTest {

	private QuartoLuxo quarto1, quarto2, quarto3;

	private Calendar momentoAgr;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;

	private final boolean TEM_CAMA_EXTRA = true;
	private final boolean NAO_TEM_CAMA_EXTRA = false;

	@Before
	public void criaObjetos() throws NullPointerException, CamaExtraException,
			DataInvalidaException {
		momentoAgr = new GregorianCalendar();
		dataCheckIn = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 1);
		
		dataCheckOut = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 2);

		quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES,
				dataCheckIn, dataCheckOut);
		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
				dataCheckIn, dataCheckOut);
		quarto3 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO,
				dataCheckIn, dataCheckOut);

	}// criaObjetos

	@Test
	public void testCriaQuartoLuxo() throws CamaExtraException,
			NullPointerException, DataInvalidaException {
		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, null, dataCheckIn,
					dataCheckOut);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO, null,
					dataCheckOut);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
					dataCheckIn, null);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO,
					dataCheckIn, dataCheckIn);
		} catch (CamaExtraException e) {
			Assert.assertTrue(true);
		}

		Calendar dataCheckIn = new GregorianCalendar(2015, 3, 22);

		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
					dataCheckIn, dataCheckOut);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		dataCheckIn = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 5);
		
		Calendar dataCheckOut = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 4);

		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
					dataCheckIn, dataCheckOut);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}
		
		dataCheckIn = new GregorianCalendar(2015, 03, 22);
		
		try {
			quarto1 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
					dataCheckIn, this.dataCheckOut);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

	}// testCriaQuartoLuxo

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
	public void testEquals() throws NullPointerException, CamaExtraException,
			DataInvalidaException {
		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES,
				dataCheckIn, dataCheckOut);
		Assert.assertTrue(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
				dataCheckIn, dataCheckOut);
		Assert.assertFalse(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA,
				TiposDeQuarto.SIMPLES, dataCheckIn, dataCheckOut);
		Assert.assertFalse(quarto1.equals(quarto2));

		Calendar dataCheckIn = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 2);

		Calendar dataCheckOut = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 7);

		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES,
				dataCheckIn, this.dataCheckOut);
		Assert.assertFalse(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA,
				TiposDeQuarto.SIMPLES, this.dataCheckIn, this.dataCheckOut);
		Assert.assertTrue(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA,
				TiposDeQuarto.SIMPLES, this.dataCheckIn, dataCheckOut);
		Assert.assertFalse(quarto1.equals(quarto2));

		quarto2 = new QuartoLuxo(TEM_CAMA_EXTRA,
				TiposDeQuarto.SIMPLES, this.dataCheckIn, this.dataCheckOut);
		Assert.assertTrue(quarto1.equals(quarto2));

	}// testEquals

}// TestQuartoExecutivo

