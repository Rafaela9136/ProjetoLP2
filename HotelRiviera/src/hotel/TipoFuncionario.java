package hotel;

/**
 * Enum usava para estabelecer o tipo do funcionario na criacao de contas para
 * acessar o sistema.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public enum TipoFuncionario {
	GERENTE("Gerente"), BALCONISTA("Balconista");

	String nome;

	private TipoFuncionario(String nome) {
		this.nome = nome;
	}// Construtor

	/**
	 * Recupera o nome.
	 * 
	 * @return O nome.
	 */
	public String getNome() {
		return nome;
	}// getNome
}// TipoFuncionario
