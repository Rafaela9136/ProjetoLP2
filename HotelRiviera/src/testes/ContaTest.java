package testes;

import hotel.Conta;
import hotel.TipoFuncionario;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.LoginInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;

public class ContaTest {

	private Conta conta1, conta2, conta3;

	@Before
	public void criaObjetos() throws LoginInvalidoException,
			NullPointerException, SenhaInvalidaException,
			NomeCompletoInvalidoException {
		conta1 = new Conta("usuario", "senha123", "Nome completo usuario",
				TipoFuncionario.BALCONISTA);
		conta2 = new Conta("paulo123", "oluap321", "Paulo Melo",
				TipoFuncionario.GERENTE);
		conta3 = new Conta("marcia46", "9467423", "Marcia Sousa",
				TipoFuncionario.BALCONISTA);
	}

	@Test
	public void testaCriaConta() throws LoginInvalidoException,
			SenhaInvalidaException, NomeCompletoInvalidoException {
		try {
			new Conta(null, "senhavalida", "Joao Pereira",
					TipoFuncionario.BALCONISTA);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("loginvalido", null, "Nome completo",
					TipoFuncionario.BALCONISTA);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("loginvalido", "senhavalida", null,
					TipoFuncionario.BALCONISTA);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("loginvalido", "senhavalida", "Nome completo valido",
					null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("login invalido", "senhavalida", "Nome completo valido",
					TipoFuncionario.BALCONISTA);
		} catch (LoginInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("login", "senhavalida", "Nome completo valido",
					TipoFuncionario.BALCONISTA);
		} catch (LoginInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("loginvalido", "senha invalida", "Nome completo",
					TipoFuncionario.BALCONISTA);
		} catch (SenhaInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("loginvalido", "senha", "Nome completo",
					TipoFuncionario.BALCONISTA);
		} catch (SenhaInvalidaException e) {
			Assert.assertTrue(true);
		}

		try {
			new Conta("loginvalido", "senhavalida", "Nomecompletoinvalido",
					TipoFuncionario.BALCONISTA);
		} catch (NomeCompletoInvalidoException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaSetLogin() throws LoginInvalidoException {
		Assert.assertEquals(conta1.getLogin(), "usuario");
		conta1.setLogin("usuarioconta1");
		Assert.assertEquals(conta1.getLogin(), "usuarioconta1");

		Assert.assertEquals(conta2.getLogin(), "paulo123");
		conta2.setLogin("paulo444");
		Assert.assertEquals(conta2.getLogin(), "paulo444");
	}

	@Test
	public void testaSetSenha() throws SenhaInvalidaException {
		Assert.assertEquals(conta1.getSenha(), "senha123");
		conta1.setSenha("senhaconta1");
		Assert.assertEquals(conta1.getSenha(), "senhaconta1");

		Assert.assertEquals(conta2.getSenha(), "oluap321");
		conta2.setSenha("832467");
		Assert.assertEquals(conta2.getSenha(), "832467");
	}

	@Test
	public void testaSetNomeCompleto() throws NomeCompletoInvalidoException {
		Assert.assertEquals(conta3.getNomeCompleto(), "Marcia Sousa");
		conta3.setNomeCompleto("Marcia Sousa Felix");
		Assert.assertEquals(conta3.getNomeCompleto(), "Marcia Sousa Felix");

		Assert.assertEquals(conta2.getNomeCompleto(), "Paulo Melo");
		conta2.setNomeCompleto("Paulo Ferreira Melo");
		Assert.assertEquals(conta2.getNomeCompleto(), "Paulo Ferreira Melo");
	}

	@Test
	public void testaTipoFuncionario() {
		Assert.assertEquals(conta1.getTipo(), TipoFuncionario.BALCONISTA);
		conta1.setTipo(TipoFuncionario.GERENTE);
		Assert.assertEquals(conta1.getTipo(), TipoFuncionario.GERENTE);

		Assert.assertEquals(conta2.getTipo(), TipoFuncionario.GERENTE);
		conta2.setTipo(TipoFuncionario.BALCONISTA);
		Assert.assertEquals(conta2.getTipo(), TipoFuncionario.BALCONISTA);

		try {
			conta3.setTipo(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
	}

	@Test
	public void testaToString() {
		final String FIM_LINHA = System.getProperty("line.separator");
		Assert.assertEquals(conta1.toString(), "Conta" + FIM_LINHA
				+ "Nome Completo: " + conta1.getNomeCompleto() + FIM_LINHA
				+ "Tipo do Funcionario: " + conta1.getTipo().getNome()
				+ FIM_LINHA + "Login: " + conta1.getLogin() + FIM_LINHA
				+ "Senha: " + conta1.getSenha());
		Assert.assertEquals(conta2.toString(), "Conta" + FIM_LINHA
				+ "Nome Completo: " + conta2.getNomeCompleto() + FIM_LINHA
				+ "Tipo do Funcionario: " + conta2.getTipo().getNome()
				+ FIM_LINHA + "Login: " + conta2.getLogin() + FIM_LINHA
				+ "Senha: " + conta2.getSenha());
		Assert.assertEquals(conta3.toString(), "Conta" + FIM_LINHA
				+ "Nome Completo: " + conta3.getNomeCompleto() + FIM_LINHA
				+ "Tipo do Funcionario: " + conta3.getTipo().getNome()
				+ FIM_LINHA + "Login: " + conta3.getLogin() + FIM_LINHA
				+ "Senha: " + conta3.getSenha());
	}

	@Test
	public void testaEquals() throws LoginInvalidoException,
			SenhaInvalidaException, NomeCompletoInvalidoException {
		Assert.assertFalse(conta1.equals(null));
		Assert.assertFalse(conta1.equals(conta2));
		Assert.assertFalse(conta2.equals(conta1));
		conta2.setLogin(conta1.getLogin());
		Assert.assertFalse(conta1.equals(conta2));
		conta2.setSenha(conta1.getSenha());
		Assert.assertFalse(conta1.equals(conta2));
		conta2.setNomeCompleto(conta1.getNomeCompleto());
		Assert.assertFalse(conta1.equals(conta2));
		conta2.setTipo(conta1.getTipo());
		Assert.assertTrue(conta1.equals(conta2));
		Assert.assertTrue(conta2.equals(conta1));

		Conta conta4 = new Conta(conta3.getLogin(), conta3.getSenha(),
				conta3.getNomeCompleto(), conta3.getTipo());
		Assert.assertTrue(conta4.equals(conta3));
		conta4.setSenha("senha85467rd");
		Assert.assertFalse(conta4.equals(conta3));
		conta3.setLogin("login612df5f");
		Assert.assertFalse(conta4.equals(conta3));
		conta3.setLogin(conta4.getLogin());
		conta4.setSenha(conta3.getSenha());
		Assert.assertTrue(conta4.equals(conta3));
	}

}
