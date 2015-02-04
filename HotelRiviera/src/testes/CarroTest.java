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

		Carro corsa = new Carro(carroExecutivo, dataInicio3, dataTermino1,
				false, false);
		Assert.assertEquals(corsa.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(corsa.getDataInicio(), dataInicio3);
		Assert.assertEquals(corsa.getDataTermino(), dataTermino1);
		Assert.assertEquals(corsa.getIsTanqueCheio(), false);
		Assert.assertEquals(corsa.getIsAssegurado(), false);

		Carro ferrari = new Carro(carroLuxo, dataInicio2, dataTermino3, true,
				true);
		Assert.assertEquals(ferrari.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(ferrari.getDataInicio(), dataInicio2);
		Assert.assertEquals(ferrari.getDataTermino(), dataTermino3);
		Assert.assertEquals(ferrari.getIsTanqueCheio(), true);
		Assert.assertEquals(ferrari.getIsAssegurado(), true);

		Carro carrao = new Carro(carroLuxo, new GregorianCalendar(2015,
				Calendar.DECEMBER, 24), new GregorianCalendar(2016,
				Calendar.JANUARY, 5), false, false);
		Assert.assertEquals(carrao.numeroDeDias(), 12);
		Assert.assertEquals(carrao.getPreco(), 1200, 0.0001);

		Carro carrinho = new Carro(carroLuxo, new GregorianCalendar(2015,
				Calendar.OCTOBER, 29), new GregorianCalendar(2015,
				Calendar.OCTOBER, 30), false, false);
		Assert.assertEquals(carrinho.numeroDeDias(), 1);

		Carro carro = new Carro(carroLuxo, new GregorianCalendar(2015,
				Calendar.NOVEMBER, 12), new GregorianCalendar(2015,
				Calendar.NOVEMBER, 12), false, false);
		Assert.assertEquals(carro.numeroDeDias(), 0);
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
		carro2.setDataTermino(new GregorianCalendar(2015, Calendar.AUGUST, 20));
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
		Assert.assertEquals(carro4.getDataInicio().get(Calendar.YEAR), 2015);
		Assert.assertEquals(carro4.getDataInicio().get(Calendar.MONTH),
				Calendar.JUNE);
		Assert.assertEquals(carro4.getDataInicio().get(Calendar.DAY_OF_MONTH),
				17);
		Calendar novaDataTermino = new GregorianCalendar(2015, Calendar.JUNE,
				16);
		Assert.assertTrue(novaDataTermino.before(carro4.getDataInicio()));
		try {
			carro4.setDataTermino(novaDataTermino);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(carro2.getDataInicio(), dataInicio2);
		Assert.assertEquals(carro2.getDataTermino(), dataTermino2);
		Calendar dataAtual = new GregorianCalendar();
		try {
			carro2.setDataTermino(dataAtual);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(carro1.getDataTermino(), dataTermino1);
		try {
			carro1.setDataTermino(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		carro1.setDataTermino(dataTermino2);
		Assert.assertEquals(carro1.getDataTermino(), dataTermino2);

		Assert.assertEquals(carro3.getDataTermino(), dataTermino3);
		try {
			carro3.setDataTermino(new GregorianCalendar(2014,
					Calendar.FEBRUARY, 15));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		Assert.assertNotEquals(
				carro3.getDataTermino().get(Calendar.DAY_OF_MONTH), 15);
		Assert.assertNotEquals(carro3.getDataTermino().get(Calendar.MONTH), 1);
		Assert.assertNotEquals(carro3.getDataTermino().get(Calendar.YEAR), 2014);

		Assert.assertEquals(carro2.getDataTermino(), dataTermino2);
		carro2.setDataTermino(dataTermino3);
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
		carro1.setDataTermino(dataTermino3);
		Assert.assertEquals(carro1.numeroDeDias(), 30);
		Assert.assertEquals(carro1.getPreco(), 2050, 0.001);

		Assert.assertEquals(carro2.getIsTanqueCheio(), true);
		Assert.assertEquals(carro2.getIsAssegurado(), false);
		Assert.assertEquals(carro2.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro2.numeroDeDias(), 14);
		Assert.assertEquals(carro2.getPreco(), 1550, 0.001);
		carro2.setDataTermino(dataTermino1);
		Assert.assertEquals(carro2.numeroDeDias(), 18);
		Assert.assertEquals(carro2.getPreco(), 1950, 0.001);

		Assert.assertEquals(carro3.getIsTanqueCheio(), false);
		Assert.assertEquals(carro3.getIsAssegurado(), true);
		Assert.assertEquals(carro3.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro3.numeroDeDias(), 13);
		Assert.assertEquals(carro3.getPreco(), 1400, 0.001);
		carro3.setDataTermino(new GregorianCalendar(2015, Calendar.JULY, 22));
		Assert.assertEquals(carro3.numeroDeDias(), 23);
		Assert.assertEquals(carro3.getPreco(), 2400, 0.001);

		Assert.assertEquals(carro4.getIsTanqueCheio(), false);
		Assert.assertEquals(carro4.getIsAssegurado(), false);
		Assert.assertEquals(carro4.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro4.numeroDeDias(), 18);
		Assert.assertEquals(carro4.getPreco(), 1080, 0.001);
		carro4.setDataTermino(new GregorianCalendar(2015, Calendar.JULY, 19));
		Assert.assertEquals(carro4.numeroDeDias(), 32);
		Assert.assertEquals(carro4.getPreco(), 1920, 0.001);
	}

	@Test
	public void testaToString() throws NullPointerException, DataInvalidaException {
		Assert.assertEquals(
				carro1.toString(),
				"Carro Executivo\n"
				+ "Adicionais: [Tanque cheio = R$150.0, Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1630.0\n"
				+ "Numero de dias: 23\n"
				+ "Data Inicio: 12/6/2015\n"
				+ "Data Termino: 5/7/2015");
		carro1.setDataInicio(new GregorianCalendar(2015, Calendar.JUNE, 17));
		Assert.assertEquals(
				carro1.toString(),
				"Carro Executivo\n"
				+ "Adicionais: [Tanque cheio = R$150.0, Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1330.0\n"
				+ "Numero de dias: 18\n"
				+ "Data Inicio: 17/6/2015\n"
				+ "Data Termino: 5/7/2015");
		carro1.setDataTermino(new GregorianCalendar(2015, Calendar.JULY, 15));
		Assert.assertEquals(
				carro1.toString(),
				"Carro Executivo\n"
				+ "Adicionais: [Tanque cheio = R$150.0, Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1930.0\n"
				+ "Numero de dias: 28\n"
				+ "Data Inicio: 17/6/2015\n"
				+ "Data Termino: 15/7/2015");
		
		Assert.assertEquals(
				carro3.toString(),
				"Carro Luxo\n"
				+ "Adicionais: [Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1400.0\n"
				+ "Numero de dias: 13\n"
				+ "Data Inicio: 29/6/2015\n"
				+ "Data Termino: 12/7/2015");
		carro3.setDataTermino(new GregorianCalendar(2015, Calendar.JULY, 29));
		carro3.setDataInicio(new GregorianCalendar(2015, Calendar.JULY, 3));
		Assert.assertEquals(
				carro3.toString(),
				"Carro Luxo\n"
				+ "Adicionais: [Seguro = R$100.0]\n"
				+ "Preco Total: R$ 2700.0\n"
				+ "Numero de dias: 26\n"
				+ "Data Inicio: 3/7/2015\n"
				+ "Data Termino: 29/7/2015");
		System.out.println(carro4.toString());
	}

	@Test
	public void testaEquals() {

	}

}
