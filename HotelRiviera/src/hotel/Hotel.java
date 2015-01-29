package hotel;

import java.util.*;

public class Hotel {
		
		private double dispesasTotais;
		private Calendar dataCheckIn;
		private Calendar dataCheckOut;
		private List<Contrato> contratosHotel;
		
		private Map<TipoDeQuarto, Integer> quartosDisponiveis = new HashMap<TipoDeQuarto, Integer>();
		
		{
			quartosDisponiveis.put(TiposQuartosExecutivo.EXECUTIVO_SIMPLES, 5);
			quartosDisponiveis.put(TiposQuartosExecutivo.EXECUTIVO_DUPLO, 15);
			quartosDisponiveis.put(TiposQuartosExecutivo.EXECUTIVO_TRIPLO, 20);
			quartosDisponiveis.put(TiposQuartosLuxo.LUXO_SIMPLES, 5);
			quartosDisponiveis.put(TiposQuartosLuxo.LUXO_DUPLO, 15);
			quartosDisponiveis.put(TiposQuartosLuxo.LUXO_TRIPLO, 20);
			quartosDisponiveis.put(TiposQuartosPresidencial.PRESIDENCIAL, 5);
		}
		
		private double a
}
		
	