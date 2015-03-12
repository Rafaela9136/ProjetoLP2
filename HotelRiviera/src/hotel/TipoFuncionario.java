package hotel;

public enum TipoFuncionario {
	GERENTE("Gerente"), BALCONISTA("Balconista");
	
	String nome;
	
	private TipoFuncionario(String nome) {
		this.nome = nome;
	}// Construtor
	
	public String getNome() {
		return nome;
	}// getNome
}// TipoFuncionario
