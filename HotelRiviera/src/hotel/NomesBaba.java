package hotel;

public enum NomesBaba {
		
	MARILUCIA_GALVAO("Marilucia Galvao"), 
	ROSANETE_DA_SILVA("Rosanete da Silva"), ROSILDA_lOURDES("Rosilda Lourdes");
	
	public String nome;
	
	NomesBaba(String nome) {
		this.nome = nome;
	}// Construtor
	
	public String getNome() {
		return nome;
	}// getNome
		
}// NomesBaba
