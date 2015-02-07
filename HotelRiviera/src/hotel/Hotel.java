package hotel;

import java.util.*;

public class Hotel {
		
		private double dispesasTotais;
		private static List<Contrato> contratosHotel = new ArrayList<Contrato>();
		
		public static boolean adicionaContrato(Contrato contrato){
			if(contratosHotel.add(contrato))
				return true;
			return false;
		}
		
		public static List<Contrato> getContratos() {
			return contratosHotel;
		}	
		
		private double calculaCustoFinal(Contrato contrato) {
			return 0;
		}
}

		
	