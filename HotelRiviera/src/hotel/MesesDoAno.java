package hotel;

public enum MesesDoAno {
	JANEIRO("Janeiro"), FEVEREIRO("Fevereiro"), MARCO("Marco"), ABRIL("Abril"), 
	MAIO("Maio"), JUNHO("Junho"), JULHO("Julho"), AGOSTO("Agosto"), 
	SETEMBRO("Setembro"), OUTUBRO("Outubro"), NOVEMBRO("Novembro"), DEZEMBRO("Dezembro");

	private String nome;

	private MesesDoAno(String nome) {
		this.nome = nome;
	}// Construtorr

	public String getNome() {
		return nome;
	}// getNome

}// MesesDoAno
