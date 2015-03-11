package hotel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import excecoes.CPFInvalidoException;
import excecoes.CartaoInvalidoException;
import excecoes.DataInvalidaException;
import excecoes.NumeroInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.StringVaziaException;

/**
 * Classe que representa um Hospede do Hotel. Ao ser criado, caso seja
 * brasileiro, eh necessario que seja informado seu nome, data de nascimento,
 * pais, estado, cidade, endereco com numero, cpf e um cartao para o pagamento.
 * Caso nao seja brasileiro, eh informado apenas o nome, data de nascimento e o
 * cartao.
 * 
 * @author Grupo
 * @version 1.0
 *
 */
public class Hospede implements Serializable {

	private static final String SET_NAO_MORA_NO_BRASIL = "--";
	public static final boolean NAO_BRASILEIRO = false;

	private String nome, pais, cidade, logradouro, numero, cpf,
			cartaoDeCredito;
	private Calendar dataNascimento;
	private Estado estado;

	/**
	 * Cria um objeto do tipo Hospede, com nome, data de nascimento, pais,
	 * estado, cidade, logradouro, numero, cpf e cartao de credito.
	 * 
	 * @param nome
	 *            O nome do Hospede.
	 * @param dataNascimento
	 *            A data de nascimento.
	 * @param pais
	 *            O pais onde mora.
	 * @param estado
	 *            O estado onde mora.
	 * @param cidade
	 *            A cidade onde mora.
	 * @param logradouro
	 *            Logradouro onde reside, podendo ser rua, avenida, beco, etc.
	 * @param numero
	 *            Numero de sua residencia.
	 * @param cpf
	 *            O numero de seu cpf.
	 * @param cartaoDeCredito
	 *            O cartao de credito para que seja feito o pagamento.
	 * @throws NullPointerException
	 *             Caso o objeto tenha valor nulo.
	 * @throws CPFInvalidoException
	 *             Caso o cpf nao siga o padrao ###.###.###-## ou contenha
	 *             letras e caracteres que nao sejam numeros.
	 * @throws DataInvalidaException
	 *             Caso a data seja depois da data atual.
	 * @throws StringVaziaException
	 *             Caso a string seja vazia.
	 * @throws CartaoInvalidoException
	 *             Se o cartao nao seguir o padrao ####.####.####.#### ou conter
	 *             letras e caracteres que nao sejam numeros.
	 * @throws StringInvalidaException
	 *             Se a string possuir numeros ou caracteres que nao sejam
	 *             letras.
	 * @throws NumeroInvalidoException
	 *             Se a string possuir letras ou caracteres que nao sejam
	 *             numeros.
	 */
	public Hospede(String nome, Calendar dataNascimento, String pais,
			Estado estado, String cidade, String logradouro, String numero,
			String cpf, String cartaoDeCredito) throws NullPointerException,
			CPFInvalidoException, DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		verificaStringApenasLetras(nome);
		verificaData(dataNascimento);
		verificaCartao(cartaoDeCredito);
		if (pais.toLowerCase().equals("brasil")) {
			verificaCpf(cpf);
			verificaParametroNulo(estado);
			verificaStringApenasLetras(cidade);
			verificaStringApenasLetras(logradouro);
			verificaNumero(numero);
		}
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

	/**
	 * Cria um objeto do tipo Hospede informando apenas o nome, data de
	 * nascimento e cartao de credito.
	 * 
	 * @param nome
	 *            O nome do Hospede.
	 * @param dataNascimento
	 *            A sua data de nascimento.
	 * @param cartaoDeCredito
	 *            O numero de cartao de credito.
	 * @throws NullPointerException
	 *             Se o objeto tiver valor nulo.
	 * @throws CPFInvalidoException
	 *             Se o cpf nao seguir o padrao ###.###.###-##
	 * @throws DataInvalidaException
	 *             Se a data ainda nao tiver ocorrido.
	 * @throws StringVaziaException
	 *             Se a string for vazia.
	 * @throws CartaoInvalidoException
	 *             Se o cartao nao seguir o padrao ####.####.####.####
	 * @throws StringInvalidaException
	 *             Se a string tiver caracteres que nao sejam letras.
	 * @throws NumeroInvalidoException
	 *             Se a string tiver caracteres que nao sejam numeros.
	 */
	public Hospede(String nome, Calendar dataNascimento, String cartaoDeCredito)
			throws NullPointerException, CPFInvalidoException,
			DataInvalidaException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		this(nome, dataNascimento, SET_NAO_MORA_NO_BRASIL, Estado.XX,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL,
				SET_NAO_MORA_NO_BRASIL, SET_NAO_MORA_NO_BRASIL, cartaoDeCredito);
	}// Construtor 2

	/**
	 * Retorna o nome do Hospede.
	 * 
	 * @return O nome do Hospede.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Altera o nome do Hospede caso haja algum erro.
	 * 
	 * @param nome
	 *            O novo nome do Hospede.
	 * @throws NullPointerException
	 *             Se o novo nome tiver valor nulo.
	 * @throws StringVaziaException
	 *             Se o nome for uma string vazia.
	 * @throws StringInvalidaException
	 *             Se o nome possuir numeros ou outros caracteres que nao sejam
	 *             letras.
	 */
	public void setNome(String nome) throws NullPointerException,
			StringVaziaException, StringInvalidaException {
		verificaStringApenasLetras(nome);
		this.nome = nome;
	}

	/**
	 * Retorna a data de nascimento do Hospede.
	 * 
	 * @return A data de nascimento.
	 */
	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	/**
	 * Altera a data de nascimento caso haja algum erro.
	 * 
	 * @param dataNascimento
	 *            A nova data de nascimento.
	 * @throws NullPointerException
	 *             Caso a data tenha valor nulo.
	 * @throws DataInvalidaException
	 *             Caso a data ainda nao tiver ocorrido.
	 */
	public void setDataNascimento(Calendar dataNascimento)
			throws NullPointerException, DataInvalidaException {
		verificaData(dataNascimento);
		this.dataNascimento = dataNascimento;
	}

	/**
	 * Retorna o Pais onde o Hospede reside.
	 * 
	 * @return O pais do Hospede.
	 */
	public String getPais() {
		return pais;
	}

	/**
	 * Altera o Pais onde o Hospede reside, caso haja algum erro.
	 * 
	 * @param pais
	 *            O novo Pais.
	 * @throws NullPointerException
	 *             Se o objeto tiver valor nulo.
	 * @throws StringVaziaException
	 *             Se a string passada for vazia.
	 * @throws StringInvalidaException
	 *             Se a string passada possuir caracteres que nao sejam letras.
	 */
	public void setPais(String pais) throws NullPointerException,
			StringVaziaException, StringInvalidaException {
		verificaStringApenasLetras(pais);
		this.pais = pais;
	}

	/**
	 * Retorna o estado onde o Hospede reside.
	 * 
	 * @return O estado do Hospede.
	 */
	public String getEstado() {
		return estado.name();
	}

	/**
	 * Altera o estado do Hospede.
	 * 
	 * @param estado
	 *            O novo estado.
	 * @throws NullPointerException
	 *             Se o objeto passado for nulo.
	 */
	public void setEstado(Estado estado) throws NullPointerException {
		verificaParametroNulo(estado);
		this.estado = estado;
	}

	/**
	 * Retorna a cidade onde o Hospede mora.
	 * 
	 * @return A cidade do Hospede.
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * Altera a cidade do Hospede.
	 * 
	 * @param novaCidade
	 *            A nova cidade.
	 * @throws NullPointerException
	 *             Se a nova cidade tiver valor nulo.
	 * @throws StringVaziaException
	 *             Se a nova cidade for vazia.
	 * @throws StringInvalidaException
	 *             Se a string da nova cidade possuir caracteres que nao sao
	 *             letras.
	 */
	public void setCidade(String novaCidade) throws NullPointerException,
			StringVaziaException, StringInvalidaException {
		verificaStringApenasLetras(novaCidade);
		this.cidade = novaCidade;
	}

	/**
	 * Retorna o local de residencia do Hospede.
	 * 
	 * @return O logradouro do Hospede.
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Altera o logradouro do Hospede.
	 * 
	 * @param novoLogradouro
	 *            O novo logradouro.
	 * @throws NullPointerException
	 *             Se a string tiver valor nulo.
	 * @throws StringVaziaException
	 *             Se a string for vazia.
	 * @throws StringInvalidaException
	 *             Se a string tiver caracteres que nao sejam letras.
	 */
	public void setLogradouro(String novoLogradouro)
			throws NullPointerException, StringVaziaException,
			StringInvalidaException {
		verificaStringApenasLetras(novoLogradouro);
		this.logradouro = novoLogradouro;
	}

	/**
	 * Retorna o numero do endereco do Hospede.
	 * 
	 * @return O numero da casa ou do apartamento.
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * Altera o numero do endereco.
	 * 
	 * @param novoNumero
	 *            O novo numero.
	 * @throws NullPointerException
	 *             Se o numero tiver valor nulo.
	 * @throws StringVaziaException
	 *             Se o numero for uma string vazia.
	 * @throws NumeroInvalidoException
	 *             Se o novo numero possuir caracteres que nao sejam numeros.
	 */
	public void setNumero(String novoNumero) throws NullPointerException,
			StringVaziaException, NumeroInvalidoException {
		verificaNumero(novoNumero);
		this.numero = novoNumero;
	}

	/**
	 * Retorna o CPF do Hospede.
	 * 
	 * @return O CPF do Hospede.
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * Altera o CPF do Hospede, caso haja algum erro.
	 * 
	 * @param cpf
	 *            O novo CPF.
	 * @throws NullPointerException
	 *             Se o novo cpf tiver valor nulo.
	 * @throws CPFInvalidoException
	 *             Se o novo cpf nao seguir o padrao ###.###.###-## ou possuir
	 *             caracteres que nao sejam numeros.
	 * @throws StringVaziaException
	 *             Se o novo cpf for uma string vazia.
	 */
	public void setCpf(String cpf) throws NullPointerException,
			CPFInvalidoException, StringVaziaException {
		verificaCpf(cpf);
		this.cpf = cpf;
	}

	/**
	 * Retorna o cartao de credito do Hospede.
	 * 
	 * @return O numero do cartao do Hospede.
	 */
	public String getCartaoDeCredito() {
		return cartaoDeCredito;
	}

	/**
	 * Altera o numero do cartao do Hospede.
	 * 
	 * @param novoCartao
	 *            O novo numero de cartao.
	 * @throws NullPointerException
	 *             Se o novo numero tiver valor nulo.
	 * @throws CartaoInvalidoException
	 *             Se o novo numero nao seguir o padrao ####.####.####.#### ou
	 *             possuir caracteres que nao sejam numeros.
	 * @throws StringVaziaException
	 *             Se o novo numero for uma string vazia.
	 */
	public void setCartaoDeCredito(String novoCartao)
			throws NullPointerException, CartaoInvalidoException,
			StringVaziaException {
		verificaCartao(novoCartao);
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

	private void verificaStringVazia(String str) throws StringVaziaException {
		if (str.trim().isEmpty()) {
			throw new StringVaziaException();
		}
	}

	private void verificaStringApenasLetras(String string)
			throws NullPointerException, StringVaziaException,
			StringInvalidaException {
		verificaParametroNulo(string);
		verificaStringVazia(string);
		if (!string.matches("[a-zA-Z\\s]+"))
			throw new StringInvalidaException();
	}

	private void verificaCpf(String cpf) throws NullPointerException,
			CPFInvalidoException, StringVaziaException {
		verificaParametroNulo(cpf);
		verificaStringVazia(cpf);
		if (!cpf.matches("\\d{3}.\\d{3}.\\d{3}-\\d{2}"))
			throw new CPFInvalidoException();
	}

	private void verificaCartao(String cartao) throws NullPointerException,
			CartaoInvalidoException, StringVaziaException {
		verificaParametroNulo(cartao);
		verificaStringVazia(cartao);
		if (!cartao.matches("\\d{4}.\\d{4}.\\d{4}.\\d{4}"))
			throw new CartaoInvalidoException();
	}

	private void verificaNumero(String numero) throws NullPointerException,
			StringVaziaException {
		verificaParametroNulo(numero);
		verificaStringVazia(numero);
	}

	/**
	 * Retorna uma representacao em String de todos os dados do Hospede.
	 * 
	 * @return Uma String contendo todos os dados do Hospede.
	 */
	@Override
	public String toString() {
		return "HOSPEDE\n"
				+ "Nome: "
				+ getNome()
				+ "\nData de Nascimento: "
				+ new SimpleDateFormat("dd/MM/yyyy").format(dataNascimento
						.getTime()) + "\nPais: " + getPais() + "\nEstado: "
				+ getEstado() + "\nCidade: " + getCidade() + "\nEndereco: "
				+ getLogradouro() + ", " + getNumero() + "\nCPF: " + getCpf()
				+ "\nCartao de Credito: " + getCartaoDeCredito();
	}

	/**
	 * 
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cartaoDeCredito == null) ? 0 : cartaoDeCredito.hashCode());
		result = prime * result + ((cidade == null) ? 0 : cidade.hashCode());
		result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
		result = prime * result
				+ ((dataNascimento == null) ? 0 : dataNascimento.hashCode());
		result = prime * result + ((estado == null) ? 0 : estado.hashCode());
		result = prime * result
				+ ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
		result = prime * result + ((pais == null) ? 0 : pais.hashCode());
		return result;
	}

	/**
	 * Verifica se dois objetos do tipo Hospede sao iguais a partir de seus
	 * nomes, datas de nascimento, paises, estados, cidades, logradouros,
	 * numeros, cpfs e cartoes.
	 * 
	 * @return True se forem iguais ou False caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Hospede))
			return false;
		Hospede outro = (Hospede) obj;
		return getNome().equals(outro.getNome())
				&& getDataNascimento().equals(outro.getDataNascimento())
				&& getPais().equals(outro.getPais())
				&& getEstado().equals(outro.getEstado())
				&& getCidade().equals(outro.getCidade())
				&& getLogradouro().equals(outro.getLogradouro())
				&& getNumero().equals(outro.getNumero())
				&& getCpf().equals(outro.getCpf())
				&& getCartaoDeCredito().equals(outro.getCartaoDeCredito());
	}
}
