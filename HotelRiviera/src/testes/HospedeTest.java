package testes;

import hotel.Estado;
import hotel.Hospede;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;
import excecoes.CartaoInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.StringVaziaException;

public class HospedeTest {

	private Hospede hospede1, hospede2, hospede3;
	private static final String SET_NAO_MORA_NO_BRASIL = "--";
	private Calendar data1 = new GregorianCalendar(1980, Calendar.MARCH, 20),
			data2 = new GregorianCalendar(1974, Calendar.OCTOBER, 13),
			data3 = new GregorianCalendar(1987, Calendar.DECEMBER, 5);
	private String cpf1 = "01234567891", cpf2 = "32165498761",
			cpf3 = "25896314720";
	private String cartao1 = "5624984231566251", cartao2 = "6032157482194203",
			cartao3 = "2320168497262164";

	@Before
	public void CriaObjetos() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException {
		hospede1 = new Hospede("Joao", data1, "Brasil", Estado.PB,
				"Campina Grande", "Rua Borborema", "234", "24564975431",
				cartao1);
		hospede2 = new Hospede("Jose", data2, "Brasil", Estado.RN, "Natal",
				"Rua Suassuna", "123", "16235487956", cartao2);
		hospede3 = new Hospede("Mohammed", data3, cartao3);
	}

	@Test
	public void testaCriaHospede() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException {
		try {
			new Hospede(null, data1, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("", data2, cartao3);
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Maria", null, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Joaquim", data3, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Pedro", data2, "123");
		} catch (CartaoInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Nome", data1, "");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Nome", data2, "", Estado.AC, "Cidade", "Rua", "187",
					cpf3, cartao3);
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Carlos", data3, null, Estado.ES, "qualquer",
					"qualquer", "321", cpf3, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("nome", data3, "Brasil", null, "cidAADE", "Avenida",
					"222", cpf2, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Eu", data1, "brasil", Estado.PR, "", "rua qualquer",
					"123", cpf1, cartao3);
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("voce", data2, "brasel", Estado.PE, null,
					"avenida qualquer", "754", cpf2, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("nome valido", data3, "Brasil", Estado.PB, "CG", "",
					"12", cpf1, cartao2);
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Augusto", data2, "Brasil", Estado.AL, "cidade", null,
					"364", cpf3, cartao2);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Marta", data1, "Brasil", Estado.MA, "Sao Luis",
					"rua valida", "", cpf1, cartao3);
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("nomezinho", data1, "Brasil", Estado.PI, "Teresina",
					"rua valida", null, cpf1, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Gabriel", data3, "Brazil", Estado.TO, "Palmas",
					"Rua America", "147", null, cartao2);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Gabriela", data3, "Brasil", Estado.PR, "Cidade",
					"Rua rua", "987", "123456", cartao3);
		} catch (CPFInvalidoException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaGetSetNome() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException {
		Assert.assertEquals(hospede1.getNome(), "Joao");
		Assert.assertEquals(hospede2.getNome(), "Jose");
		Assert.assertEquals(hospede3.getNome(), "Mohammed");

		Hospede hosp = new Hospede("Jose", data1, cartao1);
		Assert.assertEquals(hosp.getNome(), "Jose");
		try {
			hosp.setNome(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		try {
			hosp.setNome(" ");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}
		Assert.assertEquals(hosp.getNome(), "Jose");
		hosp.setNome("Jose Pereira");
		Assert.assertEquals(hosp.getNome(), "Jose Pereira");

		Hospede hospe = new Hospede("Fernando Braz de Melo", data2, "Brasil",
				Estado.RS, "Cachoeira", "Rua da mata", "333", cpf3, cartao3);
		Assert.assertEquals(hospe.getNome(), "Fernando Braz de Melo");
		hospe.setNome("Fernando Braz");
		Assert.assertNotEquals(hospe.getNome(), "Fernando Braz de Melo");
		Assert.assertEquals(hospe.getNome(), "Fernando Braz");
	}
	
	@Test
	public void testaGetSetDataNascimento() throws NullPointerException,
			DataInvalidaException, CPFInvalidoException, StringVaziaException,
			CartaoInvalidoException {
		Assert.assertEquals(hospede1.getDataNascimento(), data1);
		Assert.assertEquals(hospede1.getDataNascimento().get(Calendar.DAY_OF_MONTH), 20);
		Assert.assertEquals(hospede1.getDataNascimento().get(Calendar.MONTH), Calendar.MARCH);
		Assert.assertEquals(hospede1.getDataNascimento().get(Calendar.YEAR), 1980);
		
		Assert.assertEquals(hospede2.getDataNascimento(), data2);
		Assert.assertEquals(hospede2.getDataNascimento().get(Calendar.DAY_OF_MONTH), 13);
		Assert.assertEquals(hospede2.getDataNascimento().get(Calendar.MONTH), Calendar.OCTOBER);
		Assert.assertEquals(hospede2.getDataNascimento().get(Calendar.YEAR), 1974);
		
		Assert.assertEquals(hospede3.getDataNascimento(), data3);
		Assert.assertEquals(hospede3.getDataNascimento().get(Calendar.DAY_OF_MONTH), 5);
		Assert.assertEquals(hospede3.getDataNascimento().get(Calendar.MONTH), Calendar.DECEMBER);
		Assert.assertEquals(hospede3.getDataNascimento().get(Calendar.YEAR), 1987);
		
		Hospede hospede = new Hospede("Fabio", new GregorianCalendar(1990, Calendar.OCTOBER, 11), cartao3);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.DAY_OF_MONTH), 11);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.MONTH), Calendar.OCTOBER);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.YEAR), 1990);
		try {
			hospede.setDataNascimento(null);
		} catch (NullPointerException e) {
			
		}
		try {
			hospede.setDataNascimento(new GregorianCalendar(2015, Calendar.JULY, 18));
		} catch (DataInvalidaException e) {
			
		}
		hospede.setDataNascimento(new GregorianCalendar(1990, Calendar.OCTOBER, 12));
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.DAY_OF_MONTH), 12);
	}

	// @Test
	// public void testConstrutorCompleto() {
	// try {
	// new Hospede(null, data, true, "pais", Estado.RO, "cidade",
	// "endereco", "numero", "12345678911");
	// Assert.fail("Esperava excecao");
	// } catch (CPFInvalidoException e) {
	// fail();
	// } catch (NullPointerException e) {
	//
	// }
	// try {
	// new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
	// "endereco", "numero", "234");
	// Assert.fail("Esperava excecao");
	// } catch (CPFInvalidoException e) {
	//
	// } catch (NullPointerException e) {
	// fail();
	// }
	// try {
	// new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
	// "endereco", "numero", "1234567891011");
	// Assert.fail("Esperava excecao");
	// } catch (CPFInvalidoException e) {
	//
	// } catch (NullPointerException e) {
	//
	// }
	// }
	//
	// @Test
	// public void testEquals() throws NullPointerException,
	// CPFInvalidoException,
	// DataInvalidaException {
	// Hospede hospede;
	// hospede = new Hospede("Claudio", data, true, "pais", Estado.RO,
	// "cidade", "endereco", "numero", "12345678911");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, false, "pais", Estado.RO, "cidade",
	// "endereco", "numero", "12345678911");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, true, "pais", Estado.AC, "cidade",
	// "endereco", "numero", "12345678911");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, true, "pais", Estado.AC,
	// "Campina Grande", "endereco", "numero", "12345678911");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
	// "R Manoel Morais", "numero", "12345678911");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, true, "pais", Estado.AC, "cidade",
	// "endereco", "529", "12345678911");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
	// "endereco", "numero", "12345678910");
	// Assert.assertFalse(hospede1.equals(hospede));
	// hospede = new Hospede("nome", data, true, "pais", Estado.RO, "cidade",
	// "endereco", "numero", "12345678911");
	// Assert.assertTrue(hospede1.equals(hospede));
	// Assert.assertFalse(hospede2.equals(hospede));
	// hospede = new Hospede("nome", data, false, SET_NAO_MORA_NO_BRASIL,
	// Estado.XX, SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
	// SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);
	// Assert.assertTrue(hospede2.equals(hospede));
	// }

}