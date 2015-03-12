package hotel;

import java.io.*;

import excecoes.LoginInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;

/**
 * Classe de uma conta de usuario utilizada por um funcionario do hotel para
 * fazer o login no sistema.
 * 
 * @author Grupo
 * @version 1.0
 * 
 */
public class Conta implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final int TAMANHO_MINIMO_LOGIN = 6;
	public static final int TAMANHO_MINIMO_SENHA = 6;

	String login, senha, nomeCompleto;
	TipoFuncionario tipo;

	/**
	 * Cria uma conta com um login, senha, nome completo do funcionario e o tipo
	 * do funcionario.
	 * 
	 * @param login
	 *            O login escolhido para a conta.
	 * @param senha
	 *            A senha escolhida para a conta.
	 * @param nomeCompleto
	 *            O nome completo do funcionario que utilizara a conta.
	 * @param tipo
	 *            O tipo do funcionario do hotel, podendo ser um gerente ou um
	 *            recepcionista.
	 * @throws LoginInvalidoException
	 *             Se o login passado for vazio ou tiver menos de 6 caracteres.
	 * @throws NullPointerException
	 *             Se algum dos parametros passados for null.
	 * @throws SenhaInvalidaException
	 *             Se a senha passada for vazia ou tiver menos de 6 caracteres.
	 * @throws NomeCompletoInvalidoException
	 *             Se o nome completo nao tiver espacos, ou seja, tiver apenas
	 *             um nome.
	 */
	public Conta(String login, String senha, String nomeCompleto,
			TipoFuncionario tipo) throws LoginInvalidoException,
			NullPointerException, SenhaInvalidaException,
			NomeCompletoInvalidoException {
		verificaLoginValido(login);
		verificaSenhaValida(senha);
		verificaNomeCompletoValido(nomeCompleto);
		if (tipo == null)
			throw new NullPointerException();
		this.login = login;
		this.senha = senha;
		this.nomeCompleto = nomeCompleto;
		this.tipo = tipo;
	}// Construtor

	/**
	 * Retorna o login da conta.
	 * 
	 * @return O login da conta.
	 */
	public String getLogin() {
		return login;
	}// getLogin

	/**
	 * Altera o login da conta.
	 * 
	 * @param login
	 *            O novo login.
	 * @throws LoginInvalidoException
	 *             Se o login passado for vazio ou tiver menos de 6 caracteres.
	 */
	public void setLogin(String login) throws LoginInvalidoException {
		verificaLoginValido(login);
		this.login = login;
	}// setLogin

	/**
	 * Retorna a senha da conta.
	 * 
	 * @return A senha da conta.
	 */
	public String getSenha() {
		return senha;
	}// getSenha

	/**
	 * Altera a senha da conta.
	 * 
	 * @param senha
	 *            A nova senha.
	 * @throws SenhaInvalidaException
	 *             Se a senha passada for vazia ou tiver menos de 6 caracteres.
	 */
	public void setSenha(String senha) throws SenhaInvalidaException {
		verificaSenhaValida(senha);
		this.senha = senha;
	}// setSenha

	/**
	 * Retorna o tipo do funcionario, podendo ser gerente ou recepcionista.
	 * 
	 * @return O tipo do funcionario da conta.
	 */
	public TipoFuncionario getTipo() {
		return tipo;
	}// getTipo

	/**
	 * Alterna o tipo da conta.
	 * 
	 * @param tipo
	 *            O novo tipo.
	 * @throws NullPointerException
	 *             Se o parametro for null.
	 */
	public void setTipo(TipoFuncionario tipo) throws NullPointerException {
		if (tipo == null)
			throw new NullPointerException();
		this.tipo = tipo;
	}// setTipo

	/**
	 * Retorna o nome completo do funcionario.
	 * 
	 * @return O nome completo.
	 */
	public String getNomeCompleto() {
		return nomeCompleto;
	}// getNomeCompleto

	/**
	 * Altera o nome completo do usuario na conta.
	 * 
	 * @param nomeCompleto
	 *            O novo nome.
	 * @throws NomeCompletoInvalidoException
	 *             Se o nome completo nao tiver espacos, ou seja, tiver apenas
	 *             um nome.
	 */
	public void setNomeCompleto(String nomeCompleto)
			throws NomeCompletoInvalidoException {
		verificaNomeCompletoValido(nomeCompleto);
		this.nomeCompleto = nomeCompleto;
	}// setNomeCompleto

	private void verificaParametroNulo(String param) {
		if (param == null)
			throw new NullPointerException();
	}

	private void verificaLoginValido(String login)
			throws LoginInvalidoException, NullPointerException {
		verificaParametroNulo(login);
		if (login.contains(" ") || login.length() < TAMANHO_MINIMO_LOGIN)
			throw new LoginInvalidoException();

	}// verificaLoginValido

	private void verificaSenhaValida(String senha)
			throws SenhaInvalidaException, NullPointerException {
		verificaParametroNulo(senha);
		if (senha.contains(" ") || senha.length() < TAMANHO_MINIMO_LOGIN)
			throw new SenhaInvalidaException();
	}// verificaSenhaValida

	private void verificaNomeCompletoValido(String nomeCompleto)
			throws NomeCompletoInvalidoException {
		verificaParametroNulo(nomeCompleto);
		if (!nomeCompleto.contains(" "))
			throw new NomeCompletoInvalidoException();
	}// verificaNomeCompletoValido

	/**
	 * Retorna uma representacao da conta em String.
	 * 
	 * @return Os dados da conta em String.
	 */
	@Override
	public String toString() {
		final String FIM_LINHA = System.getProperty("line.separator");
		return "Conta" + FIM_LINHA + "Nome Completo: " + getNomeCompleto()
				+ FIM_LINHA + "Tipo do Funcionario: " + getTipo().getNome()
				+ FIM_LINHA + "Login: " + getLogin() + FIM_LINHA + "Senha: "
				+ getSenha();
	}// toString

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		result = prime * result
				+ ((nomeCompleto == null) ? 0 : nomeCompleto.hashCode());
		result = prime * result + ((senha == null) ? 0 : senha.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	/**
	 * Verifica se duas contas sao iguais pelos seus atributos.
	 * 
	 * @return True se forem iguais ou False caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Conta)) {
			return false;
		}
		Conta outraConta = (Conta) obj;
		return getNomeCompleto().equals(outraConta.getNomeCompleto())
				&& getLogin().equals(outraConta.getLogin())
				&& getSenha().equals(outraConta.getSenha())
				&& getTipo().equals(outraConta.getTipo());
	}// equals

}// Conta
