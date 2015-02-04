package testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import hotel.Carro;
import hotel.TipoCarro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.DataInvalidaException;

public class CarroTest {

	Carro carro1, carro2, carro3, carro4;
	private TipoCarro carroExecutivo = TipoCarro.EXECUTIVO;
	private TipoCarro carroLuxo = TipoCarro.LUXO;
	Calendar dataInicio1 = new GregorianCalendar(2015, Calendar.JUNE, 12);
	Calendar dataInicio2 = new GregorianCalendar(2015, Calendar.JUNE, 17);
	Calendar dataInicio3 = new GregorianCalendar(2015, Calendar.JUNE, 29);
	Calendar dataTermino1 = new GregorianCalendar(2015, Calendar.JULY, 5);
	Calendar dataTermino2 = new GregorianCalendar(2015, Calendar.JULY, 1);
	Calendar dataTermino3 = new GregorianCalendar(2015, Calendar.JULY, 12);

	@Before
	public void criaObjetos() throws NullPointerException,
			DataInvalidaException {
		carro1 = new Carro(carroExecutivo, dataInicio1, dataTermino1, true,
				true);
		carro2 = new Carro(carroLuxo, dataInicio2, dataTermino2, true, false);
		carro3 = new Carro(carroLuxo, dataInicio3, dataTermino3, false, true);
		carro4 = new Carro(carroExecutivo, dataInicio2, dataTermino1, false,
				false);
	}

	@Test
	public void testaCriaCarro() throws NullPointerException,
			DataInvalidaException {
		try {
			new Carro(null, dataInicio3, dataTermino1, false, false);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Calendar dataNula = null;
		try {
			new Carro(carroLuxo, dataNula, dataTermino1, true, true);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Carro(carroExecutivo, dataInicio2, dataNula, true, false);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Carro(carroLuxo, dataInicio1, new GregorianCalendar(), false,
					true);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Carro(carroExecutivo, new GregorianCalendar(2015, 8, 14),
					new GregorianCalendar(2015, 8, 13), true, true);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Carro(carroExecutivo, new GregorianCalendar(),
					new GregorianCalendar(2015, Calendar.JANUARY, 31), true,
					true);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Carro(null, null, null, false, false);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Carro(null, new GregorianCalendar(2015, Calendar.JULY, 2),
					new GregorianCalendar(2015, Calendar.JULY, 1), true, false);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaGetIsTanqueCheio() {
		Assert.assertEquals(carro1.getIsTanqueCheio(), true);
		Assert.assertEquals(carro2.getIsTanqueCheio(), true);
		Assert.assertEquals(carro3.getIsTanqueCheio(), false);
		Assert.assertEquals(carro4.getIsTanqueCheio(), false);
	}

	@Test
	public void testaGetIsAssegurado() {
		Assert.assertEquals(carro1.getIsAssegurado(), true);
		Assert.assertEquals(carro2.getIsAssegurado(), false);
		Assert.assertEquals(carro3.getIsAssegurado(), true);
		Assert.assertEquals(carro4.getIsAssegurado(), false);
	}

	@Test
	public void testaGetTipoDeCarro() {
		Assert.assertEquals(carro1.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro2.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro3.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro4.getTipoDeCarro(), carroExecutivo);
	}

	@Test
	public void testaSetDataInicio() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro2.getDataInicio(), dataInicio2);
		try {
			carro2.setDataInicio(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			carro2.setDataInicio(new GregorianCalendar(2015, Calendar.JANUARY,
					31));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			carro4.setDataInicio(new GregorianCalendar());
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(carro3.getDataTermino().get(Calendar.DAY_OF_MONTH),
				12);
		Assert.assertEquals(carro3.getDataTermino().get(Calendar.MONTH),
				Calendar.JULY);
		Assert.assertEquals(carro3.getDataTermino().get(Calendar.YEAR), 2015);
		Calendar novaDataInicio = new GregorianCalendar(2015, Calendar.JULY, 13);
		Assert.assertTrue(novaDataInicio.after(carro3.getDataTermino()));
		try {
			carro3.setDataInicio(novaDataInicio);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		carro2.setDataInicio(new GregorianCalendar(2015, Calendar.MARCH, 17));
		Assert.assertEquals(carro2.getDataInicio().get(Calendar.DAY_OF_MONTH),
				17);
		Assert.assertEquals(carro2.getDataInicio().get(Calendar.MONTH),
				Calendar.MARCH);
		Assert.assertEquals(carro2.getDataInicio().get(Calendar.YEAR), 2015);
		try {
			carro2.setDataInicio(new GregorianCalendar(2015, Calendar.AUGUST,
					16));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		carro2.setDataDeTermino(new GregorianCalendar(2015, Calendar.AUGUST, 20));
		carro2.setDataInicio(new GregorianCalendar(2015, Calendar.AUGUST, 16));
		Assert.assertEquals(carro2.getDataInicio().get(Calendar.DAY_OF_MONTH),
				16);
		Assert.assertEquals(carro2.getDataInicio().get(Calendar.MONTH),
				Calendar.AUGUST);
		Assert.assertEquals(carro2.getDataInicio().get(Calendar.YEAR), 2015);

	}

	@Test
	public void testaSetDataTermino() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro1.getDataTermino(), dataTermino1);
		try {
			carro1.setDataDeTermino(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		carro1.setDataDeTermino(dataTermino2);
		Assert.assertEquals(carro1.getDataTermino(), dataTermino2);

		Assert.assertEquals(carro3.getDataTermino(), dataTermino3);
		try {
			carro3.setDataDeTermino(new GregorianCalendar(2014,
					Calendar.FEBRUARY, 15));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		Assert.assertNotEquals(
				carro3.getDataTermino().get(Calendar.DAY_OF_MONTH), 15);
		Assert.assertNotEquals(carro3.getDataTermino().get(Calendar.MONTH), 1);
		Assert.assertNotEquals(carro3.getDataTermino().get(Calendar.YEAR), 2014);

		Assert.assertEquals(carro2.getDataTermino(), dataTermino2);
		carro2.setDataDeTermino(dataTermino3);
		Assert.assertEquals(carro2.getDataTermino(), dataTermino3);
	}

	@Test
	public void testaGetPreco() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro1.getIsTanqueCheio(), true);
		Assert.assertEquals(carro1.getIsAssegurado(), true);
		Assert.assertEquals(carro1.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro1.numeroDeDias(), 23);
		Assert.assertEquals(carro1.getPreco(), 1630, 0.001);
		carro1.setDataDeTermino(dataTermino3);
		Assert.assertEquals(carro1.numeroDeDias(), 30);
		Assert.assertEquals(carro1.getPreco(), 2050, 0.001);

		Assert.assertEquals(carro2.getIsTanqueCheio(), true);
		Assert.assertEquals(carro2.getIsAssegurado(), false);
		Assert.assertEquals(carro2.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro2.numeroDeDias(), 14);
		Assert.assertEquals(carro2.getPreco(), 1550, 0.001);
		carro2.setDataDeTermino(dataTermino1);
		Assert.assertEquals(carro2.numeroDeDias(), 18);
		Assert.assertEquals(carro2.getPreco(), 1950, 0.001);

		Assert.assertEquals(carro3.getIsTanqueCheio(), false);
		Assert.assertEquals(carro3.getIsAssegurado(), true);
		Assert.assertEquals(carro3.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro3.numeroDeDias(), 13);
		Assert.assertEquals(carro3.getPreco(), 1400, 0.001);
		carro3.setDataDeTermino(new GregorianCalendar(2015, Calendar.JULY, 22));
		Assert.assertEquals(carro3.numeroDeDias(), 23);
		Assert.assertEquals(carro3.getPreco(), 2400, 0.001);

		Assert.assertEquals(carro4.getIsTanqueCheio(), false);
		Assert.assertEquals(carro4.getIsAssegurado(), false);
		Assert.assertEquals(carro4.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro4.numeroDeDias(), 18);
		Assert.assertEquals(carro4.getPreco(), 1080, 0.001);
		carro4.setDataDeTermino(new GregorianCalendar(2015, Calendar.JULY, 19));
		Assert.assertEquals(carro4.numeroDeDias(), 32);
		Assert.assertEquals(carro4.getPreco(), 1920, 0.001);
	}

}
