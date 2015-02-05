package hotel;

import java.util.*;

import excecoes.DataInvalidaException;
import excecoes.NomeVazioException;

public class Baba implements Servico {
	
	public static final double VALOR_HORA = 25;
	public static final double VALOR_HORA_DOBRADA = 50;
	public static final int INICIO_HORA_DOBRADA = 18; // entre 18h e 7h o preco por hora da baba e dobrado.
	public static final int FIM_HORA_DOBRADA = 7; 
	
	private Calendar inicioDoServico;
	private Calendar terminoDoServico;
		
	public Baba(Calendar inicioDoServico, Calendar terminoDoServico) throws NullPointerException {
		verificaData(inicioDoServico);
		verificaData(terminoDoServico);
		this.inicioDoServico = inicioDoServico;
		this.terminoDoServico = terminoDoServico;		
	}// Construtor

	private void verificaData(Calendar data) {
		if (data == null)
			throw new NullPointerException();
	}
	
	public void setTerminoDoServico(Calendar novaDataTermino) throws NullPointerException  {
		verificaData(novaDataTermino);	
		terminoDoServico = novaDataTermino;
	}// setTerminoDoServico
	
	public void setInicioDoServico(Calendar novaDataInicio) throws NullPointerException {
		verificaData(novaDataInicio);
		inicioDoServico = novaDataInicio;
	}// setInicioDoServico

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
		double preco = 0.0;
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
		if (hora >= INICIO_HORA_DOBRADA || hora <= FIM_HORA_DOBRADA) { 
			return true;
		} else {
			return false;
		}
	}// verificaSeEHoraDobrada
	
	@Override
	public String toString() {
		return "SERVIÇO BABYSITTER" +
				"Início do serviço: " + inicioDoServico +
				"Término do serviço: " + terminoDoServico +
				"Duração do serviço: " + 
				"Valor total do serviço: " + getPreco() +
				"OBS: Das 18 h às 7 horas o valor do serviço é cobrado em dobro.";	
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Baba)) {
			return false;
		}
		Baba outro = (Baba) obj;
		return (getInicioDoServico().equals(outro.getInicioDoServico())
				&& getInicioDoServico().equals(outro.getTerminoDoServico()));
	}

}// Baba
