package testes;

import hotel.Estados;
import hotel.Hospede;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;

public class HospedeTest {
	
	private Hospede hospede1, hospede2;
	private Calendar c = Calendar.getInstance();
	private String SET_NAO_MORA_NO_BRASIL = "--";

	@Before
	public void CriaObjetos() throws NullPointerException, CPFInvalidoException {
		hospede1 = new Hospede ("nome", c.getInstance(), true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678911");
		hospede2 = new Hospede("nome", c.getInstance());
	}
	
	@Test
	public void testEquals() throws NullPointerException, CPFInvalidoException  {
		Hospede hospede;
		hospede = new Hospede("Claudio", c.getInstance(), true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678910");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", c.getInstance(), true, "pais", Estados.AC, "cidade", "endereco", "numero", "12345678910");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", c.getInstance(), true, "pais", Estados.AC, "Campina Grande", "endereco", "numero", "12345678910");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", c.getInstance(), true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678910");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede ("nome", c.getInstance(), true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678911");
		Assert.assertTrue(hospede1.equals(hospede));
		Assert.assertFalse(hospede2.equals(hospede));
		hospede = new Hospede ("nome", c.getInstance(), false, SET_NAO_MORA_NO_BRASIL, Estados.XX, SET_NAO_MORA_NO_BRASIL, 
				SET_NAO_MORA_NO_BRASIL,SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);	
	}
	

}