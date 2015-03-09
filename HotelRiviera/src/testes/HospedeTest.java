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
import excecoes.NumeroInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.StringVaziaException;

public class HospedeTest {

	private Hospede hospede1, hospede2, hospede3;
	private Calendar data1 = new GregorianCalendar(1980, Calendar.MARCH, 20),
			data2 = new GregorianCalendar(1974, Calendar.OCTOBER, 13),
			data3 = new GregorianCalendar(1987, Calendar.DECEMBER, 5);
	private String cpf1 = "231.574.344-30", cpf2 = "321.654.987-61",
			cpf3 = "258.963.147-20";
	private String cartao1 = "5624.9842.3156.6251",
			cartao2 = "6032.1574.8219.4203", cartao3 = "2320.1684.9726.2164";

	@Before
	public void CriaObjetos() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		hospede1 = new Hospede("Joao", data1, "Brasil", Estado.PB,
				"Campina Grande", "Rua Borborema", "234", cpf1, cartao1);
		hospede2 = new Hospede("Jose", data2, "Brasil", Estado.RN, "Natal",
				"Rua Suassuna", "123", cpf2, cartao2);
		hospede3 = new Hospede("Mohammed", data3, cartao3);
	}

	@Test
	public void testaCriaHospede() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
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
			new Hospede("$�*@#�", data1, cartao3);
		} catch (StringInvalidaException e) {
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
			new Hospede("voce", data2, "Brasil", Estado.PE, null,
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
			new Hospede("Gabriel", data3, "Brasil", Estado.TO, "Palmas",
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
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
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
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
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
			StringInvalidaException, StringVaziaException,
			CPFInvalidoException, CartaoInvalidoException {
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
		Assert.assertEquals(hospede1.getEstado(), "PB");
		Assert.assertEquals(hospede2.getEstado(), "RN");
		Assert.assertEquals(hospede3.getEstado(), "XX");

		try {
			hospede1.setEstado(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		hospede2.setEstado(Estado.AC);
		Assert.assertEquals(hospede2.getEstado(), "AC");
		hospede3.setEstado(Estado.AM);
		Assert.assertEquals(hospede3.getEstado(), "AM");
		hospede1.setEstado(Estado.AC);
		Assert.assertEquals(hospede1.getEstado(), "AC");
		Assert.assertEquals(hospede1.getEstado(), hospede2.getEstado());

		try {
			hospede2.setEstado(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		Assert.assertEquals(hospede2.getEstado(), "AC");

	}

	@Test
	public void testaGetSetCidade() throws StringVaziaException,
			StringInvalidaException, CPFInvalidoException,
			CartaoInvalidoException, NullPointerException {
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
	public void testaGetSetLogradouro() throws NullPointerException,
			StringInvalidaException, CPFInvalidoException,
			CartaoInvalidoException, StringVaziaException {
		Assert.assertEquals(hospede1.getLogradouro(), "Rua Borborema");
		Assert.assertEquals(hospede2.getLogradouro(), "Rua Suassuna");
		Assert.assertEquals(hospede3.getLogradouro(), "--");

		try {
			hospede1.setLogradouro(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setLogradouro("");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede1.setLogradouro("519196515");
		} catch (StringInvalidaException e) {
			Assert.assertTrue(true);
		}

		hospede3.setLogradouro(hospede1.getLogradouro());
		Assert.assertEquals(hospede3.getLogradouro(), hospede1.getLogradouro());
		hospede2.setLogradouro("Rua avenida");
		Assert.assertEquals(hospede2.getLogradouro(), "Rua avenida");
	}

	@Test
	public void testaGetSetNumero() throws NullPointerException,
			StringVaziaException, CPFInvalidoException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		Assert.assertEquals(hospede1.getNumero(), "234");
		Assert.assertEquals(hospede2.getNumero(), "123");
		Assert.assertEquals(hospede3.getNumero(), "--");

		try {
			hospede1.setNumero("154fijnf19123");
		} catch (NumeroInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede1.setNumero(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setNumero("   ");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setNumero("1651.516");
		} catch (NumeroInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede3.setNumero("%#�$#*($");
		} catch (NumeroInvalidoException e) {
			Assert.assertTrue(true);
		}

		hospede1.setNumero("2653");
		Assert.assertEquals(hospede1.getNumero(), "2653");
		hospede2.setNumero("32160");
		Assert.assertEquals(hospede2.getNumero(), "32160");
		hospede3.setNumero(hospede2.getNumero());
		Assert.assertEquals(hospede2.getNumero(), hospede3.getNumero());
	}

	@Test
	public void testaGetSetCpf() throws NullPointerException,
			CPFInvalidoException, StringVaziaException {
		Assert.assertEquals(hospede1.getCpf(), "231.574.344-30");
		Assert.assertEquals(hospede2.getCpf(), "321.654.987-61");
		Assert.assertEquals(hospede3.getCpf(), "--");

		try {
			hospede1.setCpf(" ");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede1.setCpf(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setCpf("91651212521");
		} catch (CPFInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setCpf("845.625.123.92");
		} catch (CPFInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede3.setCpf("abc.ifg.kgd-82");
		} catch (CPFInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede3.setCpf("864fds321fd");
		} catch (CPFInvalidoException e) {
			Assert.assertTrue(true);
		}

		hospede1.setCpf("524.682.316-71");
		Assert.assertEquals(hospede1.getCpf(), "524.682.316-71");
		hospede2.setCpf("312.132.021-89");
		Assert.assertEquals(hospede2.getCpf(), "312.132.021-89");
		hospede3.setCpf(hospede1.getCpf());
		Assert.assertEquals(hospede3.getCpf(), "524.682.316-71");
		Assert.assertEquals(hospede1.getCpf(), hospede3.getCpf());
	}

	@Test
	public void testaGetSetCartao() throws CartaoInvalidoException,
			StringVaziaException {
		Assert.assertEquals(hospede1.getCartaoDeCredito(),
				"5624.9842.3156.6251");
		Assert.assertEquals(hospede2.getCartaoDeCredito(),
				"6032.1574.8219.4203");
		Assert.assertEquals(hospede3.getCartaoDeCredito(),
				"2320.1684.9726.2164");

		try {
			hospede1.setCartaoDeCredito(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede1.setCartaoDeCredito("");
		} catch (StringVaziaException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setCartaoDeCredito("5842659481342016");
		} catch (CartaoInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setCartaoDeCredito("8459.6423.0213.845");
		} catch (CartaoInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede2.setCartaoDeCredito("fikjenifvc12e65v1");
		} catch (CartaoInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hospede3.setCartaoDeCredito("2346-1245-6235-1092");
		} catch (CartaoInvalidoException e) {
			Assert.assertTrue(true);
		}

		hospede1.setCartaoDeCredito("8542.2310.6210.8462");
		Assert.assertEquals(hospede1.getCartaoDeCredito(),
				"8542.2310.6210.8462");
		hospede2.setCartaoDeCredito("0213.6521.3941.3162");
		Assert.assertEquals(hospede2.getCartaoDeCredito(),
				"0213.6521.3941.3162");
		hospede3.setCartaoDeCredito(hospede2.getCartaoDeCredito());
		Assert.assertEquals(hospede3.getCartaoDeCredito(),
				hospede2.getCartaoDeCredito());
	}

	@Test
	public void testaToString() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		Assert.assertEquals(hospede1.toString(), "HOSPEDE" + "\nNome: Joao"
				+ "\nData de Nascimento: 20/03/1980" + "\nPais: Brasil"
				+ "\nEstado: PB" + "\nCidade: Campina Grande"
				+ "\nEndereco: Rua Borborema, 234" + "\nCPF: 231.574.344-30"
				+ "\nCartao de Credito: 5624.9842.3156.6251");
		Assert.assertEquals(hospede2.toString(), "HOSPEDE" + "\nNome: Jose"
				+ "\nData de Nascimento: 13/10/1974" + "\nPais: Brasil"
				+ "\nEstado: RN" + "\nCidade: Natal"
				+ "\nEndereco: Rua Suassuna, 123" + "\nCPF: 321.654.987-61"
				+ "\nCartao de Credito: 6032.1574.8219.4203");
		Assert.assertEquals(hospede3.toString(), "HOSPEDE" + "\nNome: Mohammed"
				+ "\nData de Nascimento: 05/12/1987" + "\nPais: --"
				+ "\nEstado: XX" + "\nCidade: --" + "\nEndereco: --, --"
				+ "\nCPF: --" + "\nCartao de Credito: 2320.1684.9726.2164");

		Hospede novoHospede = new Hospede("Paula", new GregorianCalendar(1990,
				Calendar.JULY, 3), "Brasil", Estado.MA, "Sao Luis",
				"Rua Antonio Melo", "621", "245.613.203-70",
				"5136.8452.1302.9764");

		Assert.assertEquals(novoHospede.toString(), "HOSPEDE" + "\nNome: Paula"
				+ "\nData de Nascimento: 03/07/1990" + "\nPais: Brasil"
				+ "\nEstado: MA" + "\nCidade: Sao Luis"
				+ "\nEndereco: Rua Antonio Melo, 621" + "\nCPF: 245.613.203-70"
				+ "\nCartao de Credito: 5136.8452.1302.9764");

		novoHospede.setNome("Paula Ramalho");

		Assert.assertEquals(novoHospede.toString(), "HOSPEDE"
				+ "\nNome: Paula Ramalho" + "\nData de Nascimento: 03/07/1990"
				+ "\nPais: Brasil" + "\nEstado: MA" + "\nCidade: Sao Luis"
				+ "\nEndereco: Rua Antonio Melo, 621" + "\nCPF: 245.613.203-70"
				+ "\nCartao de Credito: 5136.8452.1302.9764");

		novoHospede.setCidade("Algodao");
		novoHospede.setLogradouro("Rua Morais");
		novoHospede.setNumero("83");
		Assert.assertEquals(novoHospede.toString(), "HOSPEDE"
				+ "\nNome: Paula Ramalho" + "\nData de Nascimento: 03/07/1990"
				+ "\nPais: Brasil" + "\nEstado: MA" + "\nCidade: Algodao"
				+ "\nEndereco: Rua Morais, 83" + "\nCPF: 245.613.203-70"
				+ "\nCartao de Credito: 5136.8452.1302.9764");

		novoHospede.setCpf("245.613.203-60");
		Assert.assertEquals(novoHospede.toString(), "HOSPEDE"
				+ "\nNome: Paula Ramalho" + "\nData de Nascimento: 03/07/1990"
				+ "\nPais: Brasil" + "\nEstado: MA" + "\nCidade: Algodao"
				+ "\nEndereco: Rua Morais, 83" + "\nCPF: 245.613.203-60"
				+ "\nCartao de Credito: 5136.8452.1302.9764");

		novoHospede.setCartaoDeCredito("1364.2403.8492.3746");
		Assert.assertEquals(novoHospede.toString(), "HOSPEDE"
				+ "\nNome: Paula Ramalho" + "\nData de Nascimento: 03/07/1990"
				+ "\nPais: Brasil" + "\nEstado: MA" + "\nCidade: Algodao"
				+ "\nEndereco: Rua Morais, 83" + "\nCPF: 245.613.203-60"
				+ "\nCartao de Credito: 1364.2403.8492.3746");

	}

	@Test
	public void testaEquals() throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		Assert.assertFalse(hospede1.equals(null));
		Hospede hospede1 = new Hospede("Romario", data1, cartao1);
		Hospede hospede2 = new Hospede(hospede1.getNome(),
				hospede1.getDataNascimento(), hospede1.getCartaoDeCredito());
		Assert.assertEquals(hospede1.getNome(), hospede2.getNome());
		Assert.assertEquals(hospede1.getDataNascimento(), hospede2.getDataNascimento());
		Assert.assertEquals(hospede1.getCartaoDeCredito(), hospede2.getCartaoDeCredito());
		Assert.assertTrue(hospede1.equals(hospede2));

		hospede1.setPais("Brasil");
		hospede2.setPais(hospede1.getPais());
		Assert.assertEquals(hospede1.getPais(), hospede2.getPais());
		hospede2.setEstado(Estado.RS);
		hospede1.setEstado(Estado.RS);
		Assert.assertEquals(hospede1.getEstado(), hospede2.getEstado());
		Assert.assertTrue(hospede1.equals(hospede2));

		Assert.assertEquals(hospede1.getCidade(), "--");
		Assert.assertEquals(hospede2.getCidade(), "--");
		hospede1.setCidade("Paulinia");
		Assert.assertFalse(hospede1.equals(hospede2));
		hospede2.setCidade("Paulinia");
		Assert.assertTrue(hospede1.equals(hospede2));

		hospede1.setLogradouro("Rua qualquer");
		hospede1.setNumero("134");
		hospede1.setCpf("642.513.748-13");
		Assert.assertFalse(hospede1.equals(hospede2));
		
		hospede2.setLogradouro(hospede1.getLogradouro());
		hospede2.setCpf(hospede1.getCpf());
		Assert.assertFalse(hospede1.equals(hospede2));
		
		hospede2.setNumero(hospede1.getNumero());
		Assert.assertTrue(hospede1.equals(hospede2));
		Assert.assertEquals(hospede1.toString(), hospede2.toString());
	}

}