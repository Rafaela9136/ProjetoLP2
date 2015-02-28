package hotel;

import excecoes.LoginInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;

public class Conta {
	public static final int TAMANHO_MINIMO_LOGIN = 6;
	public static final int TAMANHO_MINIMO_SENHA = 6;

	String login;
	String senha;
	String nomeCompleto;
	TipoFuncionario tipo;
	
	public Conta(String login, String senha, String nomeCompleto,
			TipoFuncionario tipo) throws LoginInvalidoException, NullPointerException, SenhaInvalidaException, NomeCompletoInvalidoException {
		verificaLoginValido(login);
		verificaSenhaValida(senha);
		verificaNomeCompletoValido(nomeCompleto);
		if(tipo == null)
			throw new NullPointerException();
		this.login = login;
		this.senha = senha;
		this.nomeCompleto = nomeCompleto;
		this.tipo = tipo;
	}// Construtor

	public String getLogin() {
		return login;
	}// getLogin

	public void setLogin(String login) throws LoginInvalidoException {
		verificaLoginValido(login);
		this.login = login;
	}// setLogin

	public String getSenha() {
		return senha;
	}// getSenha

	public void setSenha(String senha) throws SenhaInvalidaException {
		verificaSenhaValida(senha);
		this.senha = senha;
	}// setSenha

	public TipoFuncionario getTipo() {
		return tipo;
	}// getTipo

	public void setTipo(TipoFuncionario tipo) throws NullPointerException {
		if (tipo == null)
			throw new NullPointerException();
		this.tipo = tipo;
	}// setTipo

	public String getNomeCompleto() {
		return nomeCompleto;
	}// getNomeCompleto

	private void verificaLoginValido(String login)
			throws LoginInvalidoException, NullPointerException {
		if (login == null)
			throw new NullPointerException();
		if (login.contains(" ") || login.length() < TAMANHO_MINIMO_LOGIN)
			throw new LoginInvalidoException();

	}// verificaLoginValido

	private void verificaSenhaValida(String senha)
			throws SenhaInvalidaException, NullPointerException {
		if (senha == null)
			throw new NullPointerException();
		if (senha.contains(" ") || senha.length() < TAMANHO_MINIMO_LOGIN)
			throw new SenhaInvalidaException();
	}// verificaSenhaValida
	
	public void verificaNomeCompletoValido(String nomeCompleto) throws NomeCompletoInvalidoException {
		if(nomeCompleto == null)
			throw new NullPointerException();
		if(!nomeCompleto.contains(" "))
			throw new NomeCompletoInvalidoException();
	}// verificaNomeCompletoValido

}// Conta
