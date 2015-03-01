package testes;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

import hotel.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import excecoes.CPFInvalidoException;
import excecoes.CamaExtraException;
import excecoes.CartaoInvalidoException;
import excecoes.ComentarioVazioException;
import excecoes.ContratoSemQuartoException;
import excecoes.DataInvalidaException;
import excecoes.EstouroDeCaracteresException;
import excecoes.ExecutivosDuploOcupadosException;
import excecoes.ExecutivosSimplesOcupadosException;
import excecoes.ExecutivosTriploOcupadosException;
import excecoes.LoginExistenteException;
import excecoes.LoginInvalidoException;
import excecoes.LuxosDuploOcupadosException;
import excecoes.LuxosSimplesOcupadosException;
import excecoes.LuxosTriploOcupadosException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.NomeInvalidoException;
import excecoes.NotaInvalidaException;
import excecoes.NumeroInvalidoException;
import excecoes.SenhaInvalidaException;
import excecoes.StringInvalidaException;
import excecoes.StringVaziaException;
import excecoes.SuitesPresidenciaisOcupadasException;

public class HotelTest {

	private Hotel hotel;
	private Contrato contrato1, contrato2, contrato3;
	private List<Opiniao> opinioes;
	private List<Contrato> contratos;
	private List<Servico> servicos1, servicos2, servicos3;
	private List<String> acompanhantes;
	private int[] quartosDesocupados = { 5, 15, 20, 5, 15, 20, 5 };

	@Before
	public void criaObjetos() throws NullPointerException,
			LoginInvalidoException, SenhaInvalidaException,
			NomeCompletoInvalidoException, NotaInvalidaException,
			EstouroDeCaracteresException, ComentarioVazioException,
			ContratoSemQuartoException, DataInvalidaException,
			NomeInvalidoException, CPFInvalidoException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException, CamaExtraException {
		opinioes = new ArrayList<Opiniao>();
		contratos = new ArrayList<Contrato>();
		servicos1 = new ArrayList<Servico>();
		servicos2 = new ArrayList<Servico>();
		servicos3 = new ArrayList<Servico>();
		acompanhantes = new ArrayList<String>();
		acompanhantes.add("Joao");
		acompanhantes.add("Maria");

		servicos1.add(new QuartoExecutivo(false, TiposDeQuarto.SIMPLES,
				new GregorianCalendar(2016, Calendar.NOVEMBER, 22),
				new GregorianCalendar(2016, Calendar.NOVEMBER, 28)));
		contrato1 = new Contrato(new Hospede("Joao Paulo",
				new GregorianCalendar(1990, Calendar.JANUARY, 20),
				"2264.5130.8467.0319"), acompanhantes, new GregorianCalendar(
				2016, Calendar.NOVEMBER, 22), new GregorianCalendar(2016,
				Calendar.NOVEMBER, 28), servicos1);

		servicos2.add(new QuartoLuxo(false, TiposDeQuarto.SIMPLES,
				new GregorianCalendar(2016, Calendar.JULY, 2),
				new GregorianCalendar(2016, Calendar.JULY, 7)));
		contrato2 = new Contrato(new Hospede("Carla Amorim",
				new GregorianCalendar(1988, Calendar.JUNE, 10),
				"3102.5431.9526.7314"), acompanhantes, new GregorianCalendar(
				2016, Calendar.JULY, 2), new GregorianCalendar(2016,
				Calendar.JULY, 7), servicos2);

		servicos3.add(new SuitePresidencial(new GregorianCalendar(2016,
				Calendar.APRIL, 20), new GregorianCalendar(2016,
				Calendar.APRIL, 23)));
		contrato3 = new Contrato(new Hospede("Pedro Carvalho",
				new GregorianCalendar(1984, Calendar.SEPTEMBER, 17),
				"5461.3120.8746.9130"), acompanhantes, new GregorianCalendar(
				2016, Calendar.APRIL, 20), new GregorianCalendar(2016,
				Calendar.APRIL, 23), servicos3);

		opinioes.add(new Opiniao("Razoavel", 5));
		opinioes.add(new Opiniao("Bom", 7));
		opinioes.add(new Opiniao("Otimo", 10));

		contratos.add(contrato1);
		contratos.add(contrato2);
		contratos.add(contrato3);

		hotel = new Hotel(contratos, quartosDesocupados, opinioes);
	}

	@Test
	public void testaCriaHotel() throws LoginInvalidoException,
			SenhaInvalidaException, NomeCompletoInvalidoException {
		try {
			new Hotel(null, quartosDesocupados, opinioes);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hotel(contratos, null, opinioes);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		try {
			new Hotel(contratos, quartosDesocupados, null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(hotel.getContratos(), contratos);
		Assert.assertEquals(hotel.getOpinioes(), opinioes);
	}

	@Test
	public void testaAdicionaRemoveContrato()
			throws ExecutivosDuploOcupadosException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException {
		try {
			hotel.adicionaContrato(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Assert.assertFalse(hotel.removeContrato(null));

		Assert.assertTrue(hotel.getContratos().contains(contrato1));
		Assert.assertTrue(hotel.getContratos().contains(contrato2));
		Assert.assertTrue(hotel.getContratos().contains(contrato3));

		Assert.assertTrue(hotel.removeContrato(contrato1));
		Assert.assertFalse(hotel.getContratos().contains(contrato1));
		Assert.assertFalse(hotel.removeContrato(contrato1));

		hotel.adicionaContrato(contrato1);
		Assert.assertTrue(hotel.getContratos().contains(contrato1));
	}

	@Test
	public void AdicionaRemoveConta() throws LoginInvalidoException,
			NomeCompletoInvalidoException, LoginExistenteException,
			NullPointerException, SenhaInvalidaException {
		try {
			hotel.adicionaConta(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(hotel.getContasHotel().get(0).getLogin(), "gerente");
		try {
			hotel.adicionaConta(new Conta("gerente", "senha1",
					"Nome completo aqui", TipoFuncionario.GERENTE));
		} catch (LoginExistenteException e) {
			Assert.assertTrue(true);
		}
	}

}
