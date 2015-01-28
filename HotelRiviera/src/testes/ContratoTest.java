package testes;

import static org.junit.Assert.*;
import hotel.Carro;
import hotel.Contrato;
import hotel.Estrategias;
import hotel.Hospede;
import hotel.PrecoCarro;
import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.Servico;
import hotel.TipoDeQuarto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContratoTest {
	
	private Contrato contrato1, contrato2;
	private Calendar c = Calendar.getInstance();
	private Hospede hospedeTitular;
	private Quarto quarto;
	private List<Hospede> hospedes = new ArrayList();
	private List<Quarto> quartos = new ArrayList();
	private List<Servico> servicos = new ArrayList();

	@Before
	public void criaObjetos() throws Exception{
		hospedeTitular = new  Hospede("nome", c.getInstance());
		hospedes.add(hospedeTitular);
		quarto = new QuartoExecutivo(false, TipoDeQuarto.EXECUTIVO_SIMPLES);
		quartos.add(quarto);
		contrato1 = new Contrato(hospedeTitular, hospedes, Estrategias.NATAL_REVEILION, 10, 6, 2015, 20, 6, 2015, false, quartos, servicos);
		contrato2 = new Contrato(hospedeTitular, hospedes, Estrategias.SAO_JOAO, 15, 3, 2015, 23, 3, 2015, true, quartos, servicos);
	}
	
	@Test
	public void testEditaServicos() {
		Servico carro = new Carro(PrecoCarro.EXECUTIVO, true, true);
		servicos.add(carro);
		Assert.assertEquals(servicos.get(-1), carro);		
	}

}