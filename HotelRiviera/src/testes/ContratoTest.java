package testes;

import static org.junit.Assert.*;
import hotel.Baba;
import hotel.Carro;
import hotel.Contrato;
import hotel.EstrategiaAplicavel;
import hotel.EstrategiaNatalReveillon;
import hotel.Frigobar;
import hotel.Hospede;
import hotel.QuartoLuxo;
import hotel.SuitePresidencial;
import hotel.TipoCarro;
import hotel.Quarto;
import hotel.QuartoExecutivo;
import hotel.Servico;
import hotel.TiposDeQuarto;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.FrigobarEmListServicosException;
import excecoes.NomeVazioException;

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
	private List<Servico> servicos;
	private Baba baba;

	private Carro carro;
	private boolean isTanqueCheio;
	private boolean isAssegurado;

	private Quarto quarto1;
	private Quarto quarto2;
	private Quarto quarto3;
	private Quarto quarto4;
	private Quarto quarto5;

	@Before
	public void criaObjetos() throws NullPointerException,
			CPFInvalidoException, ContratoSemQuartoException,
			FrigobarEmListServicosException, DataInvalidaException,
			NomeVazioException {
		dataNascimento = Calendar.getInstance();
		hospedeTitular = new Hospede("Ricardo vidaloka", dataNascimento);
		estrategia = new EstrategiaNatalReveillon();
		dataCheckIn = new GregorianCalendar(2015, 4, 15);
		dataCheckOut = new GregorianCalendar(2015, 5, 15);
		baba = new Baba(dataCheckIn, dataCheckOut);

		this.isTanqueCheio = true;
		this.isAssegurado = true;
		carro = new Carro(TipoCarro.EXECUTIVO, dataCheckIn, dataCheckOut,
				isTanqueCheio, isAssegurado);

		quarto1 = new QuartoExecutivo(Quarto.TEM_CAMA_EXTRA,
				TiposDeQuarto.DUPLO, dataCheckIn, dataCheckOut);
		quarto2 = new QuartoLuxo(Quarto.NAO_TEM_CAMA_EXTRA,
				TiposDeQuarto.TRIPLO, dataCheckIn, dataCheckOut);
		quarto3 = new SuitePresidencial(dataCheckIn, dataCheckIn);
		quarto4 = new QuartoExecutivo(Quarto.NAO_TEM_CAMA_EXTRA,
				TiposDeQuarto.SIMPLES, dataCheckIn, dataCheckOut);
		quarto5 = new QuartoLuxo(Quarto.NAO_TEM_CAMA_EXTRA,
				TiposDeQuarto.TRIPLO, dataCheckIn, dataCheckOut);

		acompanhantes.add("Jusefa mulher do vidaloka");
		acompanhantes.add("Filho do vidaloka");

		servicos = new ArrayList<Servico>();
		servicos.add(carro);
		servicos.add(baba);

		servicos.add(quarto1);
		servicos.add(quarto2);
		servicos.add(quarto3);
		servicos.add(quarto4);

		this.isReserva = true;

		contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
				dataCheckIn, dataCheckOut, isReserva, servicos);

	}// criaObjetos

	@Test
	public void testCriaContrato() throws ContratoSemQuartoException,
			FrigobarEmListServicosException, DataInvalidaException {
		try {
			contrato1 = new Contrato(null, acompanhantes, estrategia,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, null, estrategia,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado ");
		} catch (NullPointerException e) {

		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, null,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					null, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					dataCheckIn, null, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					dataCheckIn, dataCheckOut, isReserva, null);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		}// try-catch

		dataCheckIn.set(Calendar.MONTH, 6);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {

		}// try-catch

		dataCheckIn.set(Calendar.MONTH, 4);

		List<Servico> servicos = new ArrayList<Servico>();

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado ContratoSemQuartoException");
		} catch (ContratoSemQuartoException e) {

		}// try-catch

		servicos.add(baba);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado ContratoSemQuartoException");
		} catch (ContratoSemQuartoException e) {

		}// try-catch

		servicos.remove(0);

		Frigobar frigobar = new Frigobar();
		servicos.add(frigobar);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado FrigobarEmListServicosException");
		} catch (FrigobarEmListServicosException e) {

		}// try-catch

	}// testCriaContrato

	@Test
	public void testAdicionaServico() throws FrigobarEmListServicosException {
		Frigobar frigobar1 = new Frigobar();

		try {
			contrato1.adicionaServico(frigobar1);
			Assert.fail("Deveria ter lancado FrigobarEmListServicosException");
		} catch (FrigobarEmListServicosException e) {

		}// try-catch
		contrato1.adicionaServico(quarto5);
		List<Servico> servicos = contrato1.getServicos();
		Assert.assertTrue(servicos.get(servicos.size() - 1) instanceof Quarto);
		Assert.assertEquals(servicos.get(servicos.size() - 1), quarto5);

	}// testAdicionaServico

	@Test
	public void testRemoveServico() {
		contrato1.removeServico(quarto4);
		Assert.assertFalse(contrato1.getServicos().contains(quarto4));
		try {
			contrato1.adicionaServico(quarto4);
		} catch (FrigobarEmListServicosException e) {
			Assert.fail("Nao deveria ter lancado excecao");
		}// try-catch

	}// testRemoveServico

}// ContratoTest