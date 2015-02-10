package hotel;

import java.util.*;

public class Hotel {
		
		private double dispesasTotais;
		private static List<Contrato> contratosHotel = new ArrayList<Contrato>();
		
		public static void adicionaContrato(Contrato contrato){
			contratosHotel.add(contrato);
		}
		
		public static List<Contrato> getContratos() {
			return contratosHotel;
		}	
		
		public static List<Contrato> pesquisaContrato(String text) {
			List<Contrato> contratosEncontrados = new ArrayList<Contrato>();
			
			for (Contrato contrato : contratosHotel) {
				if(contrato.getHospedeTitular().getNome().equals(text))
					contratosEncontrados.add(contrato);
				for (String acompanhante : contrato.getAcompanhantes()) {
					if(acompanhante.equals(text))
						contratosEncontrados.add(contrato);
				}
			}
			return contratosEncontrados;
		}
		
		private double calculaCustoFinal(Contrato contrato) {
			return 0;
		}
}

		
	