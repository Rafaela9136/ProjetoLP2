package hotel;

import java.io.*;

import excecoes.LoginInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;

public class Conta implements Serializable {
	public static final int TAMANHO_MINIMO_LOGIN = 6;
	public static final int TAMANHO_MINIMO_SENHA = 6;

	String login, senha, nomeCompleto;
	TipoFuncionario tipo;

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
	
	public void setNomeCompleto(String nomeCompleto) throws NomeCompletoInvalidoException {
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

	@Override
	public String toString() {
		final String FIM_LINHA = System.getProperty("line.separator");
		return "Conta" + FIM_LINHA
				+ "Nome Completo: " + getNomeCompleto() + FIM_LINHA
				+ "Tipo do Funcionario: " + getTipo().getNome() + FIM_LINHA
				+ "Login: " + getLogin() + FIM_LINHA
				+ "Senha: " + getSenha();
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

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Conta))
			return false;
		Conta outraConta = (Conta) obj;
		return getNomeCompleto().equals(outraConta.getNomeCompleto())
				&& getLogin().equals(outraConta.getLogin())
				&& getSenha().equals(outraConta.getSenha())
				&& getTipo().equals(outraConta.getTipo());
	}// equals

	// Para reiniciar o arquivo das contas
//	public static void main(String[] args) throws FileNotFoundException,
//			IOException, LoginInvalidoException, NullPointerException,
//			SenhaInvalidaException, NomeCompletoInvalidoException, ClassNotFoundException {
//		List<Conta> contas = new ArrayList<Conta>();
//		Conta gerente = new Conta("gerente", "prog2ufcg", "Gerente do Hotel",
//				TipoFuncionario.GERENTE);
//		contas.add(gerente);
//		ObjectOutputStream out = new ObjectOutputStream(
//				new BufferedOutputStream(
//						new FileOutputStream("contasHotel.dat")));
//		out.writeObject(contas);
//		out.close();
//		
//		
//		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("contasHotel.dat")));
//		List<Conta> contasLidas = (List<Conta>) in.readObject();
//		in.close();
//		for (Conta conta : contasLidas) {
//			System.out.println(conta);
//		}// for
//	}// main

}// Conta
