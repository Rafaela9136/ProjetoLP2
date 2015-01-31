package testes;

import java.util.Calendar;
import java.util.GregorianCalendar;

import hotel.Carro;
import hotel.TipoCarro;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CarroTest {

	Carro carro1, carro2, carro3, carro4;
	private TipoCarro carroExecutivo = TipoCarro.EXECUTIVO;
	private TipoCarro carroLuxo = TipoCarro.LUXO;
	Calendar dataInicio;
	Calendar dataTermino1 = new GregorianCalendar(2015, 1, 5);
	Calendar dataTermino2 = new GregorianCalendar(2015, 1, 3);
	Calendar dataTermino3 = new GregorianCalendar(2015, 1, 12);

	@Before
	public void criaObjetos() {
		carro1 = new Carro(carroExecutivo, dataTermino1, true, true);
		carro2 = new Carro(carroLuxo, dataTermino2, true, false);
		carro3 = new Carro(carroLuxo, dataTermino3, false, true);
		carro4 = new Carro(carroExecutivo, dataTermino2);
	}

	@Test
	public void testaCriaCarro() throws NullPointerException {
		Calendar data = null;
		try {
			new Carro(carroLuxo, data, true, true);
		} catch (NullPointerException e) {

		}
		try {
			new Carro(carroExecutivo, null);
		} catch (NullPointerException e) {

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
	public void testaSetDataTermino() throws NullPointerException {
		Assert.assertEquals(carro1.getDataTermino(), dataTermino1);
		try {
			carro1.setDataDeTermino(null);
		} catch (NullPointerException e) {

		}
		carro1.setDataDeTermino(dataTermino2);
		Assert.assertEquals(carro1.getDataTermino(), dataTermino2);

		Assert.assertEquals(carro3.getDataTermino(), dataTermino3);
		carro3.setDataDeTermino(dataTermino1);
		Assert.assertEquals(carro3.getDataTermino(), dataTermino1);

		Assert.assertEquals(carro2.getDataTermino(), dataTermino2);
		carro2.setDataDeTermino(dataTermino3);
		Assert.assertEquals(carro2.getDataTermino(), dataTermino3);
	}

	@Test
	public void testaGetPreco() {
		
	}

}
