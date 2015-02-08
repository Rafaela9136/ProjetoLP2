package hotel;

import java.util.Calendar;

import excecoes.CPFInvalidoException;

public class Hospede {

	private static final String SET_NAO_MORA_NO_BRASIL = "--";
	public static final boolean NAO_BRASILEIRO = false;

	private String nome, pais, cidade, logradouro, numero, cpf;
	private Calendar dataNascimento;
	private boolean moraNoBrasil;
	private Estado estado;

	public Hospede(String nome, Calendar dataNascimento, boolean moraNoBrasil,
			String pais, Estado estado, String cidade, String logradouro,
			String numero, String cpf) throws NullPointerException,
			CPFInvalidoException {
		verificaParametro(nome);
		verificaParametro(dataNascimento);
		verificaParametro(pais);
		verificaParametro(estado);
		verificaParametro(cidade);
		verificaParametro(logradouro);
		verificaParametro(numero);
		verificaCPF(cpf);
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cpf = cpf;
	}// Construtor 1

	public Hospede(String nome, Calendar dataNascimento)
			throws NullPointerException, CPFInvalidoException {
		this(nome, dataNascimento, NAO_BRASILEIRO, SET_NAO_MORA_NO_BRASIL,
				Estado.XX, SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);
	}// Construtor 2

	public String getNome() {
		return nome;
	}
	
	public Calendar getDataNascimento() {
		return dataNascimento;
	}
	
	public boolean isMoraNoBrasil() {
		return moraNoBrasil;
	}
	
	public void setMoraNoBrasil(boolean moraNoBrasil) {
		this.moraNoBrasil = moraNoBrasil;
	}
	
	public String getPais() {
		return pais;
	}
	
	public void setPais(String pais) {
		this.pais = pais;
	}
	
	public String getEstado() {
		return estado.name();
	}
	
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	public String getCidade() {
		return cidade;
	}
	
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCpf() {
		return cpf;
	}

	private void verificaParametro(Object parametro)
			throws NullPointerException {
		if (parametro == null)
			throw new NullPointerException();
	}

	private void verificaCPF(String cpf) throws NullPointerException,
			CPFInvalidoException {
		verificaParametro(cpf);
		if (cpf.length() != 11)
			throw new CPFInvalidoException();
	}

	@Override
	public String toString() {
		return "Hospede [nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", moraNoBrasil=" + moraNoBrasil + ", pais=" + pais
				+ ", estado=" + estado + ", cidade=" + cidade + ", endereco="
				+ logradouro + ", numero=" + numero + ", CPF=" + cpf + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
