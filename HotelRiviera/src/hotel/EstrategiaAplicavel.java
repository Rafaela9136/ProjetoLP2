package hotel;

import java.util.*;

public interface EstrategiaAplicavel {
				
	public Calendar getDataInicio(); 
	
	public Calendar getDataFim();
	
	public double aplicaPorcentagem(double despesasTotais);
	
}
