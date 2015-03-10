package hotel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Conector implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1416930223875857500L;
	private static List<String> lista = new ArrayList<String>();

	public static TiposDeQuarto selecionaTipoQuarto(Object estilo) {
		if (estilo.equals("Simples"))
			return TiposDeQuarto.SIMPLES;
		if (estilo.equals("Duplo"))
			return TiposDeQuarto.DUPLO;
		if (estilo.equals("Triplo"))
			return TiposDeQuarto.TRIPLO;
		return null;
	}
	
	public static TipoFuncionario selecionaTipoFuncionario(Object estilo) {
		if (estilo.equals("Gerente"))
			return TipoFuncionario.GERENTE;
		if (estilo.equals("Balconista"))
			return TipoFuncionario.BALCONISTA;
		return null;
	}

	public static TipoCarro selecionaTipoCarro(Object estilo) {
		if (estilo.equals("Executivo"))
			return TipoCarro.EXECUTIVO;
		if (estilo.equals("Luxo"))
			return TipoCarro.LUXO;
		return null;
	}
	
	public static int trasformaNota(String object){
		int notaInt = Integer.parseInt(object);
		return notaInt;
	}

	public static Estado selecionaEstado(String estadoDado) {
		for (int i = 0; i < Estado.values().length; i++) {
			if (Estado.values()[i].name().equals(estadoDado))
				return Estado.values()[i];
		}
		return null;
	}

	public static Calendar transformaData(String data) {
		Calendar c = Calendar.getInstance();
		String[] diaMesAno = data.split("/");
		c.set(Integer.parseInt(diaMesAno[2]), Integer.parseInt(diaMesAno[1]) - 1,
				Integer.parseInt(diaMesAno[0]));
		return c;
	}
	
	public static String transformaData(Calendar data) {
		String novaData = (Integer.toString(Calendar.DAY_OF_MONTH) + "/" + Integer.toString(Calendar.MONTH + 1) + "/" + Integer.toString(Calendar.YEAR));
		return novaData;
	}

	public static Calendar transformaDataHora(String data, String hora) {
		Calendar c = Calendar.getInstance();
		String[] diaMesAno = data.split("/");
		c.set(Integer.parseInt(diaMesAno[2]), Integer.parseInt(diaMesAno[1]) - 1,
				Integer.parseInt(diaMesAno[0]));
		String[] horaMin = hora.split(":");
		c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(horaMin[0]));
		return c;
	}

	public static float transformaFloat(String num) {
		return Float.parseFloat(num);
	}

	public static List<String> transformaVetor(String[] args) {
		for (int i = 0; i < args.length; i++) {
			lista.add(args[i]);
		}
		return lista;
	}
}
