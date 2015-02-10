package testes;

import hotel.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;
import excecoes.CartaoInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.StringInvalidaException;
import excecoes.StringVaziaException;

public class HospedeTest {

	private Hospede hospede1, hospede2, hospede3;
	private Calendar data1 = new GregorianCalendar(1980, Calendar.MARCH, 20),
			data2 = new GregorianCalendar(1974, Calendar.OCTOBER, 13),
			data3 = new GregorianCalendar(1987, Calendar.DECEMBER, 5);
	private String cpf1 = "102.524.114-20", cpf2 = "321.654.987-61",
			cpf3 = "258.963.147-20";
	private String cartao1 = "5624.9842.3156.6251", cartao2 = "6032.1574.8219.4203",
			cartao3 = "2320.1684.9726.2164";

	@Before
	public void CriaObjetos() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException {
		hospede1 = new Hospede("Joao", data1, "Brasil", Estado.PB, "Campina Grande", "Rua Borborema", "234", cpf1, cartao1);
		hospede2 = new Hospede("Jose", data2, "Brasil", Estado.RN, "Natal",
				"Rua Suassuna", "123", cpf2, cartao2);
		hospede3 = new Hospede("Mohammed", data3, cartao3);
	}

	@Test
	public void testaCriaHospede() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException {
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
			new Hospede("1918641", data1, cartao1);
		} catch (StringInvalidaException e) {
			Assert.assertTrue(true);
		}
		
		try {
			new Hospede("Paulo1615", data2, cartao2);
		} catch (StringInvalidaException e) {
			Assert.assertTrue(true);
		}
		
		try {
			new Hospede("$¨*@#¨", data1, cartao3);
		} catch (StringInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Maria", null, cartao1);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hospede("Paulo", new GregorianCalendar(), cartao1);
		} catch (DataInvalidaException e) {
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
			CartaoInvalidoException, StringInvalidaException {
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
			CartaoInvalidoException, StringInvalidaException {
		Assert.assertEquals(hospede1.getDataNascimento(), data1);
		Assert.assertEquals(
				hospede1.getDataNascimento().get(Calendar.DAY_OF_MONTH), 20);
		Assert.assertEquals(hospede1.getDataNascimento().get(Calendar.MONTH),
				Calendar.MARCH);
		Assert.assertEquals(hospede1.getDataNascimento().get(Calendar.YEAR),
				1980);

		Assert.assertEquals(hospede2.getDataNascimento(), data2);
		Assert.assertEquals(
				hospede2.getDataNascimento().get(Calendar.DAY_OF_MONTH), 13);
		Assert.assertEquals(hospede2.getDataNascimento().get(Calendar.MONTH),
				Calendar.OCTOBER);
		Assert.assertEquals(hospede2.getDataNascimento().get(Calendar.YEAR),
				1974);

		Assert.assertEquals(hospede3.getDataNascimento(), data3);
		Assert.assertEquals(
				hospede3.getDataNascimento().get(Calendar.DAY_OF_MONTH), 5);
		Assert.assertEquals(hospede3.getDataNascimento().get(Calendar.MONTH),
				Calendar.DECEMBER);
		Assert.assertEquals(hospede3.getDataNascimento().get(Calendar.YEAR),
				1987);

		Hospede hospede = new Hospede("Fabio", new GregorianCalendar(1990,
				Calendar.OCTOBER, 11), cartao3);
		Assert.assertEquals(
				hospede.getDataNascimento().get(Calendar.DAY_OF_MONTH), 11);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.MONTH),
				Calendar.OCTOBER);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.YEAR),
				1990);
		try {
			hospede.setDataNascimento(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede.setDataNascimento(new GregorianCalendar(2015,
					Calendar.JULY, 18));
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede.setDataNascimento(new GregorianCalendar());
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		hospede.setDataNascimento(new GregorianCalendar(1990, Calendar.OCTOBER,
				12));
		Assert.assertEquals(
				hospede.getDataNascimento().get(Calendar.DAY_OF_MONTH), 12);

		hospede.setDataNascimento(new GregorianCalendar(1992,
				Calendar.FEBRUARY, 4));
		Assert.assertEquals(
				hospede.getDataNascimento().get(Calendar.DAY_OF_MONTH), 4);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.MONTH),
				Calendar.FEBRUARY);
		Assert.assertEquals(hospede.getDataNascimento().get(Calendar.YEAR),
				1992);

	}

	@Test
	public void testaGetSetPais() throws NullPointerException,
			StringInvalidaException, StringVaziaException, CPFInvalidoException, CartaoInvalidoException {
		Assert.assertEquals(hospede1.getPais(), "Brasil");
		Assert.assertEquals(hospede2.getPais(), "Brasil");
		Assert.assertEquals(hospede3.getPais(), "--");

		try {
			hospede1.setPais("");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setPais(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		
		hospede3.setPais("Mexico");
		Assert.assertEquals(hospede3.getPais(), "Mexico");
		hospede2.setPais("Venezuela");
		Assert.assertEquals(hospede2.getPais(), "Venezuela");
		hospede2.setPais(hospede3.getPais());
		Assert.assertEquals(hospede2.getPais(), hospede3.getPais());
		
		hospede1.setPais("EUA");
		Assert.assertEquals(hospede1.getPais(), "EUA");
		
		try {
			hospede1.setPais("    ");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaGetSetEstado() {

	}

	@Test
	public void testaGetSetCidade() throws StringVaziaException,
			StringInvalidaException, CPFInvalidoException, CartaoInvalidoException {
		Assert.assertEquals(hospede1.getCidade(), "Campina Grande");
		Assert.assertEquals(hospede2.getCidade(), "Natal");
		Assert.assertEquals(hospede3.getCidade(), "--");

		try {
			hospede1.setCidade("");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setCidade(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede3.setCidade("     ");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		hospede1.setCidade("Soledade");
		Assert.assertEquals(hospede1.getCidade(), "Soledade");

		hospede2.setCidade("Parai");
		Assert.assertEquals(hospede2.getCidade(), "Parai");

		hospede3.setCidade("Qualquer");
		Assert.assertEquals(hospede3.getCidade(), "Qualquer");
	}

	@Test
	public void testaGetSetLogradouro() {

	}

	@Test
	public void testaGetSetNumero() {

	}

	@Test
	public void testaGetSetCpf() {

	}

	@Test
	public void testaGetSetCartao() {

	}

	@Test
	public void testaToString() {

	}

	@Test
	public void testaEquals() {

	}

}