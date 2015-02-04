package hotel;

import java.util.*;

import excecoes.DataInvalidaException;

public class Baba2 implements Servico {
	
	public static final double VALOR_HORA = 25;
	public static final double VALOR_HORA_DOBRADA = 50;
	public static final int[] HORAS_DOBRADAS = {18, 19, 20, 21, 22, 23, 0, 1,
		2, 3, 4, 5, 6, 7};
	
	
	private String nome;
	private Calendar inicioDoServico;
	private Calendar terminoDoServico;
		
	public Baba2(String nome, Calendar inicioDoServico, Calendar terminoDoServico) {
		if (inicioDoServico == null || terminoDoServico == null)
			throw new NullPointerException();
		this.nome = nome;
		this.inicioDoServico = inicioDoServico;
		this.terminoDoServico = terminoDoServico;		
	}// Construtor
	
	public void setTerminoDoServico(Calendar novaDataTermino) throws NullPointerException, DataInvalidaException {
		if (novaDataTermino == null)
			throw new NullPointerException();
		if(novaDataTermino.before(inicioDoServico))
			throw new DataInvalidaException();		
		terminoDoServico = novaDataTermino;
	}// setTerminoDoServico
	
	public void setInicioDoServico(Calendar novaDataInicio) throws NullPointerException {
		if (novaDataInicio == null)
			throw new NullPointerException();
		inicioDoServico = novaDataInicio;
	}// setInicioDoServico

	public String getNome() {
		return nome;
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
		for (int i = 0; i < HORAS_DOBRADAS.length; i++) {
			if (hora == HORAS_DOBRADAS[i])
				return true;

		}// for
		return false;
	}// verificaSeEHoraDobrada
	
	@Override
	public String toString() {
		return "Baba [nome=" + nome + ", inicioDoServico=" + inicioDoServico
				+ ", terminoDoServico=" + terminoDoServico + "]";
	}

	/*@Override
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
	}*/

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Baba2)) {
			return false;
		}
		Baba outro = (Baba) obj;
		return (this.nome.equals(outro.getNome()) && this.getInicioDoServico().equals(outro.getInicioDoServico())
				&& this.getInicioDoServico().equals(outro.getTerminoDoServico()));
	}

}// Baba
