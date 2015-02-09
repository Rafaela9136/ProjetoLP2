package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

import excecoes.CPFInvalidoException;
import excecoes.CartaoInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.StringVaziaException;

public class Hospede {

	private static final String SET_NAO_MORA_NO_BRASIL = "--";
	public static final boolean NAO_BRASILEIRO = false;

	private String nome, pais, cidade, logradouro, numero, cpf,
			cartaoDeCredito;
	private Calendar dataNascimento;
	private Estado estado;

	public Hospede(String nome, Calendar dataNascimento, String pais,
			Estado estado, String cidade, String logradouro, String numero,
			String cpf, String cartaoDeCredito) throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException {
		verificaStringVazia(nome);
		verificaData(dataNascimento);
		verificaStringVazia(pais);
		verificaParametroNulo(estado);
		verificaStringVazia(cidade);
		verificaStringVazia(logradouro);
		verificaStringVazia(numero);
		if (pais.toLowerCase().equals("brasil"))
			verificaCPF(cpf);
		verificaCartao(cartaoDeCredito);
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.logradouro = logradouro;
		this.numero = numero;
		this.cpf = cpf;
		this.cartaoDeCredito = cartaoDeCredito;
	}// Construtor 1

	public Hospede(String nome, Calendar dataNascimento, String cartaoDeCredito)
			throws NullPointerException, CPFInvalidoException,
			DataInvalidaException, StringVaziaException,
			CartaoInvalidoException {
		this(nome, dataNascimento, SET_NAO_MORA_NO_BRASIL, Estado.XX,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL, cartaoDeCredito);
	}// Construtor 2

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) throws NullPointerException,
			StringVaziaException {
		verificaStringVazia(nome);
		this.nome = nome;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	public String getPais() {
		return pais;
	}

	public String getEstado() {
		return estado.name();
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String novaCidade) throws NullPointerException,
			StringVaziaException {
		verificaStringVazia(novaCidade);
		this.cidade = novaCidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String novoLogradouro) {
		verificaParametroNulo(novoLogradouro);
		this.logradouro = novoLogradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String novoNumero) {
		verificaParametroNulo(novoNumero);
		this.numero = novoNumero;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) throws NullPointerException,
			CPFInvalidoException, StringVaziaException {
		verificaCPF(cpf);
		this.cpf = cpf;
	}

	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	public void setCartaoDeCredito(String novoCartao) {
		verificaParametroNulo(novoCartao);
		this.cartaoDeCredito = novoCartao;
	}

	private void verificaData(Calendar dataNascimento)
			throws DataInvalidaException {
		verificaParametroNulo(dataNascimento);
		if (dataNascimento.after(new GregorianCalendar()))
			throw new DataInvalidaException();
	}

	private void verificaParametroNulo(Object parametro) {
		if (parametro == null)
			throw new NullPointerException();
	}

	private void verificaCPF(String cpf) throws NullPointerException,
			CPFInvalidoException, StringVaziaException {
		verificaStringVazia(cpf);
		if (cpf.trim().length() != 11)
			throw new CPFInvalidoException();
	}

	private void verificaStringVazia(String string)
			throws NullPointerException, StringVaziaException {
		verificaParametroNulo(string);
		if (string.trim().isEmpty())
			throw new StringVaziaException();
	}

	private void verificaCartao(String numCartao) throws NullPointerException,
			CartaoInvalidoException, StringVaziaException {
		verificaStringVazia(numCartao);
		if (numCartao.trim().length() != 16)
			throw new CartaoInvalidoException();
	}

	@Override
	public String toString() {
		return "Hospede [nome=" + nome + ", dataNascimento=" + dataNascimento
				+ ", pais=" + pais + ", estado=" + estado + ", cidade="
				+ cidade + ", endereco=" + logradouro + ", numero=" + numero
				+ ", CPF=" + cpf + "]";
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

}
