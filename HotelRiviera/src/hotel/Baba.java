package hotel;

import java.util.Calendar;

import excecoes.DataInvalidaException;
import excecoes.HoraInvalidaException;

public class Baba implements Servico {
	public static final double VALOR_HORA = 25;
	public static final double VALOR_HORA_DOBRADA = 50;
	public static final int[] HORAS_DOBRADAS = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	private NomesBaba baba;
	private Calendar inicioDoServico;
	private Calendar terminoDoServico;
	private boolean isReserva;

	public Baba(NomesBaba baba, boolean isReserva) {
		this.baba = baba;
		this.isReserva = isReserva;
		
		if(!isReserva)
			inicioDoServico = Calendar.getInstance();
		
	}// Constrkjhjkhkhutor
	
	public void setTerminoDoServico(int dia, int mes, int ano, int hora) throws Exception {
		verificaDataValida(dia, mes, ano);
		verificaHoraValida(hora);
		terminoDoServico.set(ano, mes, dia, hora, 0);
	}// setTerminoDoServico
	
	public void setInicioDoServico(int dia, int mes, int ano, int hora) throws Exception {
		verificaDataValida(dia, mes, ano);
		verificaHoraValida(hora);
		inicioDoServico.set(ano, mes, dia, hora, 0);
	}// setInicioDoServico

	public String getNome() {
		return baba.getNome();
	}// getNome

	@Override
	public double getPreco() {
		return calculaPreco();
	}// getPreco

	public Calendar getInicioDoServico() {
		return inicioDoServico;
	}// getInicioDoServico

	public Calendar getTerminoDoServico() {
		return terminoDoServico;
	}// getTerminoDoServico
	

	private double calculaPreco() {
		double preco = 0;
		int horaInicial = inicioDoServico.get(Calendar.HOUR_OF_DAY);
		int horaFinal = terminoDoServico.get(Calendar.HOUR_OF_DAY);

		while (horaInicial != horaFinal) {
			if (verificaSeEHoraDobrada(horaInicial))
				preco += VALOR_HORA_DOBRADA;
			else
				preco += VALOR_HORA;

			horaInicial++;
			if (horaInicial == 24)
				horaInicial = 0;
		}// while
		return preco;
	}// calculaPreco
	
	private boolean verificaSeEHoraDobrada(int hora) {
		for (int i = 0; i < HORAS_DOBRADAS.length; i++) {
			if (hora == HORAS_DOBRADAS[i])
				return true;

		}// for
		return false;
	}// verificaSeEHoraDobrada
	
	private void verificaHoraValida(int hora) throws HoraInvalidaException {
		if(hora < 0 || hora > 23)
			throw new HoraInvalidaException();
	}// verificaHoraValida

	private void verificaDataValida(int dia, int mes, int ano) throws DataInvalidaException {
		if(mes > 12)
			throw new DataInvalidaException();

		else if(dia > 31)
			throw new DataInvalidaException();

		else if(dia == 31) {
			if((mes % 2 == 0 && mes <= 6) || mes == 9 || mes == 11)
				throw new DataInvalidaException();

		} else if(mes == 2)
			verificaCasoFevereiro(dia, ano);
	}// verificaDataValida

	private void verificaCasoFevereiro(int dia, int ano) throws DataInvalidaException {
		if (ano % 100 == 0) {
			if (dia > 28)
				throw new DataInvalidaException();
		} else if (ano % 4 == 0 || ano % 400 == 0) {
			if (dia > 29)
				throw new DataInvalidaException();
		} else {
			if (dia > 28)
				throw new DataInvalidaException();
		}
	}// verificaCasoFevereiro

	@Override
	public String toString() {
		return "Baba [nome=" + baba + ", inicioDoServico=" + inicioDoServico
				+ ", terminoDoServico=" + terminoDoServico + ", isReserva="
				+ isReserva + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((inicioDoServico == null) ? 0 : inicioDoServico.hashCode());
		result = prime * result + (isReserva ? 1231 : 1237);
		result = prime * result + ((baba == null) ? 0 : baba.hashCode());
		result = prime
				* result
				+ ((terminoDoServico == null) ? 0 : terminoDoServico.hashCode());
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
		Baba other = (Baba) obj;
		if (inicioDoServico == null) {
			if (other.inicioDoServico != null)
				return false;
		} else if (!inicioDoServico.equals(other.inicioDoServico))
			return false;
		if (isReserva != other.isReserva)
			return false;
		if (baba != other.baba)
			return false;
		if (terminoDoServico == null) {
			if (other.terminoDoServico != null)
				return false;
		} else if (!terminoDoServico.equals(other.terminoDoServico))
			return false;
		return true;
	}

}// Baba
