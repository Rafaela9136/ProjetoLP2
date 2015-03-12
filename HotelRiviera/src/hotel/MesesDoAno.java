package hotel;

/**
 * Enum conetendo os nomes dos meses do ano, e utilizada para mostrar o faturamento do hotel em um ano especifico.
 * @author Grupinho da Alegria
 *
 */
public enum MesesDoAno {
	JANEIRO("Janeiro"), FEVEREIRO("Fevereiro"), MARCO("Marco"), ABRIL("Abril"), 
	MAIO("Maio"), JUNHO("Junho"), JULHO("Julho"), AGOSTO("Agosto"), 
	SETEMBRO("Setembro"), OUTUBRO("Outubro"), NOVEMBRO("Novembro"), DEZEMBRO("Dezembro");

	private String nome;

	private MesesDoAno(String nome) {
		this.nome = nome;
	}// Construtorr

	/**
	 * Recupera o nome do mes.
	 * @return Nome do mes
	 */
	public String getNome() {
		return nome;
	}// getNome

}// MesesDoAno
