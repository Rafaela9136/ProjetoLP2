package hotel;

import java.io.Serializable;
import java.util.Comparator;
/**
 * Comparador utilizado para ordenar a lista de opinioes na classe Hotel.
 * @author Grupinho da alegria.
 *
 */
public class OpiniaoComparator implements Comparator<Opiniao>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(Opiniao opiniao, Opiniao outraOpiniao) {
		return opiniao.compareTo(outraOpiniao);
	}// compare
	

}// OpiniaoComparator
