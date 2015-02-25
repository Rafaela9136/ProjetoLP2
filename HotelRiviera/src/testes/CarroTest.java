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
	Calendar dataInicio1 = new GregorianCalendar(2015, Calendar.JUNE, 12, 15,
			12);
	Calendar dataInicio2 = new GregorianCalendar(2015, Calendar.JUNE, 17, 13,
			51);
	Calendar dataInicio3 = new GregorianCalendar(2015, Calendar.JUNE, 29, 10, 1);
	Calendar dataTermino1 = new GregorianCalendar(2015, Calendar.JULY, 5, 22,
			27);
	Calendar dataTermino2 = new GregorianCalendar(2015, Calendar.JULY, 1, 9, 14);
	Calendar dataTermino3 = new GregorianCalendar(2015, Calendar.JULY, 12, 7,
			32);

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
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		Calendar dataNula = null;
		try {
			new Carro(carroLuxo, dataNula, dataTermino1, true, true);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			new Carro(carroExecutivo, dataInicio2, dataNula, true, false);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			new Carro(carroLuxo, dataInicio1, new GregorianCalendar(), false,
					true);
			Assert.fail("Deveria ter lancado DataInvalidaExcepiton");
		} catch (DataInvalidaException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			new Carro(carroExecutivo, new GregorianCalendar(2015, 8, 14),
					new GregorianCalendar(2015, 8, 13), true, true);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}

		try {
			new Carro(carroExecutivo, new GregorianCalendar(),
					new GregorianCalendar(2015, Calendar.JANUARY, 31), true,
					true);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {
			
		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}


		Carro corsa = new Carro(carroExecutivo, dataInicio3, dataTermino1,
				false, false);
		Assert.assertEquals(corsa.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(corsa.getDataCheckIn(), dataInicio3);
		Assert.assertEquals(corsa.getDataCheckOut(), dataTermino1);
		Assert.assertEquals(corsa.getIsTanqueCheio(), false);
		Assert.assertEquals(corsa.getIsAssegurado(), false);

		Carro ferrari = new Carro(carroLuxo, dataInicio2, dataTermino3, true,
				true);
		Assert.assertEquals(ferrari.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(ferrari.getDataCheckIn(), dataInicio2);
		Assert.assertEquals(ferrari.getDataCheckOut(), dataTermino3);
		Assert.assertEquals(ferrari.getIsTanqueCheio(), true);
		Assert.assertEquals(ferrari.getIsAssegurado(), true);

		Carro carrao = new Carro(carroLuxo, new GregorianCalendar(2015,
				Calendar.DECEMBER, 24), new GregorianCalendar(2016,
				Calendar.JANUARY, 5), false, false);
		Assert.assertEquals(carrao.getNumeroDeDias(), 12);
		Assert.assertEquals(carrao.getPreco(), 1200, 0.0001);

		Carro carrinho = new Carro(carroLuxo, new GregorianCalendar(2015,
				Calendar.OCTOBER, 29), new GregorianCalendar(2015,
				Calendar.OCTOBER, 30), false, false);
		Assert.assertEquals(carrinho.getNumeroDeDias(), 1);

		Carro carro = new Carro(carroLuxo, new GregorianCalendar(2015,
				Calendar.NOVEMBER, 12), new GregorianCalendar(2015,
				Calendar.NOVEMBER, 12), false, false);
		Assert.assertEquals(carro.getNumeroDeDias(), 0);
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
		Assert.assertEquals(carro2.getDataCheckIn(), dataInicio2);
		try {
			carro2.setDataCheckIn(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			carro2.setDataCheckIn(new GregorianCalendar(2015, Calendar.JANUARY,
					31));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(carro3.getDataCheckOut().get(Calendar.DAY_OF_MONTH),
				12);
		Assert.assertEquals(carro3.getDataCheckOut().get(Calendar.MONTH),
				Calendar.JULY);
		Assert.assertEquals(carro3.getDataCheckOut().get(Calendar.YEAR), 2015);
		Calendar novaDataInicio = new GregorianCalendar(2015, Calendar.JULY, 13);
		Assert.assertTrue(novaDataInicio.after(carro3.getDataCheckOut()));
		try {
			carro3.setDataCheckIn(novaDataInicio);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		carro2.setDataCheckIn(new GregorianCalendar(2015, Calendar.MARCH, 17));
		Assert.assertEquals(carro2.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				17);
		Assert.assertEquals(carro2.getDataCheckIn().get(Calendar.MONTH),
				Calendar.MARCH);
		Assert.assertEquals(carro2.getDataCheckIn().get(Calendar.YEAR), 2015);
		try {
			carro2.setDataCheckIn(new GregorianCalendar(2015, Calendar.AUGUST,
					16));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		carro2.setDataCheckOut(new GregorianCalendar(2015, Calendar.AUGUST, 20));
		carro2.setDataCheckIn(new GregorianCalendar(2015, Calendar.AUGUST, 16));
		Assert.assertEquals(carro2.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				16);
		Assert.assertEquals(carro2.getDataCheckIn().get(Calendar.MONTH),
				Calendar.AUGUST);
		Assert.assertEquals(carro2.getDataCheckIn().get(Calendar.YEAR), 2015);

	}

	@Test
	public void testaSetDataTermino() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro4.getDataCheckIn().get(Calendar.YEAR), 2015);
		Assert.assertEquals(carro4.getDataCheckIn().get(Calendar.MONTH),
				Calendar.JUNE);
		Assert.assertEquals(carro4.getDataCheckIn().get(Calendar.DAY_OF_MONTH),
				17);
		Calendar novaDataTermino = new GregorianCalendar(2015, Calendar.JUNE,
				16);
		Assert.assertTrue(novaDataTermino.before(carro4.getDataCheckIn()));
		try {
			carro4.setDataCheckOut(novaDataTermino);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(carro2.getDataCheckIn(), dataInicio2);
		Assert.assertEquals(carro2.getDataCheckOut(), dataTermino2);
		Calendar dataAtual = new GregorianCalendar();
		try {
			carro2.setDataCheckOut(dataAtual);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(carro1.getDataCheckOut(), dataTermino1);
		try {
			carro1.setDataCheckOut(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		carro1.setDataCheckOut(dataTermino2);
		Assert.assertEquals(carro1.getDataCheckOut(), dataTermino2);

		Assert.assertEquals(carro3.getDataCheckOut(), dataTermino3);
		try {
			carro3.setDataCheckOut(new GregorianCalendar(2014,
					Calendar.FEBRUARY, 15));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}
		Assert.assertNotEquals(
				carro3.getDataCheckOut().get(Calendar.DAY_OF_MONTH), 15);
		Assert.assertNotEquals(carro3.getDataCheckOut().get(Calendar.MONTH), 1);
		Assert.assertNotEquals(carro3.getDataCheckOut().get(Calendar.YEAR), 2014);

		Assert.assertEquals(carro2.getDataCheckOut(), dataTermino2);
		carro2.setDataCheckOut(dataTermino3);
		Assert.assertEquals(carro2.getDataCheckOut(), dataTermino3);
	}

	@Test
	public void testaGetPreco() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro1.getIsTanqueCheio(), true);
		Assert.assertEquals(carro1.getIsAssegurado(), true);
		Assert.assertEquals(carro1.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro1.getNumeroDeDias(), 23);
		Assert.assertEquals(carro1.getPreco(), 1630, 0.001);
		carro1.setDataCheckOut(dataTermino3);
		Assert.assertEquals(carro1.getNumeroDeDias(), 30);
		Assert.assertEquals(carro1.getPreco(), 2050, 0.001);

		Assert.assertEquals(carro2.getIsTanqueCheio(), true);
		Assert.assertEquals(carro2.getIsAssegurado(), false);
		Assert.assertEquals(carro2.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro2.getNumeroDeDias(), 14);
		Assert.assertEquals(carro2.getPreco(), 1550, 0.001);
		carro2.setDataCheckOut(dataTermino1);
		Assert.assertEquals(carro2.getNumeroDeDias(), 18);
		Assert.assertEquals(carro2.getPreco(), 1950, 0.001);

		Assert.assertEquals(carro3.getIsTanqueCheio(), false);
		Assert.assertEquals(carro3.getIsAssegurado(), true);
		Assert.assertEquals(carro3.getTipoDeCarro(), carroLuxo);
		Assert.assertEquals(carro3.getNumeroDeDias(), 13);
		Assert.assertEquals(carro3.getPreco(), 1400, 0.001);
		carro3.setDataCheckOut(new GregorianCalendar(2015, Calendar.JULY, 22,
				13, 50));
		Assert.assertEquals(carro3.getNumeroDeDias(), 23);
		Assert.assertEquals(carro3.getPreco(), 2400, 0.001);

		Assert.assertEquals(carro4.getIsTanqueCheio(), false);
		Assert.assertEquals(carro4.getIsAssegurado(), false);
		Assert.assertEquals(carro4.getTipoDeCarro(), carroExecutivo);
		Assert.assertEquals(carro4.getNumeroDeDias(), 18);
		Assert.assertEquals(carro4.getPreco(), 1080, 0.001);
		carro4.setDataCheckOut(new GregorianCalendar(2015, Calendar.JULY, 19,
				10, 20));
		Assert.assertEquals(carro4.getNumeroDeDias(), 32);
		Assert.assertEquals(carro4.getPreco(), 1920, 0.001);
	}

	@Test
	public void testaToString() throws NullPointerException,
			DataInvalidaException {
		Assert.assertEquals(carro1.toString(), "SERVICO CARRO\n"
				+ "Tipo: Executivo\n"
				+ "Adicionais: [Tanque cheio = R$150.0, Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1630.0\n" + "Duracao: 23 dias\n"
				+ "Data Inicio: 12/06/2015 as 15:12\n"
				+ "Data Termino: 05/07/2015 as 22:27");
		carro1.setDataCheckIn(new GregorianCalendar(2015, Calendar.JUNE, 17, 20,
				16));
		Assert.assertEquals(carro1.toString(), "SERVICO CARRO\n"
				+ "Tipo: Executivo\n"
				+ "Adicionais: [Tanque cheio = R$150.0, Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1330.0\n" + "Duracao: 18 dias\n"
				+ "Data Inicio: 17/06/2015 as 20:16\n"
				+ "Data Termino: 05/07/2015 as 22:27");
		carro1.setDataCheckOut(new GregorianCalendar(2015, Calendar.JULY, 15,
				14, 53));
		Assert.assertEquals(carro1.toString(), "SERVICO CARRO\n"
				+ "Tipo: Executivo\n"
				+ "Adicionais: [Tanque cheio = R$150.0, Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1930.0\n" + "Duracao: 28 dias\n"
				+ "Data Inicio: 17/06/2015 as 20:16\n"
				+ "Data Termino: 15/07/2015 as 14:53");

		Assert.assertEquals(carro3.toString(), "SERVICO CARRO\n"
				+ "Tipo: Luxo\n" + "Adicionais: [Seguro = R$100.0]\n"
				+ "Preco Total: R$ 1400.0\n" + "Duracao: 13 dias\n"
				+ "Data Inicio: 29/06/2015 as 10:01\n"
				+ "Data Termino: 12/07/2015 as 07:32");
		carro3.setDataCheckOut(new GregorianCalendar(2015, Calendar.JULY, 29, 9,
				30));
		carro3.setDataCheckIn(new GregorianCalendar(2015, Calendar.JULY, 3, 10,
				10));
		Assert.assertEquals(carro3.toString(), "SERVICO CARRO\n"
				+ "Tipo: Luxo\n" + "Adicionais: [Seguro = R$100.0]\n"
				+ "Preco Total: R$ 2700.0\n" + "Duracao: 26 dias\n"
				+ "Data Inicio: 03/07/2015 as 10:10\n"
				+ "Data Termino: 29/07/2015 as 09:30");
	}

	@Test
	public void testaEquals() throws NullPointerException,
			DataInvalidaException {
		Carro carro1 = new Carro(carroExecutivo, dataInicio1, dataTermino1,
				true, true);
		Carro carro2 = new Carro(carro1.getTipoDeCarro(),
				carro1.getDataCheckIn(), carro1.getDataCheckOut(),
				carro1.getIsTanqueCheio(), carro1.getIsAssegurado());
		Assert.assertTrue(carro1.equals(carro2));
		carro1.setDataCheckIn(new GregorianCalendar(2015, Calendar.JUNE, 20, 15,
				0));
		Assert.assertFalse(carro1.equals(carro2));
		carro2.setDataCheckIn(new GregorianCalendar(2015, Calendar.JUNE, 20, 15,
				0));
		Assert.assertTrue(carro1.equals(carro2));
		carro2 = null;
		Assert.assertFalse(carro1.equals(carro2));

		Carro carro3 = new Carro(carroLuxo, dataInicio2, dataTermino2, false,
				false);
		Carro carro4 = new Carro(carroExecutivo, dataInicio2, dataTermino2,
				false, false);
		Assert.assertNotEquals(carro3.getTipoDeCarro(), carro4.getTipoDeCarro());
		Assert.assertFalse(carro3.equals(carro4));

		Carro carro5 = new Carro(carroLuxo, dataInicio3, dataTermino3, false,
				true);
		Carro carro6 = new Carro(carroLuxo, dataInicio3, dataTermino1, false,
				true);
		Assert.assertNotEquals(carro5.getDataCheckOut(), carro6.getDataCheckOut());
		carro6.setDataCheckOut(dataTermino3);
		Assert.assertTrue(carro5.equals(carro6));
	}

}
