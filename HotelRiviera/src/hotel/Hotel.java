package hotel;

import java.util.*;

public class Hotel {
		
		private int numExecSimplesDesocupados = 5;
		private int numExecDuploDesocupados = 15;
		private int numExecTriploDesocupados = 20;
		
		private int numLuxoSimplesDesocupados = 5;
		private int numLuxoDuploDesocupados = 15;
		private int numLuxoTriploDesocupados = 20;
		
		private int numPresidencialDesocupados = 5;
		
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
		
		public static void removeContrato(Contrato contrato) {
			int cont = 0;
			for (int i = 0; i < contratosHotel.size(); i++) {
				if(contratosHotel.get(i).equals(contrato))
					cont = i;
			}
			contratosHotel.remove(cont);
		}
}

		
	