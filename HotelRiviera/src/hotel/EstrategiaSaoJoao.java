package hotel;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EstrategiaSaoJoao implements EstrategiaAplicavel{
	
	private final int DIA_INICIO = 23;
	private final int DIA_FIM = 29;
	private double porcentagemAAplicar = 1.5; // 50% a mais no valor das despesas totais do cliente.
	
	
	public double getPorcentagemAAplicar() {
		return porcentagemAAplicar;
	}

	@Override
	public String toString() {
		return "\nEstrategia Sao Joao \nDia inicial: " + DIA_INICIO + "\nDia final: "
				+ DIA_FIM + "Porcentagem: " + porcentagemAAplicar
				+ "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + DIA_FIM;
		result = prime * result + DIA_INICIO;
		long temp;
		temp = Double.doubleToLongBits(porcentagemAAplicar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EstrategiaSaoJoao other = (EstrategiaSaoJoao) obj;
		if (DIA_FIM != other.DIA_FIM)
			return false;
		if (DIA_INICIO != other.DIA_INICIO)
			return false;
		if (Double.doubleToLongBits(porcentagemAAplicar) != Double
				.doubleToLongBits(other.porcentagemAAplicar))
			return false;
		return true;
	}

	public void setPorcentagemAAplicar(double porcentagemAAplicar) {
		this.porcentagemAAplicar = porcentagemAAplicar;
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataInicio() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.JUNE, this.DIA_INICIO);
	}

	@SuppressWarnings("static-access")
	@Override
	public Calendar getDataFim() {
		return new GregorianCalendar(Calendar.getInstance().YEAR, Calendar.JUNE, this.DIA_FIM);
	}

	@Override
	public double aplicaPorcentagem(double despesasTotais) {
		return despesasTotais * porcentagemAAplicar;
	}

}
