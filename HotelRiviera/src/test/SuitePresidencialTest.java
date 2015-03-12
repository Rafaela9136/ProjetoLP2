package test;

import java.util.Calendar;
import java.util.GregorianCalendar;

import models.*;

import org.junit.*;

import exceptions.CamaExtraException;
import exceptions.DataInvalidaException;

public class SuitePresidencialTest {

	private SuitePresidencial suite1, suite2;

	private Calendar momentoAgr;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;

	@Before
	public void criaSuite() throws NullPointerException, DataInvalidaException {
		momentoAgr = new GregorianCalendar();

		dataCheckIn = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 1);

		dataCheckOut = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 2);

		suite1 = new SuitePresidencial(dataCheckIn, dataCheckOut);
		suite2 = new SuitePresidencial(dataCheckIn, dataCheckOut);
	}

	@Test
	public void testCriaSuite() {
		try {
			new SuitePresidencial(null, dataCheckOut);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}
		
		try {
			new SuitePresidencial(dataCheckIn, null);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}
		
		Calendar dataCheckIn = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) - 1);
		
		Calendar dataCheckOut = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH));
		
		try {
			new SuitePresidencial(dataCheckIn, this.dataCheckOut);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}
		
		try {
			new SuitePresidencial(this.dataCheckIn, dataCheckOut);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}
		
	}

	@Test
	public void testaValorDiaria() {
		Assert.assertEquals(suite1.getPreco(), 1200, 0.0001);
		Assert.assertEquals(suite2.getPreco(), 1200, 0.0001);
	}

	@Test
	public void testEquals() throws NullPointerException, CamaExtraException,
			DataInvalidaException {
		suite2 = new SuitePresidencial(dataCheckIn, dataCheckOut);
		Assert.assertTrue(suite1.equals(suite2));

		Calendar dataCheckIn = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 2);

		Calendar dataCheckOut = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 7);

		suite2 = new SuitePresidencial(dataCheckIn, this.dataCheckOut);
		Assert.assertFalse(suite1.equals(suite2));

		suite2 = new SuitePresidencial(this.dataCheckIn, this.dataCheckOut);
		Assert.assertTrue(suite1.equals(suite2));

		suite2 = new SuitePresidencial(this.dataCheckIn, dataCheckOut);
		Assert.assertFalse(suite1.equals(suite2));

		suite2 = new SuitePresidencial(this.dataCheckIn, this.dataCheckOut);
		Assert.assertTrue(suite1.equals(suite2));

	}// testEquals
}
