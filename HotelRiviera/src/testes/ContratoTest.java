package testes;

import hotel.Baba;
import hotel.Carro;
import hotel.ContaRestaurante;
import hotel.Contrato;
import hotel.EstrategiaAplicavel;
import hotel.EstrategiaNatalReveillon;
import hotel.EstrategiaSaoJoaoPremium;
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
import excecoes.CamaExtraException;
import excecoes.CartaoInvalidoException;
import excecoes.ComentarioVazioException;
import excecoes.ContratoFechadoException;
import excecoes.ContratoSemOpiniaoException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.EstouroDeCaracteresException;
import excecoes.FrigobarEmListServicosException;
import excecoes.NomeVazioException;
import excecoes.NotaInvalidaException;
import excecoes.NumeroInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.StringVaziaException;
import excecoes.ValorNegativoException;

public class ContratoTest {
	public static final boolean NAO_TEM_CAMA_EXTRA = false;
	public static final boolean TEM_CAMA_EXTRA = true;

	private Contrato contrato1;
	private Contrato contrato2;

	private Hospede hospedeTitular;
	private Calendar dataNascimento;
	private List<String> acompanhantes = new ArrayList<String>();
	private Calendar dataCheckIn;
	private Calendar dataCheckOut;
	private Calendar momentoAgr;
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
			NomeVazioException, StringVaziaException, CartaoInvalidoException,
			CamaExtraException, StringInvalidaException,
			NumeroInvalidoException {
		dataNascimento = Calendar.getInstance();
		hospedeTitular = new Hospede("Ricardo vidaloka", dataNascimento,
				"0123.4567.8999.9999");
		// Usada para settar as datas de teste, para que os testes continuem
		// funcionando sem importar
		// o tempo que faca que o teste foi criado
		momentoAgr = new GregorianCalendar();
		dataCheckIn = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 1);
		dataCheckOut = new GregorianCalendar(dataCheckIn.get(Calendar.YEAR),
				dataCheckIn.get(Calendar.MONTH),
				dataCheckIn.get(Calendar.DAY_OF_MONTH) + 5);
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

		contrato1 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

	}// criaObjetos

	@Test
	public void testCriaContrato() throws ContratoSemQuartoException,
			FrigobarEmListServicosException, DataInvalidaException {
		try {
			contrato1 = new Contrato(null, acompanhantes, dataCheckIn,
					dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, null, dataCheckIn,
					dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado ");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, null,
					dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, null, isReserva, servicos);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, isReserva, null);
			Assert.fail("Deveria ter lancado NullPointerException");
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		dataCheckIn.set(Calendar.MONTH, 6);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		dataCheckIn.set(Calendar.MONTH, 4);

		List<Servico> servicos = new ArrayList<Servico>();

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado ContratoSemQuartoException");
		} catch (ContratoSemQuartoException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		servicos.add(baba);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado ContratoSemQuartoException");
		} catch (ContratoSemQuartoException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		servicos.remove(0);

		Frigobar frigobar = new Frigobar();
		servicos.add(frigobar);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, isReserva, servicos);
			Assert.fail("Deveria ter lancado FrigobarEmListServicosException");
		} catch (FrigobarEmListServicosException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		Calendar dataCheckIn = new GregorianCalendar(2015, Calendar.FEBRUARY, 6);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, isReserva, this.servicos);
			Assert.fail("Deveria ter lancado DataInvalidaException");
		} catch (DataInvalidaException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
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

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch
		try {
			contrato1.adicionaServico(quarto5);
			Assert.fail("Deveria ter lancado AddQuartoContratoException");
		} catch (AddQuartoContratoException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		contrato1.adicionaServico(carro);
		Assert.assertTrue(contrato1.getServicos()
				.get(contrato1.getServicos().size() - 1).equals(carro));

	}// testAdicionaServico

	@Test
	public void testCalculaValorTotalServicos() throws ValorNegativoException,
			NullPointerException, DataInvalidaException {
		Assert.assertEquals(21600, contrato1.calculaValorTotalServicos(), 0.5);

		Calendar dataInicio = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), Calendar.MONTH,
				Calendar.DAY_OF_MONTH + 1);

		Calendar dataTermino = new GregorianCalendar(
				dataInicio.get(Calendar.YEAR), dataInicio.get(Calendar.MONTH),
				dataInicio.get(Calendar.DAY_OF_MONTH) + 5);

		Carro carro2 = new Carro(TipoCarro.LUXO, dataInicio, dataTermino,
				isTanqueCheio, isAssegurado);

		servicos.add(carro2);

		Assert.assertEquals(22500, contrato1.calculaValorTotalServicos(), 0.5);

		Baba baba2 = new Baba(dataInicio, dataTermino);

		servicos.add(baba2);

		Assert.assertEquals(28050, contrato1.calculaValorTotalServicos(), 0.5);
		ContaRestaurante conta = new ContaRestaurante(1000);

		servicos.add(conta);

		Assert.assertEquals(29250, contrato1.calculaValorTotalServicos(), 0.5);

	}// testCalculaValorTotalServicos

	@Test
	public void testSetIsAberto() throws ContratoFechadoException,
			ContratoSemOpiniaoException, NullPointerException,
			NotaInvalidaException, EstouroDeCaracteresException,
			ComentarioVazioException {
		final boolean FECHADO = false;
		final boolean ABERTO = true;
		try {
			contrato1.setIsAberto(FECHADO);
			Assert.fail("Deveria ter lancado ContratoSemOpiniaoException");
		} catch (ContratoSemOpiniaoException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch

		contrato1.inicializaOpiniao(6, "Eh meio paia o hotel");
		contrato1.setIsAberto(FECHADO);

		try {
			contrato1.setIsAberto(ABERTO);
			Assert.fail("Deveria ter lancado ContratoFechadoException");
		} catch (ContratoFechadoException e) {

		} catch (Exception e) {
			Assert.fail("Nao deveria ter lancado essa excecao");
		}// try-catch
	}// testSetIsAberto

	@Test
	public void testEquals() throws NullPointerException,
			ContratoSemQuartoException, FrigobarEmListServicosException,
			DataInvalidaException, CPFInvalidoException,
			AddQuartoContratoException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException {
		dataCheckIn = new GregorianCalendar(2015, Calendar.MAY, 15);
		dataCheckOut = new GregorianCalendar(2015, Calendar.MAY, 20);
		contrato1 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

		Calendar dataNascimento2 = new GregorianCalendar(1500, 6, 9);
		Hospede hospedeTitular2 = new Hospede("Jao da lenha", dataNascimento2,
				"0123.4567.8999.9999");

		contrato2 = new Contrato(hospedeTitular2, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		Assert.assertFalse(contrato1.equals(contrato2));

		acompanhantes.add("Mais um");

		List<String> acompanhantes2 = new ArrayList<String>();
		contrato2 = new Contrato(hospedeTitular, acompanhantes2, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		Assert.assertFalse(contrato1.equals(contrato2));

		final boolean NAO_RESERVA = false;

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, NAO_RESERVA, servicos);

		Assert.assertFalse(contrato1.equals(contrato2));

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

		List<Servico> servicos2 = new ArrayList<Servico>();

		servicos2.add(quarto5);

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos2);

		Assert.assertFalse(contrato1.equals(contrato2));

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

		contrato2.adicionaDespesa(500);

		Assert.assertFalse(contrato1.equals(contrato2));

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, isReserva, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

	}// testEquals

}// ContratoTest