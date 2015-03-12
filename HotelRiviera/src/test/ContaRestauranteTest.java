package test;

import java.text.SimpleDateFormat;

import models.ContaRestaurante;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import exceptions.ValorNegativoException;

public class ContaRestauranteTest {

	ContaRestaurante conta1, conta2;

	@Before
	public void criaObjs() throws ValorNegativoException {
		conta1 = new ContaRestaurante(700);
		conta2 = new ContaRestaurante(200);
	}

	@Test
	public void testaCria() {
		Assert.assertEquals(conta1.getNome(), "Conta do Restaurante");
		try {
			new ContaRestaurante(-100);
		} catch (ValorNegativoException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaGetPreco() throws ValorNegativoException {
		Assert.assertEquals(conta1.getPreco(), 700, 0.01);
		Assert.assertEquals(conta2.getPreco(), 200, 0.01);

		ContaRestaurante contarest = new ContaRestaurante(100);
		Assert.assertEquals(contarest.getPreco(), 100, 0.01);
	}

	@Test
	public void testaDatas() {
		Assert.assertEquals(conta1.getDataCheckIn(), conta1.getDataCheckOut());
		Assert.assertEquals(conta2.getDataCheckIn(), conta2.getDataCheckOut());
	}

	@Test
	public void testaToString() throws ValorNegativoException {
		final String FIM_LINHA = System.getProperty("line.separator");
		Assert.assertEquals(conta1.toString(), "Conta do Restaurante"
				+ FIM_LINHA + "Valor: R$ 700,00" + FIM_LINHA
				+ "Data: " + new SimpleDateFormat("dd/MM/yyyy").format(conta1
						.getDataCheckIn().getTime()));
		ContaRestaurante conta = new ContaRestaurante(1200.50);
		Assert.assertEquals(
				conta.toString(),
				"Conta do Restaurante"
						+ FIM_LINHA
						+ "Valor: R$ 1.200,50"
						+ FIM_LINHA
						+ "Data: "
						+ new SimpleDateFormat("dd/MM/yyyy").format(conta
								.getDataCheckIn().getTime()));
	}
	
	@Test
	public void testaEquals() throws ValorNegativoException {
		Assert.assertFalse(conta1.equals(null));
		Assert.assertFalse(conta1.equals(conta2));
		ContaRestaurante outra = new ContaRestaurante(conta2.getPreco());
		Assert.assertTrue(outra.equals(conta2));;
	}
}
