package hotel;

import java.util.Calendar;

public class Hospede {
	private static final String SET_NAO_MORA_NO_BRASIL = "--";
	public static final boolean NAO_BRASILEIRO = false;
	
	String nome;
	Calendar dataNascimento;
	
	boolean moraNoBrasil;
	
	private String pais;
	private Estados estado;
	private String cidade;
	private String endereco;
	private String numero;
	
	private String CPF;
	
	public Hospede(String nome, Calendar dataNascimento,
			boolean moraNoBrasil, String pais, Estados estado,
			String cidade, String endereco, String numero, String CPF) throws Exception {
		checaParametroValido(nome);
		
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		
		if(moraNoBrasil) {
			this.pais = "Brasil";
			this.estado = estado;
			checaParametroValido(cidade);
			this.cidade = cidade;
			checaParametroValido(endereco);
			this.endereco = endereco;
			checaParametroValido(numero);
			this.numero = numero;
			checaParametroValido(CPF);
			this.CPF = CPF;
		} else {
			checaParametroValido(pais);
			this.pais = pais;
			this.estado = Estados.XX;
			this.cidade = SET_NAO_MORA_NO_BRASIL;
			this.endereco = SET_NAO_MORA_NO_BRASIL;
			this.numero = SET_NAO_MORA_NO_BRASIL;
			this.CPF = SET_NAO_MORA_NO_BRASIL;
		}// if
		
	}// Construtor 1
	
	public Hospede(String nome, Calendar dataNascimento) throws Exception {
		this(nome, dataNascimento, NAO_BRASILEIRO,
				SET_NAO_MORA_NO_BRASIL, Estados.XX,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);
	}// Construtor 2
	
	private void checaParametroValido(String parametro) throws Exception{
		if(parametro == null)
			throw new NullPointerException ();
	}// checaNomeValido

	public String getNome() {
		return nome;
	}// getNome

	public Calendar getDataNascimento() {
		return dataNascimento;
	}// getDataNascimento

	public boolean isMoraNoBrasil() {
		return moraNoBrasil;
	}// isMoraNoBrasil

	public String getPais() {
		return pais;
	}// getPais

	public String getEstado() {
		return estado.name();
	}// getEstado

	public String getCidade() {
		return cidade;
	}// getCidade

	public String getEndereco() {
		return endereco;
	}// getEndereco

	public String getNumero() {
		return numero;
	}// getNumero

	public String getCPF() {
		return CPF;
	}// getCPF
	
}// Hospede
