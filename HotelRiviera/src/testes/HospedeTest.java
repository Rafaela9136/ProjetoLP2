package testes;

import static org.junit.Assert.fail;
import hotel.Estado;
import hotel.Hospede;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;
import excecoes.DataInvalidaException;

public class HospedeTest {

	private Hospede hospede1, hospede2;
	private String SET_NAO_MORA_NO_BRASIL = "--";
	private Calendar data;

	@Before
	public void CriaObjetos() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException {
		hospede1 = new Hospede("nome", data, true, "pais", Estado.RO,
				"cidade", "endereco", "numero", "12345678911");
		hospede2 = new Hospede("nome", data);
	}

	@Test
	public void testConstrutorCompleto() {
		try {
			new Hospede(null, data, true, "pais", Estado.RO, "cidade",
					"endereco", "numero", "12345678911");
			Assert.fail("Esperava excecao");
		} catch (CPFInvalidoException e) {
			fail();
		} catch (NullPointerException e) {

		}
		try {
			new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
					"endereco", "numero", "234");
			Assert.fail("Esperava excecao");
		} catch (CPFInvalidoException e) {

		} catch (NullPointerException e) {
			fail();
		}
		try {
			new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
					"endereco", "numero", "1234567891011");
			Assert.fail("Esperava excecao");
		} catch (CPFInvalidoException e) {

		} catch (NullPointerException e) {

		}
	}

	@Test
	public void testEquals() throws NullPointerException, CPFInvalidoException,
			DataInvalidaException {
		Hospede hospede;
		hospede = new Hospede("Claudio", data, true, "pais", Estado.RO,
				"cidade", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, false, "pais", Estado.RO,
				"cidade", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, true, "pais", Estado.AC, "cidade",
				"endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, true, "pais", Estado.AC,
				"Campina Grande", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
				"R Manoel Morais", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, true, "pais", Estado.AC, "cidade",
				"endereco", "529", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
				"endereco", "numero", "12345678910");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
				"endereco", "numero", "12345678911");
		Assert.assertTrue(hospede1.equals(hospede));
		Assert.assertFalse(hospede2.equals(hospede));
		hospede = new Hospede("nome", data, false, SET_NAO_MORA_NO_BRASIL,
				Estado.XX, SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);
		Assert.assertTrue(hospede2.equals(hospede));
	}

}