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
	Calendar dataInicio1 = new GregorianCalendar(2015, 5, 12);
	Calendar dataInicio2 = new GregorianCalendar(2015, 5, 17);
	Calendar dataInicio3 = new GregorianCalendar(2015, 5, 29);
	Calendar dataTermino1 = new GregorianCalendar(2015, 6, 5);
	Calendar dataTermino2 = new GregorianCalendar(2015, 6, 1);
	Calendar dataTermino3 = new GregorianCalendar(2015, 6, 12);

	@Before
	public void criaObjetos() throws NullPointerException,
			DataInvalidaException {
		carro1 = new Carro(carroExecutivo, dataInicio1, dataTermino1, true,
				true);
		carro2 = new Carro(carroLuxo, dataInicio2, dataTermino2, true, false);
		carro3 = new Carro(carroLuxo, dataInicio3, dataTermino3, false, true);
		carro4 = new Carro(carroExecutivo, dataInicio2, dataTermino1);
	}

	@Test
	public void testaCriaCarro() throws NullPointerException,
			DataInvalidaException {
		Calendar dataNula = null;
		try {
			new Carro(carroLuxo, dataNula, dataTermino1, true, true);
		} catch (NullPointerException e) {

		}
		try {
			new Carro(carroExecutivo, dataInicio2, dataNula);
		} catch (NullPointerException e) {

		}
		try {
			new Carro(carroLuxo, dataInicio1, new GregorianCalendar());
		} catch (DataInvalidaException e) {

		}
		try {
			new Carro(carroExecutivo, dataTermino1, dataInicio1);
		} catch (DataInvalidaException e) {

		}
		try {
			new Carro(carroExecutivo, new GregorianCalendar(),
					new GregorianCalendar(2015, 0, 31), true, true);
		} catch (DataInvalidaException e) {

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

	}

	@Test
	public void testaSetDataTermino() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro1.getDataTermino(), dataTermino1);
		try {
			carro1.setDataDeTermino(null);
		} catch (NullPointerException e) {

		}
		carro1.setDataDeTermino(dataTermino2);
		Assert.assertEquals(carro1.getDataTermino(), dataTermino2);

		Assert.assertEquals(carro3.getDataTermino(), dataTermino3);
		try {
			carro3.setDataDeTermino(new GregorianCalendar(2014, 1, 15));
		} catch (DataInvalidaException e) {

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
		Assert.assertEquals(carro1.numeroDeDias(), 5);
		Assert.assertEquals(carro1.getPreco(), 550, 0.001);
		carro1.setDataDeTermino(new GregorianCalendar(2014, 1, 10));
		Assert.assertEquals(carro1.numeroDeDias(), 10);
		Assert.assertEquals(carro1.getPreco(), 850, 0.001);

		Assert.assertEquals(carro2.getIsTanqueCheio(), true);
		Assert.assertEquals(carro2.getIsAssegurado(), false);
		Assert.assertEquals(carro2.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro2.numeroDeDias(), 3);
		Assert.assertEquals(carro2.getPreco(), 450, 0.001);
		carro2.setDataDeTermino(new GregorianCalendar(2014, 1, 27));
		Assert.assertEquals(carro2.numeroDeDias(), 27);
		Assert.assertEquals(carro2.getPreco(), 2850, 0.001);

		Assert.assertEquals(carro3.getIsTanqueCheio(), false);
		Assert.assertEquals(carro3.getIsAssegurado(), true);
		Assert.assertEquals(carro3.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro3.numeroDeDias(), 12);
		Assert.assertEquals(carro3.getPreco(), 1300, 0.001);
		carro3.setDataDeTermino(new GregorianCalendar(2014, 1, 15));
		Assert.assertEquals(carro3.numeroDeDias(), 15);
		Assert.assertEquals(carro3.getPreco(), 1600, 0.001);

		Assert.assertEquals(carro4.getIsTanqueCheio(), false);
		Assert.assertEquals(carro4.getIsAssegurado(), false);
		Assert.assertEquals(carro4.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro4.numeroDeDias(), 30);
		Assert.assertEquals(carro4.getPreco(), 1800, 0.001);
		carro4.setDataDeTermino(new GregorianCalendar(2014, 2, 10));
		Assert.assertEquals(carro4.numeroDeDias(), 38);
		Assert.assertEquals(carro4.getPreco(), 2280, 0.001);

	}

}
