package hotel;

import excecoes.ComentarioVazioException;
import excecoes.EstouroDeCaracteresException;
import excecoes.NotaInvalidaException;

/**
 * @version 2.0
 * @author Grupinho da Alegria
 *
 */
public class Opiniao {
	public static final float NOTA_MAX = 10;
	public static final float NOTA_MIN = 0;

	private String comentario;
	private float nota;

	/**
	 * 
	 * @param comentario
	 *            Comentario a ser adicionado na opiniao.
	 * @param nota
	 *            Nota da Opiniao.
	 * @throws NotaInvalidaException
	 *             Excecao lancada quando a nota e menor que a constante
	 *             NOTA_MIN ou quando a nota e maior que a constante NOTA_MAX.
	 * @throws EstouroDeCaracteresException
	 *             Excecao lancada quando o comentario excede a quantidade
	 *             maxima de caracteres.
	 * @throws NullPointerException
	 *             Excecao lancada quando @param comentario nao foi
	 *             inicializado.
	 * @throws ComentarioVazioException Excecao lancada quando o comentario nao possui nenhum caractere.
	 */
	public Opiniao(String comentario, float nota) throws NotaInvalidaException,
			EstouroDeCaracteresException, NullPointerException, ComentarioVazioException {
		verificaNotaValida(nota);
		verificaComentarioValido(comentario);
		this.comentario = comentario;
		this.nota = nota;
	}// Construtor
	
	/**
	 * @see nothing Recupera o comentario do objeto Opiniao.
	 * @return Retorna o comentario do objeto Opiniao.
	 */
	public String getComentario() {
		return comentario;
	}// getComentario
	
	/**
	 * @see nothing Recupera a nota do Objeto Opiniao.
	 * @return Retorna a nota do objeto Opiniao.
	 */
	public float getNota() {
		return nota;
	}// getNota

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((comentario == null) ? 0 : comentario.hashCode());
		result = prime * result + Float.floatToIntBits(nota);
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
		Opiniao other = (Opiniao) obj;
		if (comentario == null) {
			if (other.comentario != null)
				return false;
		} else if (!comentario.equals(other.comentario))
			return false;
		if (Float.floatToIntBits(nota) != Float.floatToIntBits(other.nota))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Opiniao [comentario=" + comentario + ", nota=" + nota + "]";
	}
	
	private void verificaNotaValida(float nota) throws NotaInvalidaException {
		if (nota > NOTA_MAX || nota < NOTA_MIN)
			throw new NotaInvalidaException();
	}// verificaNotaValida

	private void verificaComentarioValido(String comentario)
			throws NullPointerException, EstouroDeCaracteresException,
			ComentarioVazioException {
		if (comentario == null)
			throw new NullPointerException();

		if (comentario.length() > 400)
			throw new EstouroDeCaracteresException();

		if (comentario.isEmpty())
			throw new ComentarioVazioException();

	}// verificaComentarioValido

}// Opiniao
