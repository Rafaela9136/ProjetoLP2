package testes;

import static org.junit.Assert.*;
import hotel.Carro;
import hotel.Contrato;
import hotel.Estrategias;
import hotel.Hospede;
import hotel.TipoCarro;
import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.Servico;
import hotel.TipoDeQuarto;
import hotel.TiposQuartosExecutivo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ContratoTest {

	private Contrato contrato1, contrato2;
	private Calendar c = Calendar.getInstance();
	private Hospede hospedeTitular;
	private Quarto quarto;
	private List<String> hospedes = new ArrayList();
	private List<Quarto> quartos = new ArrayList();
	private List<Servico> servicos = new ArrayList();

	//
	// @Before
	// public void criaObjetos() throws Exception{
	// hospedeTitular = new Hospede("nome", 10, 9, 1996);
	// hospedes.add("Maria Cocielo");
	// quarto = new QuartoExecutivo(false,
	// TiposQuartosExecutivo.EXECUTIVO_SIMPLES);
	// quartos.add(quarto);
	// contrato1 = new Contrato(hospedeTitular, hospedes,
	// Estrategias.NATAL_REVEILION, 10, 6, 2015, 20, 6, 2015, false, quartos,
	// servicos);
	// contrato2 = new Contrato(hospedeTitular, hospedes, Estrategias.SAO_JOAO,
	// 15, 3, 2015, 23, 3, 2015, true, quartos, servicos);
	// }
	//
	@Test
	public void testEditarServicos() {
		Servico carro = new Carro(TipoCarro.EXECUTIVO, new GregorianCalendar(
				2014, 1, 5), true, true);
		for (int i = 0; i < 5; i++) {
			contrato1.adicionaServico(carro);
		}
		Assert.assertEquals(servicos.size(), 5);
		for (int i = 0; i < 5; i++) {
			contrato1.removeServico(carro);
		}
		Assert.assertEquals(servicos.size(), 0);
	}

}