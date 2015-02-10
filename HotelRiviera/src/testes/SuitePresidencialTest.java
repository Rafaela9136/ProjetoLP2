package testes;

import hotel.*;

import org.junit.*;

import excecoes.CamaExtraException;

public class SuitePresidencialTest {

	Quarto suite1, suite2;

	@Before
	public void criaSuite() {
		suite1 = new SuitePresidencial();
		suite2 = new SuitePresidencial();
	}

	@Test
	public void testaValorDiaria() {
		Assert.assertEquals(suite1.getPreco(), 1200, 0.0001);
		Assert.assertEquals(suite2.getPreco(), 1200, 0.0001);
	}

	@Test
	public void testaEquals() throws NullPointerException, CamaExtraException {
		Assert.assertTrue(suite1.equals(suite2));
		Assert.assertFalse(suite1.equals(new QuartoExecutivo(false,
				TiposDeQuarto.DUPLO)));
	}
}
