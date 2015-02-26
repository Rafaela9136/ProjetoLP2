package hotel;

import java.io.Serializable;
import java.util.Comparator;

public class OpiniaoComparator implements Comparator<Opiniao>, Serializable {

	@Override
	public int compare(Opiniao opiniao, Opiniao outraOpiniao) {
		return opiniao.compareTo(outraOpiniao);
	}// compare
	

}// OpiniaoComparator
