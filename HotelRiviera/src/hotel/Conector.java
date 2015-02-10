package hotel;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class Conector {

	private static Calendar c = Calendar.getInstance();
	private static List<String> lista = new ArrayList<String>();
	
	public static TiposDeQuarto selecionaTipoQuarto(Object estilo){
			if(estilo.equals("Simples"))
				return TiposDeQuarto.SIMPLES;
			if(estilo.equals("Duplo"))
				return TiposDeQuarto.DUPLO;
			if(estilo.equals("Triplo"))
				return TiposDeQuarto.TRIPLO;
			return null;
	}
	
	public static Estado selecionaEstado(String estadoDado) {
		for (int i = 0; i < Estado.values().length; i++) {
			if(Estado.values()[i].name().equals(estadoDado))
				return Estado.values()[i];
		}
		return null;
	}
	
	public static Calendar transformaData(String data){
		String[] diaMesAno = data.split("/");
		c.set(Integer.parseInt(diaMesAno[2]), Integer.parseInt(diaMesAno[1]), Integer.parseInt(diaMesAno[0]));
		return c;
	}
	
	public static float transformaFloat(String num){
		return Float.parseFloat(num);
	}
	
	public static List<String> transformaVetor(String[] args){
		for (int i = 0; i < args.length; i++) {
			lista.add(args[i]);
		}
		return lista;
	}
}
