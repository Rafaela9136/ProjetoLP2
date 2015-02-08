package testes;

import org.junit.*;

import excecoes.ValorNegativoException;
import hotel.Frigobar;

public class TestFrigobar {
	private Frigobar frigobar1;
	private Frigobar frigobar2;

	@Before
	public void criaObjetos() {
		frigobar1 = new Frigobar();

	}// criaObjetos

	@Test
	public void testSomaPreco() throws ValorNegativoException {
		frigobar1.somaPreco(50);
		Assert.assertEquals(50, frigobar1.getPreco(), 0.005);
		frigobar1.somaPreco(-40);
		Assert.assertEquals(10, frigobar1.getPreco(), 0.005);
		frigobar1.somaPreco(-10);
		Assert.assertEquals(0, frigobar1.getPreco(), 0.005);

		try {
			frigobar1.somaPreco(-10);
			Assert.fail("Deveria ter lancado ValorNegativoException");
		} catch (ValorNegativoException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

	}// testSomaPreco
	
	@Test
	public void TestEquals() throws ValorNegativoException {
		frigobar2 = new Frigobar();
		Assert.assertTrue(frigobar1.equals(frigobar2));
		frigobar2.somaPreco(10);
		Assert.assertFalse(frigobar1.equals(frigobar2));
		frigobar2.somaPreco(-10);
		Assert.assertTrue(frigobar1.equals(frigobar2));
		
	}// TestEquals
	
	
}// TestFrigobar