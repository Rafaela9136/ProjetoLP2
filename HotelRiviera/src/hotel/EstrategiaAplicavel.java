package hotel;

import java.util.*;

public interface EstrategiaAplicavel {
	
	public double getPorcentagemAAplicar();
				
	public Calendar getDataInicio(); 
	
	public Calendar getDataFim();
	
	@Override
	public boolean equals(Object obj);
	
	@Override
	public int hashCode();
	
	@Override
	public String toString();
}
