package hotel;

import java.util.*;

public interface EstrategiaAplicavel {
				
	public Calendar getDataInicio(); 
	
	public Calendar getDataFim();
	
	public double aplicaPorcentagem(double despesasTotais);
	
	@Override
	public boolean equals(Object obj);
	
	@Override
	public int hashCode();
	
	@Override
	public String toString();
}
