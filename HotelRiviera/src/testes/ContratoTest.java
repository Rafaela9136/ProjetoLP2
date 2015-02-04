package testes;

import static org.junit.Assert.*;
import hotel.Carro;
import hotel.Contrato;
import hotel.EstrategiaAplicavel;
import hotel.EstrategiaNatalReveillon;
import hotel.Hospede;
import hotel.TipoCarro;
import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.Servico;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContratoTest {
	private Contrato contrato1;
	private Contrato contrato2;
	
	private Hospede hospedeTitular;
	private Calendar dataNascimento;
	private List<String> acompanhantes = new ArrayList<String>();
	private EstrategiaAplicavel estrategia;
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private boolean isReserva;
	
	@Before
	public void criaObjetos() {
		dataNascimento = Calendar.getInstance();
		hospedeTitular = new Hospede("Ricardo vidaloka", dataNascimento);
		estrategia = new EstrategiaNatalReveillon();
		dataCheckIn = Calendar.getInstance();
		dataCheckOut = new GregorianCalendar(2015, 4, 15);
		
		acompanhantes.add("Jusefa mulher do vidaloka");
		acompanhantes.add("Filho do vidaloka");
		isReserva = true;
		
		contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia, dataCheckIn, dataCheckOut, isReserva, quartos, servicos)
			
	}// criaObjetos
}// ContratoTest