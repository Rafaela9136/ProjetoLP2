package hotel;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import excecoes.ComentarioVazioException;
import excecoes.EstouroDeCaracteresException;
import excecoes.NotaInvalidaException;

/**
 * Classe de uma Opiniao que sera dada pelo cliente ao termino do contrato sobre
 * sua satisfacao com o Hotel.
 * 
 * @author Grupo
 * @version 1.0
 */
public class Opiniao implements Comparable<Opiniao>, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final float NOTA_MAX = 10;
	public static final float NOTA_MIN = 0;

	private String comentario;
	private float nota;
	private Calendar data;

	/**
	 * Cria uma opiniao com um comentario e uma nota.
	 * 
	 * @param comentario
	 *            Comentario da opiniao.
	 * @param nota
	 *            Nota da Opiniao.
	 * @throws NotaInvalidaException
	 *             Quando a nota e menor que a constante NOTA_MIN ou quando a
	 *             nota e maior que a constante NOTA_MAX.
	 * @throws EstouroDeCaracteresException
	 *             Quando o comentario excede a quantidade maxima de caracteres.
	 * @throws NullPointerException
	 *             Quando @param comentario nao foi inicializado.
	 * @throws ComentarioVazioException
	 *             Quando o comentario nao possui nenhum caractere.
	 */
	public Opiniao(String comentario, float nota) throws NotaInvalidaException,
			EstouroDeCaracteresException, NullPointerException,
			ComentarioVazioException {
		verificaNotaValida(nota);
		verificaComentarioValido(comentario);
		this.comentario = comentario;
		this.nota = nota;
		this.data = Calendar.getInstance();
	}// Construtor

	/**
	 * Recupera o comentario do objeto Opiniao.
	 * 
	 * @return O comentario do objeto Opiniao.
	 */
	public String getComentario() {
		return comentario;
	}// getComentario

	/**
	 * Recupera a data de criacao da opiniao.
	 * 
	 * @return Retorna a data.
	 */
	public Calendar getData() {
		return data;
	}// getData

	/**
	 * Recupera a nota do Objeto Opiniao.
	 * 
	 * @return A nota do objeto Opiniao.
	 */
	public float getNota() {
		return nota;
	}// getNota

	private void verificaNotaValida(float nota) throws NotaInvalidaException {
		if (nota > NOTA_MAX || nota < NOTA_MIN)
			throw new NotaInvalidaException();
	}// verificaNotaValida

	private void verificaComentarioValido(String comentario)
			throws NullPointerException, EstouroDeCaracteresException,
			ComentarioVazioException {
		if (comentario == null)
			throw new NullPointerException();
		if (comentario.trim().isEmpty())
			throw new ComentarioVazioException();
		if (comentario.length() > 400)
			throw new EstouroDeCaracteresException();

	}// verificaComentarioValido

	@Override
	public int compareTo(Opiniao outraOpiniao) {
		if (outraOpiniao == null) {
			throw new NullPointerException();
		} else if (getNota() > outraOpiniao.getNota()) {
			return 1;
		} else if (getNota() < outraOpiniao.getNota()) {
			return -1;
		}
		return 0;
	}

	/**
	 * Retorna uma representacao em string da opiniao dada, com seu comentario e
	 * nota.
	 * 
	 * @return String contendo os dados da opiniao.
	 */
	@Override
	public String toString() {
		final String FIM_LINHA = System.getProperty("line.separator");
		return "Opiniao: " + FIM_LINHA + "Comentario: '" + getComentario()
				+ "'" + FIM_LINHA + "Nota: " + (int) getNota() + FIM_LINHA
				+ "Data: "
				+ new SimpleDateFormat("dd/MM/yyyy").format(data.getTime());
	}

	/**
	 * Verifica se dois objetos do tipo Opiniao sao iguais, a partir de seus
	 * comentarios e suas notas.
	 * 
	 * @return True se forem iguas ou False caso contrario.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Opiniao)) {
			return false;
		}
		Opiniao outra = (Opiniao) obj;
		return getNota() == outra.getNota()
				&& getComentario().equals(outra.getComentario())
				&& getData().equals(outra.getData());
	}

}// Opiniao
