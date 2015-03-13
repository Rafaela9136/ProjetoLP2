package testes;

import hotel.*;

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
import excecoes.NomeInvalidoException;
import excecoes.NomeVazioException;
import excecoes.NotaInvalidaException;
import excecoes.NumeroInvalidoException;
import excecoes.RemocaoInvalidaException;
import excecoes.ServicoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.ValorNegativoException;

public class ContratoTest {
	public static final boolean NAO_TEM_CAMA_EXTRA = false;
	public static final boolean TEM_CAMA_EXTRA = true;

	private Contrato contrato1, contrato2;

	private Hospede hospedeTitular;
	private Calendar dataNascimento;
	private List<String> acompanhantes = new ArrayList<String>();
	private Calendar dataCheckIn, dataCheckOut, momentoAgr;
	private List<Servico> servicos;
	private Baba baba;

	private Carro carro;
	private boolean isTanqueCheio;
	private boolean isAssegurado;

	private Quarto quarto1, quarto2, quarto3, quarto4, quarto5;

	@Before
	public void criaObjetos() throws NullPointerException,
			CPFInvalidoException, ContratoSemQuartoException,
			DataInvalidaException, NomeVazioException, CartaoInvalidoException,
			CamaExtraException, StringInvalidaException,
			NumeroInvalidoException, NomeInvalidoException {
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

		quarto1 = new QuartoExecutivo(TEM_CAMA_EXTRA, TiposDeQuarto.DUPLO,
				dataCheckIn, dataCheckOut);
		quarto2 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO,
				dataCheckIn, dataCheckOut);
		quarto3 = new SuitePresidencial(dataCheckIn, dataCheckIn);
		quarto4 = new QuartoExecutivo(NAO_TEM_CAMA_EXTRA,
				TiposDeQuarto.SIMPLES, dataCheckIn, dataCheckIn);
		quarto5 = new QuartoLuxo(NAO_TEM_CAMA_EXTRA, TiposDeQuarto.TRIPLO,
				dataCheckIn, dataCheckIn);

		acompanhantes.add("Jusefa mulher do vidaloka");
		acompanhantes.add("Filho do vidaloka");

		servicos = new ArrayList<Servico>();
		servicos.add(carro);
		servicos.add(baba);

		servicos.add(quarto1);
		servicos.add(quarto2);
		servicos.add(quarto3);
		servicos.add(quarto4);

		contrato1 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

	}// criaObjetos

	@Test
	public void testCriaContrato() throws ContratoSemQuartoException,
			DataInvalidaException, NomeInvalidoException {
		try {
			contrato1 = new Contrato(null, acompanhantes, dataCheckIn,
					dataCheckOut, servicos);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			contrato1 = new Contrato(hospedeTitular, null, dataCheckIn,
					dataCheckOut, servicos);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes, null,
					dataCheckOut, servicos);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, null, servicos);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		dataCheckIn.set(Calendar.MONTH, dataCheckOut.get(Calendar.MONTH) + 1);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, servicos);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Calendar dataAnteriorCheckIn = new GregorianCalendar(
				dataCheckIn.get(Calendar.YEAR),
				dataCheckIn.get(Calendar.MONTH),
				dataCheckIn.get(Calendar.DAY_OF_MONTH) - 1);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataAnteriorCheckIn, dataCheckOut, servicos);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Calendar dataPosteriorCheckOut = new GregorianCalendar(
				dataCheckOut.get(Calendar.YEAR),
				dataCheckOut.get(Calendar.MONTH),
				dataCheckOut.get(Calendar.DAY_OF_MONTH) + 1);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataPosteriorCheckOut, servicos);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		Calendar dataCheckIn = new GregorianCalendar(2015, Calendar.FEBRUARY, 6);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, this.servicos);
		} catch (DataInvalidaException e) {
			Assert.assertTrue(true);
		}

		this.dataCheckIn = new GregorianCalendar(momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 1);

		List<Servico> servicos = new ArrayList<Servico>();

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, servicos);
		} catch (ContratoSemQuartoException e) {
			Assert.assertTrue(true);
		}

		servicos.add(baba);

		try {
			contrato1 = new Contrato(hospedeTitular, acompanhantes,
					dataCheckIn, dataCheckOut, servicos);
		} catch (ContratoSemQuartoException e) {
			Assert.assertTrue(true);
		}

		servicos.remove(0);

	}// testCriaContrato

	@Test
	public void testAdicionaServico() throws AddQuartoContratoException,
			NullPointerException, ServicoInvalidoException,
			DataInvalidaException, ContratoSemQuartoException,
			NomeInvalidoException {
		try {
			contrato1.adicionaServico(quarto5);
		} catch (AddQuartoContratoException e) {
			Assert.assertTrue(true);
		}

		try {
			contrato1.adicionaServico(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Calendar dataCheckIn = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 1);

		Carro carro = new Carro(TipoCarro.EXECUTIVO, dataCheckIn, dataCheckOut,
				isTanqueCheio, isAssegurado);

		Calendar dataCheckIn2 = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 2);

		Calendar dataCheckOut2 = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR),
				momentoAgr.get(Calendar.MONTH) + 1,
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 6);

		Quarto suitePresidencial = new SuitePresidencial(dataCheckIn2,
				dataCheckOut2);

		List<String> acomp = new ArrayList<String>();

		List<Servico> servs = new ArrayList<Servico>();

		servs.add(suitePresidencial);

		Contrato contr = new Contrato(hospedeTitular, acomp, dataCheckIn2,
				dataCheckOut2, servicos);

		try {
			contr.adicionaServico(carro);
		} catch (ServicoInvalidoException e) {
			Assert.assertTrue(true);
		}

		carro = new Carro(TipoCarro.EXECUTIVO, this.dataCheckIn, dataCheckOut2,
				isTanqueCheio, isAssegurado);

		try {
			contrato1.adicionaServico(carro);
		} catch (ServicoInvalidoException e) {
			Assert.assertTrue(true);
		}

		contrato1.adicionaServico(this.carro);
		Assert.assertTrue(contrato1.getServicos()
				.get(contrato1.getServicos().size() - 1).equals(this.carro));

	}// testAdicionaServico

	@Test
	public void testRemoveServico() throws RemocaoInvalidaException {
		contrato1.removeServico(quarto4);
		List<Servico> servicos = new ArrayList<Servico>();

		servicos.add(carro);
		servicos.add(baba);
		servicos.add(quarto1);
		servicos.add(quarto2);
		servicos.add(quarto3);

		Assert.assertEquals(contrato1.getServicos(), servicos);

		contrato1.removeServico(quarto3);
		contrato1.removeServico(quarto2);

		try {
			contrato1.removeServico(quarto1);
		} catch (RemocaoInvalidaException e) {
			Assert.assertTrue(true);
		}

	}// testRemoveServico

	@Test
	public void testCalculaValorTotalServicos() throws ValorNegativoException,
			NullPointerException, DataInvalidaException {
		Assert.assertEquals(21550, contrato1.calculaValorTotalServicos(), 0.5);

		Calendar dataInicio = new GregorianCalendar(
				momentoAgr.get(Calendar.YEAR), momentoAgr.get(Calendar.MONTH),
				momentoAgr.get(Calendar.DAY_OF_MONTH) + 1);

		Calendar dataTermino = new GregorianCalendar(
				dataInicio.get(Calendar.YEAR), dataInicio.get(Calendar.MONTH),
				dataInicio.get(Calendar.DAY_OF_MONTH) + 5);

		servicos.add(new Carro(TipoCarro.LUXO, dataInicio, dataTermino,
				isTanqueCheio, isAssegurado));

		Assert.assertEquals(22300, contrato1.calculaValorTotalServicos(), 0.5);

		Baba baba2 = new Baba(dataInicio, dataTermino);

		servicos.add(baba2);

		Assert.assertEquals(26925, contrato1.calculaValorTotalServicos(), 0.5);
		ContaRestaurante conta = new ContaRestaurante(1000);

		servicos.add(conta);

		Assert.assertEquals(27925, contrato1.calculaValorTotalServicos(), 0.5);

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
		} catch (ContratoSemOpiniaoException e) {
			Assert.assertTrue(true);
		}

		contrato1.inicializaOpiniao(6, "Eh meio paia o hotel");
		contrato1.setIsAberto(FECHADO);

		try {
			contrato1.setIsAberto(ABERTO);
		} catch (ContratoFechadoException e) {
			Assert.assertTrue(true);
		}
	}// testSetIsAberto

	@Test
	public void testEquals() throws NullPointerException,
			ContratoSemQuartoException, DataInvalidaException,
			CPFInvalidoException, AddQuartoContratoException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException, NomeInvalidoException {
		dataCheckIn = new GregorianCalendar(2015, Calendar.MAY, 15);
		dataCheckOut = new GregorianCalendar(2015, Calendar.MAY, 20);
		contrato1 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

		Calendar dataNascimento2 = new GregorianCalendar(1500, 6, 9);
		Hospede hospedeTitular2 = new Hospede("Jao da lenha", dataNascimento2,
				"0123.4567.8999.9999");

		contrato2 = new Contrato(hospedeTitular2, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

		Assert.assertFalse(contrato1.equals(contrato2));

		acompanhantes.add("Mais um");

		List<String> acompanhantes2 = new ArrayList<String>();
		contrato2 = new Contrato(hospedeTitular, acompanhantes2, dataCheckIn,
				dataCheckOut, servicos);

		Assert.assertFalse(contrato1.equals(contrato2));

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

		List<Servico> servicos2 = new ArrayList<Servico>();

		servicos2.add(quarto5);

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos2);

		Assert.assertFalse(contrato1.equals(contrato2));

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

		contrato2.adicionaDespesa(500);

		Assert.assertFalse(contrato1.equals(contrato2));

		contrato2 = new Contrato(hospedeTitular, acompanhantes, dataCheckIn,
				dataCheckOut, servicos);

		Assert.assertTrue(contrato1.equals(contrato2));

	}// testEquals

}// ContratoTest