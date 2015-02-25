package hotel;

import java.util.Comparator;

public class OpiniaoComparator implements Comparator<Opiniao> {

	@Override
	public int compare(Opiniao opiniao, Opiniao outraOpiniao) {
		return opiniao.compareTo(outraOpiniao);
	}// compare
	

}// OpiniaoComparator
