package testes;

import static org.junit.Assert.fail;
import hotel.Estados;
import hotel.Hospede;
import hotel.Opiniao;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.EstouroDeCaracteresException;
import excecoes.NotaInvalidaException;

public class HospedeTest {
	
	private Hospede hospede1, hospede2;
	private String SET_NAO_MORA_NO_BRASIL = "--";

	@Before
	public void CriaObjetos() throws NullPointerException, CPFInvalidoException, DataInvalidaException {
		hospede1 = new Hospede ("nome", 10, 6, 1987, true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678911");
		hospede2 = new Hospede("nome", 10, 9, 1996);
	}
	
	@Test
	public void testConstrutorCompleto(){
		try {
			new Hospede (null, 10, 6, 1987, true, "pais", Estados.RO, "cidade"
					, "endereco", "numero", "12345678911");
			Assert.fail("Esperava excecao");
		} catch(CPFInvalidoException e){
			fail();
		} catch( DataInvalidaException  e){
			fail();
		} catch (NullPointerException e){
			
		}try {
			new Hospede ("nome", -2, 13, 2018, true, "pais", Estados.RO, "cidade"
					, "endereco", "numero", "12345678911");
			Assert.fail("Esperava excecao");
		} catch(CPFInvalidoException e){
			fail();
		} catch( DataInvalidaException  e){
			
		} catch (NullPointerException e){
			fail();
		}try {
			new Hospede ("nome", 10, 6, 1987, true, "pais", Estados.RO, "cidade"
					, "endereco", "numero", "234");
			Assert.fail("Esperava excecao");
		} catch(CPFInvalidoException e){
		} catch( DataInvalidaException  e){
			fail();
		} catch (NullPointerException e){
			fail();
		}
	}
	
	@Test
	public void testEquals() throws NullPointerException, CPFInvalidoException, DataInvalidaException  {
		Hospede hospede;
		hospede = new Hospede("Claudio", 10, 6, 1987, true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", 10, 6, 1987, false, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", 10, 6, 1987, true, "pais", Estados.AC, "cidade", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", 10, 6, 1987, true, "pais", Estados.AC, "Campina Grande", "endereco", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", 10, 6, 1987, true, "pais", Estados.RO, "cidade", "R Manoel Morais", "numero", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede("nome", 10, 6, 1987, true, "pais", Estados.AC, "cidade", "endereco", "529", "12345678911");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede ("nome", 10, 6, 1987, true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678910");
		Assert.assertFalse(hospede1.equals(hospede));
		hospede = new Hospede ("nome", 10, 6, 1987, true, "pais", Estados.RO, "cidade", "endereco", "numero", "12345678911");
		Assert.assertTrue(hospede1.equals(hospede));
		Assert.assertFalse(hospede2.equals(hospede));
		hospede = new Hospede ("nome", 10, 6, 1987, false, SET_NAO_MORA_NO_BRASIL, Estados.XX, SET_NAO_MORA_NO_BRASIL, 
				SET_NAO_MORA_NO_BRASIL,SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);	
		Assert.assertTrue(hospede2.equals(hospede));
	}

}