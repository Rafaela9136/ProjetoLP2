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

import excecoes.AddQuartoContratoException;
import excecoes.CPFInvalidoException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.FrigobarEmListServicosException;
import excecoes.NomeVazioException;

public class ContratoTest {
	public static final boolean NAO_TEM_CAMA_EXTRA = false;
	public static final boolean TEM_CAMA_EXTRA = true;

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
		dataCheckIn = new GregorianCalendar(2015, Calendar.MAY, 15);
		dataCheckOut = new GregorianCalendar(2015, Calendar.MAY, 20);
		baba = new Baba(dataCheckIn, dataCheckOut);

		this.isTanqueCheio = true;
		this.isAssegurado = true;
		carro = new Carro(TipoCarro.EXECUTIVO, dataCheckIn, dataCheckOut,
				isTanqueCheio, isAssegurado);

		quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO);
		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);
		quarto3 = new SuitePresidencial();
		quarto4 = new QuartoExecutivo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.SIMPLES);
		quarto5 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO);

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
	public void testAdicionaServico() throws FrigobarEmListServicosException,
			AddQuartoContratoException {
		Frigobar frigobar1 = new Frigobar();

		try {
			contrato1.adicionaServico(frigobar1);
			Assert.fail("Deveria ter lancado FrigobarEmListServicosException");
		} catch (FrigobarEmListServicosException e) {

		} catch (AddQuartoContratoException e) {
			Assert.fail("Excecao errada");
		}// try-catch
		try {
			contrato1.adicionaServico(quarto5);
			Assert.fail("Deveria ter lancado AddQuartoContratoException");
		} catch (AddQuartoContratoException e) {

		} catch (FrigobarEmListServicosException e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		contrato1.adicionaServico(carro);
		Assert.assertTrue(contrato1.getServicos()
				.get(contrato1.getServicos().size() - 1).equals(carro));

	}// testAdicionaServico

	@Test
	public void testCalculaValorTotalServicos() throws NullPointerException,
			ContratoSemQuartoException, FrigobarEmListServicosException,
			DataInvalidaException {
		servicos = new ArrayList<Servico>();
		
		servicos.add(carro);
		servicos.add(baba);

		servicos.add(quarto1);
		servicos.add(quarto2);
		servicos.add(quarto3);
		servicos.add(quarto4);
		System.out.println(dataCheckIn.getTime());
		System.out.println(dataCheckOut.getTime());
		
		Assert.assertEquals(contrato1.getServicos().get(0).getPreco(), 550, 0.05);
		Assert.assertEquals(contrato1.getServicos().get(1).getPreco(), 4550, 0.05);
		Assert.assertEquals(21750, contrato1.calculaValorTotalServicos(), 0.5);

	}// testCalculaValorTotalServicos

	@Test
	public void testEquals() throws NullPointerException,
			ContratoSemQuartoException, FrigobarEmListServicosException,
			DataInvalidaException {
		dataCheckIn = new GregorianCalendar(2015, Calendar.MAY, 15);
		dataCheckOut = new GregorianCalendar(2015, Calendar.MAY, 20);
		contrato1 = new Contrato(hospedeTitular, acompanhantes, estrategia,
				dataCheckIn, dataCheckOut, isReserva, servicos);

		contrato2 = new Contrato(hospedeTitular, acompanhantes, estrategia,
				dataCheckIn, dataCheckOut, isReserva, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

	}// testEquals

}// ContratoTest