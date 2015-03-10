package testes;

import java.util.*;

import hotel.*;

import org.junit.*;

import excecoes.DataInvalidaException;
import excecoes.NomeVazioException;

public class BabaTest {

	private Baba baba1;
	private Baba baba2;
	private Calendar horarioInicio1, horarioInicio2, horarioFim1, horarioFim2,
			dataAtual;
	private int proxAno;

	@Before
	public void criaBabas() throws NullPointerException, NomeVazioException,
			DataInvalidaException {
		dataAtual = new GregorianCalendar();
		proxAno = dataAtual.get(Calendar.YEAR) + 1;
		horarioInicio1 = new GregorianCalendar(proxAno, Calendar.DECEMBER, 31,
				22, 0);
		horarioFim1 = new GregorianCalendar(proxAno + 1, Calendar.JANUARY, 1,
				7, 0);
		horarioInicio2 = new GregorianCalendar(proxAno, Calendar.JUNE, 20, 13,
				0);
		horarioFim2 = new GregorianCalendar(proxAno, Calendar.JUNE, 21, 19, 0);

		baba1 = new Baba(horarioInicio1, horarioFim1);
		baba2 = new Baba(horarioInicio2, horarioFim2);
	}

	@Test
	public void testaConstrutor() throws NomeVazioException,
			DataInvalidaException {
		Assert.assertEquals(baba1.getNome(), "Baba");
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
			new Baba(new GregorianCalendar(), new GregorianCalendar(proxAno,
					Calendar.FEBRUARY, 5));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaSetDataInicio() throws DataInvalidaException {
		Assert.assertEquals(baba1.getDataCheckIn(), horarioInicio1);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				31);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.MONTH),
				Calendar.DECEMBER);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.YEAR), proxAno);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.HOUR_OF_DAY),
				22);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.MINUTE), 0);

		Assert.assertEquals(baba2.getDataCheckIn(), horarioInicio2);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				20);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.MONTH),
				Calendar.JUNE);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.YEAR), proxAno);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.HOUR_OF_DAY),
				13);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.MINUTE), 0);

		try {
			baba1.setDataCheckIn(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Calendar novaDataInicio = new GregorianCalendar(proxAno + 1,
				Calendar.JANUARY, 2, 15, 0);
		Assert.assertTrue(novaDataInicio.after(baba1.getDataCheckOut()));
		try {
			baba1.setDataCheckIn(novaDataInicio);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			baba1.setDataCheckIn(new GregorianCalendar(2010, Calendar.JANUARY,
					10));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		baba1.setDataCheckIn(horarioInicio2);
		Assert.assertEquals(baba1.getDataCheckIn(), horarioInicio2);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				20);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.MONTH),
				Calendar.JUNE);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.YEAR), proxAno);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.HOUR_OF_DAY),
				13);
		Assert.assertEquals(baba1.getDataCheckIn().get(Calendar.MINUTE), 0);

		baba2.setDataCheckIn(new GregorianCalendar(proxAno, Calendar.MAY, 15,
				18, 30));
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				15);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.MONTH),
				Calendar.MAY);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.YEAR), proxAno);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.HOUR_OF_DAY),
				18);
		Assert.assertEquals(baba2.getDataCheckIn().get(Calendar.MINUTE), 30);
	}

	@Test
	public void testaSetDataTermino() throws DataInvalidaException {
		Assert.assertEquals(baba1.getDataCheckOut(), horarioFim1);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.DAY_OF_MONTH),
				1);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.MONTH),
				Calendar.JANUARY);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.YEAR),
				proxAno + 1);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.HOUR_OF_DAY),
				7);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.MINUTE), 0);

		Assert.assertEquals(baba2.getDataCheckOut(), horarioFim2);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.DAY_OF_MONTH),
				21);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.MONTH),
				Calendar.JUNE);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.YEAR), proxAno);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.HOUR_OF_DAY),
				19);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.MINUTE), 0);

		try {
			baba1.setDataCheckOut(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Calendar novaDataTermino = new GregorianCalendar(proxAno,
				Calendar.DECEMBER, 25, 16, 40);
		Assert.assertTrue(novaDataTermino.before(baba1.getDataCheckIn()));
		try {
			baba1.setDataCheckOut(novaDataTermino);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			baba2.setDataCheckOut(new GregorianCalendar(2014, Calendar.APRIL,
					20, 20, 18));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		baba1.setDataCheckIn(new GregorianCalendar(proxAno, Calendar.DECEMBER,
				26));
		baba1.setDataCheckOut(new GregorianCalendar(proxAno, Calendar.DECEMBER,
				28));
		Assert.assertTrue(baba1.getDataCheckIn()
				.before(baba1.getDataCheckOut()));

		baba1.setDataCheckOut(new GregorianCalendar(proxAno + 1,
				Calendar.JANUARY, 1));
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.YEAR),
				proxAno + 1);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.MONTH),
				Calendar.JANUARY);
		Assert.assertEquals(baba1.getDataCheckOut().get(Calendar.DAY_OF_MONTH),
				1);
		baba2.setDataCheckOut(baba1.getDataCheckOut());
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.YEAR),
				proxAno + 1);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.MONTH),
				Calendar.JANUARY);
		Assert.assertEquals(baba2.getDataCheckOut().get(Calendar.DAY_OF_MONTH),
				1);
	}

	@Test
	public void testaGetPreco() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(baba1.getNumeroDeHoras(), 9);
		Assert.assertEquals(baba1.getPreco(), 450, 0.0001);
		baba1.setDataCheckOut(new GregorianCalendar(proxAno + 1,
				Calendar.JANUARY, 1, 23, 30));
		Assert.assertEquals(baba1.getNumeroDeHoras(), 26);
		Assert.assertEquals(baba1.getPreco(), 1025, 0.0001);
		baba1.setDataCheckOut(new GregorianCalendar(proxAno + 1,
				Calendar.JANUARY, 5, 17, 4));
		baba1.setDataCheckIn(new GregorianCalendar(proxAno + 1,
				Calendar.JANUARY, 5, 0, 40));
		Assert.assertEquals(baba1.getNumeroDeHoras(), 17);
		Assert.assertEquals(baba1.getPreco(), 600, 0.0001);

		Assert.assertEquals(baba2.getNumeroDeHoras(), 30);
		Assert.assertEquals(baba2.getPreco(), 1100, 0.0001);
		baba2.setDataCheckOut(new GregorianCalendar(proxAno, Calendar.JUNE, 21,
				13, 50));
		Assert.assertEquals(baba2.getNumeroDeHoras(), 25);
		Assert.assertEquals(baba2.getPreco(), 950, 0.0001);
		baba2.setDataCheckIn(new GregorianCalendar(proxAno, Calendar.JUNE, 21,
				2, 30));
		Assert.assertEquals(baba2.getNumeroDeHoras(), 12);
		Assert.assertEquals(baba2.getPreco(), 425, 0.0001);
		baba2.setDataCheckOut(new GregorianCalendar(proxAno, Calendar.JUNE, 21,
				20, 20));
		Assert.assertEquals(baba2.getNumeroDeHoras(), 18);
		Assert.assertEquals(baba2.getPreco(), 625, 0.0001);
	}

	@Test
	public void testaToString() {
		final String FIM_LINHA = System.getProperty("line.separator");
		Assert.assertEquals(baba1.toString(), "SERVICO BABYSITTER" + FIM_LINHA
				+ "Preco Total: R$ " + baba1.getPreco() + FIM_LINHA
				+ "Duracao: " + baba1.getNumeroDeHoras() + " horas" + FIM_LINHA
				+ "Data Inicio: 31/12/2016 22:00" + FIM_LINHA
				+ "Data Termino: 01/01/2017 07:00" + FIM_LINHA
				+ "OBS: Das 18h as 7h o valor do servico e cobrado em dobro.");

		Assert.assertEquals(baba2.toString(), "SERVICO BABYSITTER" + FIM_LINHA
				+ "Preco Total: R$ " + baba2.getPreco() + FIM_LINHA
				+ "Duracao: " + baba2.getNumeroDeHoras() + " horas" + FIM_LINHA
				+ "Data Inicio: 20/06/2016 13:00" + FIM_LINHA
				+ "Data Termino: 21/06/2016 19:00" + FIM_LINHA
				+ "OBS: Das 18h as 7h o valor do servico e cobrado em dobro.");
	}

	@Test
	public void testaEquals() throws NullPointerException,
			DataInvalidaException {
		Assert.assertFalse(baba1.equals(null));
		Assert.assertFalse(baba1.equals(baba2));
		Assert.assertTrue(baba1.equals(new Baba(horarioInicio1, horarioFim1)));
		Assert.assertTrue(baba2.equals(new Baba(horarioInicio2, horarioFim2)));
		Assert.assertFalse(baba2.equals(new Baba(horarioInicio2,
				new GregorianCalendar(proxAno, Calendar.JUNE, 21, 20, 0))));
		Assert.assertFalse(baba2.equals(new Baba(new GregorianCalendar(proxAno,
				Calendar.JUNE, 20, 10, 0), horarioFim2)));
	}
}
