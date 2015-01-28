package hotel;

import excecoes.EstouroDeCaracteresException;
import excecoes.NotaInvalidaException;

public class Opiniao {
	public static final float NOTA_MAX = 10;
	public static final float NOTA_MIN = 0;

	private String comentario;
	private float nota;

	public Opiniao(String comentario, float nota) throws NotaInvalidaException, EstouroDeCaracteresException, NullPointerException  {
		verificaNotaValida(nota);
		verificaComentarioValido(comentario);
		this.comentario = comentario;
		this.nota = nota;
	}// Construtor
	
	private void verificaNotaValida(float nota) throws NotaInvalidaException {
		if (nota > NOTA_MAX || nota < NOTA_MIN)
			throw new NotaInvalidaException();
	}// verificaNotaValida

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

	private void verificaComentarioValido(String comentario) throws NullPointerException, EstouroDeCaracteresException {
		if (comentario == null)
			throw new NullPointerException();

		if (comentario.length() > 400)
			throw new EstouroDeCaracteresException();

	}// verificaComentarioValido

	public String getComentario() {
		return comentario;
	}// getComentario

	public float getNota() {
		return nota;
	}// getNota

}// Opiniao
