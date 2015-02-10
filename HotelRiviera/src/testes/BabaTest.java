package testes;

import java.util.*;

import hotel.*;

import org.junit.*;

import excecoes.DataInvalidaException;
import excecoes.NomeVazioException;

public class BabaTest {

	private Baba baba1;
	private Baba baba2;

	private Calendar horarioInicio1 = new GregorianCalendar(2015,
			Calendar.DECEMBER, 31, 22, 0);
	private Calendar horarioFim1 = new GregorianCalendar(2016,
			Calendar.JANUARY, 1, 7, 0);

	private Calendar horarioInicio2 = new GregorianCalendar(2015,
			Calendar.JUNE, 20, 13, 0);
	private Calendar horarioFim2 = new GregorianCalendar(2015, Calendar.JUNE,
			21, 19, 0);

	@Before
	public void criaBabas() throws NullPointerException, NomeVazioException,
			DataInvalidaException {
		baba1 = new Baba(horarioInicio1, horarioFim1);
		baba2 = new Baba(horarioInicio2, horarioFim2);
	}

	@Test
	public void testaConstrutor() throws NomeVazioException,
			DataInvalidaException {
		try {
			new Baba(null, horarioFim1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Baba(horarioInicio1, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Baba(null, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Baba(horarioInicio1, horarioInicio1);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Baba(horarioFim1, horarioFim1);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Baba(horarioFim1, horarioInicio1);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Baba(new GregorianCalendar(), new GregorianCalendar(2015,
					Calendar.FEBRUARY, 5));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
	}
	
	@Test
	public void testaSetDataInicio() throws DataInvalidaException {
		Assert.assertEquals(baba1.getDataInicio(), horarioInicio1);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.DAY_OF_MONTH), 31);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.MONTH), Calendar.DECEMBER);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.YEAR), 2015);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.HOUR_OF_DAY), 22);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.MINUTE), 0);
		
		Assert.assertEquals(baba2.getDataInicio(), horarioInicio2);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.DAY_OF_MONTH), 20);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.MONTH), Calendar.JUNE);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.YEAR), 2015);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.HOUR_OF_DAY), 13);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.MINUTE), 0);
		
		try {
			baba1.setDataInicio(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		
		Calendar novaDataInicio = new GregorianCalendar(2016, Calendar.JANUARY, 2, 15, 0);
		Assert.assertTrue(novaDataInicio.after(baba1.getDataTermino()));
		try {
			baba1.setDataInicio(novaDataInicio);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		
		try {
			baba1.setDataInicio(new GregorianCalendar(2010, Calendar.JANUARY, 10));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		
		baba1.setDataInicio(horarioInicio2);
		Assert.assertEquals(baba1.getDataInicio(), horarioInicio2);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.DAY_OF_MONTH), 20);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.MONTH), Calendar.JUNE);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.YEAR), 2015);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.HOUR_OF_DAY), 13);
		Assert.assertEquals(baba1.getDataInicio().get(Calendar.MINUTE), 0);
		
		baba2.setDataInicio(new GregorianCalendar(2015, Calendar.MAY, 15, 18, 30));
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.DAY_OF_MONTH), 15);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.MONTH), Calendar.MAY);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.YEAR), 2015);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.HOUR_OF_DAY), 18);
		Assert.assertEquals(baba2.getDataInicio().get(Calendar.MINUTE), 30);
	}
	
	@Test
	public void testaSetDataTermino() throws DataInvalidaException {
		Assert.assertEquals(baba1.getDataTermino(), horarioFim1);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.DAY_OF_MONTH), 1);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.MONTH), Calendar.JANUARY);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.YEAR), 2016);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.HOUR_OF_DAY), 7);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.MINUTE), 0);
		
		Assert.assertEquals(baba2.getDataTermino(), horarioFim2);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.DAY_OF_MONTH), 21);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.MONTH), Calendar.JUNE);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.YEAR), 2015);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.HOUR_OF_DAY), 19);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.MINUTE), 0);
		
		try {
			baba1.setDataTermino(null);
		} catch (NullPointerException e) {
			
		}
		
		Calendar novaDataTermino = new GregorianCalendar(2015, Calendar.DECEMBER, 25, 16, 40);
		Assert.assertTrue(novaDataTermino.before(baba1.getDataInicio()));
		try {
			baba1.setDataTermino(novaDataTermino);
		} catch (DataInvalidaException e) {
			
		}
		
		try {
			baba2.setDataTermino(new GregorianCalendar(2014, Calendar.APRIL, 20, 20, 18));
		} catch (DataInvalidaException e) {
			
		}
		
		baba1.setDataInicio(new GregorianCalendar(2015, Calendar.DECEMBER, 26));
		baba1.setDataTermino(new GregorianCalendar(2015, Calendar.DECEMBER, 28));
		Assert.assertTrue(baba1.getDataInicio().before(baba1.getDataTermino()));
		
		baba1.setDataTermino(new GregorianCalendar(2016, Calendar.JANUARY, 1));
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.YEAR), 2016);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.MONTH), Calendar.JANUARY);
		Assert.assertEquals(baba1.getDataTermino().get(Calendar.DAY_OF_MONTH), 1);
		baba2.setDataTermino(baba1.getDataTermino());
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.YEAR), 2016);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.MONTH), Calendar.JANUARY);
		Assert.assertEquals(baba2.getDataTermino().get(Calendar.DAY_OF_MONTH), 1);
	}
	
	@Test
	public void testaGetPreco() throws NullPointerException, DataInvalidaException {
		Assert.assertEquals(baba1.getNumeroDeHoras(), 9);
		Assert.assertEquals(baba1.getPreco(), 450, 0.0001);
		baba1.setDataTermino(new GregorianCalendar(2016, Calendar.JANUARY, 1, 23, 30));
		Assert.assertEquals(baba1.getNumeroDeHoras(), 26);
		Assert.assertEquals(baba1.getPreco(), 1025, 0.0001);
		baba1.setDataTermino(new GregorianCalendar(2016, Calendar.JANUARY, 5, 17, 4));
		baba1.setDataInicio(new GregorianCalendar(2016, Calendar.JANUARY, 5, 0, 40));
		Assert.assertEquals(baba1.getNumeroDeHoras(), 17);
		Assert.assertEquals(baba1.getPreco(), 600, 0.0001);
		
		Assert.assertEquals(baba2.getNumeroDeHoras(), 30);
		Assert.assertEquals(baba2.getPreco(), 1100, 0.0001);
		baba2.setDataTermino(new GregorianCalendar(2015, Calendar.JUNE, 21, 13, 50));
		Assert.assertEquals(baba2.getNumeroDeHoras(), 25);
		Assert.assertEquals(baba2.getPreco(), 950, 0.0001);
		baba2.setDataInicio(new GregorianCalendar(2015, Calendar.JUNE, 21, 2, 30));
		Assert.assertEquals(baba2.getNumeroDeHoras(), 12);
		Assert.assertEquals(baba2.getPreco(), 425, 0.0001);
		baba2.setDataTermino(new GregorianCalendar(2015, Calendar.JUNE, 21, 20, 20));
		Assert.assertEquals(baba2.getNumeroDeHoras(), 18);
		Assert.assertEquals(baba2.getPreco(), 625, 0.0001);
	}

	@Test
	public void testaToString() {
		Assert.assertEquals("SERVICO BABYSITTER"
                           + "\nPreco Total: R$ 450.0"
                           + "\nDuracao: 9 horas"
                           + "\nData Inicio: 31/12/2015 as 22:0"
                           + "\nData Termino: 1/1/2016 as 7:0"
                           + "\nOBS: Das 18h as 7h o valor do servico e cobrado em dobro.",  baba1.toString());
		
		Assert.assertEquals("SERVICO BABYSITTER"
						+ "\nPreco Total: R$ 1100.0" 
						+ "\nDuracao: 30 horas"
						+ "\nData Inicio: 20/6/2015 as 13:0"
						+ "\nData Termino: 21/6/2015 as 19:0"
						+ "\nOBS: Das 18h as 7h o valor do servico e cobrado em dobro.", baba2.toString());	
	}
	
	@Test
	public void testaEquals() throws NullPointerException, DataInvalidaException {
		Assert.assertFalse(baba1.equals(baba2));
		Assert.assertTrue(baba1.equals(new Baba(horarioInicio1, horarioFim1)));
		Assert.assertTrue(baba2.equals(new Baba(horarioInicio2, horarioFim2)));
	}
}
