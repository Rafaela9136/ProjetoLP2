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
	
	private VerificadorDeData verificador;

	public Hospede(String nome, int diaNascimento, int mesNascimento,
			int anoNascimento, boolean moraNoBrasil, String pais,
			Estados estado, String cidade, String endereco, String numero,
			String CPF) throws Exception {
		checaParametroValido(nome);
		verificador = new VerificadorDeData();
		verificador.verificaParametrosDeDataValidos(diaNascimento, mesNascimento, anoNascimento);
		
		dataNascimento.set(Calendar.DAY_OF_MONTH, diaNascimento);
		dataNascimento.set(Calendar.MONTH, mesNascimento);
		dataNascimento.set(Calendar.YEAR, anoNascimento);

		this.nome = nome;
		

		if (moraNoBrasil) {
			checaParametroValido(cidade);
			checaParametroValido(endereco);
			checaParametroValido(numero);
			checaParametroValido(CPF);
			this.pais = "Brasil";
			this.estado = estado;
			this.cidade = cidade;
			this.endereco = endereco;
			this.numero = numero;
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

	public Hospede(String nome, int diaNascimento, int mesNascimento, int anoNascimento) throws Exception {
		this(nome, diaNascimento, mesNascimento, anoNascimento, NAO_BRASILEIRO, SET_NAO_MORA_NO_BRASIL,
				Estados.XX, SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL);
	}// Construtor 2

	private void checaParametroValido(String parametro) throws Exception {
		if (parametro == null)
			throw new NullPointerException();
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

	@Override
	public String toString() {
		return "Hospede [nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", moraNoBrasil=" + moraNoBrasil + ", pais=" + pais
				+ ", estado=" + estado + ", cidade=" + cidade + ", endereco="
				+ endereco + ", numero=" + numero + ", CPF=" + CPF + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((CPF == null) ? 0 : CPF.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result
				+ ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result + (moraNoBrasil ? 1231 : 1237);
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hospede other = (Hospede) obj;
		if (CPF == null) {
			if (other.CPF != null)
				return false;
		} else if (!CPF.equals(other.CPF))
			return false;
		if (cidade == null) {
			if (other.cidade != null)
				return false;
		} else if (!cidade.equals(other.cidade))
			return false;
		if (dataNascimento == null) {
			if (other.dataNascimento != null)
				return false;
		} else if (!dataNascimento.equals(other.dataNascimento))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (estado != other.estado)
			return false;
		if (moraNoBrasil != other.moraNoBrasil)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (pais == null) {
			if (other.pais != null)
				return false;
		} else if (!pais.equals(other.pais))
			return false;
		return true;
	}

}// Hospede
