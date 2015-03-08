package testes;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.ArrayList;

import hotel.*;

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
import excecoes.ExecutivosDuploOcupadosException;
import excecoes.ExecutivosSimplesOcupadosException;
import excecoes.ExecutivosTriploOcupadosException;
import excecoes.ListaVaziaException;
import excecoes.LoginExistenteException;
import excecoes.LoginInvalidoException;
import excecoes.LuxosDuploOcupadosException;
import excecoes.LuxosSimplesOcupadosException;
import excecoes.LuxosTriploOcupadosException;
import excecoes.MesInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.NomeInvalidoException;
import excecoes.NotaInvalidaException;
import excecoes.NumeroInvalidoException;
import excecoes.SenhaInvalidaException;
import excecoes.ServicoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.StringVaziaException;
import excecoes.SuitesPresidenciaisOcupadasException;
import excecoes.ValorNegativoException;

public class HotelTest {

	private Hotel hotel;
	private Contrato contrato1, contrato2, contrato3, contrato4, contrato5,
			contrato6, contrato7, contrato8;
	private List<Servico> servicos1, servicos2, servicos3, servicos4,
			servicos5, servicos6, servicos7, servicos8;
	private List<String> acompanhantes;
	private Calendar dataAtual;
	private int diaAtual, mesAtual, anoAtual, proxAno;
	private float nota;
	private String comentario;

	@Before
	public void criaObjetos() throws NullPointerException, CamaExtraException,
			DataInvalidaException, ContratoSemQuartoException,
			NomeInvalidoException, CPFInvalidoException, StringVaziaException,
			CartaoInvalidoException, StringInvalidaException,
			NumeroInvalidoException, LoginInvalidoException,
			SenhaInvalidaException, NomeCompletoInvalidoException,
			ExecutivosDuploOcupadosException, LuxosSimplesOcupadosException,
			LuxosDuploOcupadosException, LuxosTriploOcupadosException,
			ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException {
		nota = 5;
		comentario = "Bom";
		dataAtual = new GregorianCalendar();
		diaAtual = dataAtual.get(Calendar.DAY_OF_MONTH);
		mesAtual = dataAtual.get(Calendar.MONTH);
		anoAtual = dataAtual.get(Calendar.YEAR);
		proxAno = anoAtual + 1;

		hotel = new Hotel();

		servicos1 = new ArrayList<Servico>();
		servicos2 = new ArrayList<Servico>();
		servicos3 = new ArrayList<Servico>();
		servicos4 = new ArrayList<Servico>();
		servicos5 = new ArrayList<Servico>();
		servicos6 = new ArrayList<Servico>();
		servicos7 = new ArrayList<Servico>();
		servicos8 = new ArrayList<Servico>();
		acompanhantes = new ArrayList<String>();
		acompanhantes.add("Joao");
		acompanhantes.add("Maria");

		servicos1.add(new QuartoExecutivo(false, TiposDeQuarto.SIMPLES,
				new GregorianCalendar(proxAno, Calendar.NOVEMBER, 22),
				new GregorianCalendar(proxAno, Calendar.NOVEMBER, 28)));
		contrato1 = new Contrato(new Hospede("Joao Paulo",
				new GregorianCalendar(1990, Calendar.JANUARY, 20),
				"2264.5130.8467.0319"), acompanhantes, new GregorianCalendar(
				proxAno, Calendar.NOVEMBER, 22), new GregorianCalendar(proxAno,
				Calendar.NOVEMBER, 28), servicos1);

		servicos2.add(new QuartoLuxo(false, TiposDeQuarto.SIMPLES,
				new GregorianCalendar(proxAno, Calendar.JULY, 2),
				new GregorianCalendar(proxAno, Calendar.JULY, 7)));
		contrato2 = new Contrato(new Hospede("Carla Amorim",
				new GregorianCalendar(1988, Calendar.JUNE, 10),
				"3102.5431.9526.7314"), acompanhantes, new GregorianCalendar(
				proxAno, Calendar.JULY, 2), new GregorianCalendar(proxAno,
				Calendar.JULY, 7), servicos2);

		servicos3.add(new SuitePresidencial(new GregorianCalendar(proxAno,
				Calendar.APRIL, 20), new GregorianCalendar(proxAno,
				Calendar.APRIL, 23)));
		contrato3 = new Contrato(new Hospede("Pedro Carvalho",
				new GregorianCalendar(1984, Calendar.SEPTEMBER, 17),
				"5461.3120.8746.9130"), acompanhantes, new GregorianCalendar(
				proxAno, Calendar.APRIL, 20), new GregorianCalendar(proxAno,
				Calendar.APRIL, 23), servicos3);

		servicos4.add(new QuartoExecutivo(false, TiposDeQuarto.DUPLO,
				new GregorianCalendar(), new GregorianCalendar(anoAtual,
						mesAtual, diaAtual + 6)));
		contrato4 = new Contrato(new Hospede("Juca", new GregorianCalendar(
				1980, Calendar.JANUARY, 5), "9681.1349.6472.1307"),
				acompanhantes, new GregorianCalendar(), new GregorianCalendar(
						anoAtual, mesAtual, diaAtual + 6), servicos4);

		servicos5.add(new QuartoExecutivo(false, TiposDeQuarto.TRIPLO,
				new GregorianCalendar(proxAno, Calendar.DECEMBER, 28),
				new GregorianCalendar(proxAno + 1, Calendar.JANUARY, 2)));
		contrato5 = new Contrato(new Hospede("Marta", new GregorianCalendar(
				1982, Calendar.MARCH, 25), "5461.4310.3216.7941"),
				acompanhantes, new GregorianCalendar(proxAno,
						Calendar.DECEMBER, 28), new GregorianCalendar(
						proxAno + 1, Calendar.JANUARY, 2), servicos5);

		servicos6.add(new QuartoLuxo(false, TiposDeQuarto.DUPLO,
				new GregorianCalendar(), new GregorianCalendar(anoAtual,
						mesAtual, diaAtual + 5)));
		contrato6 = new Contrato(new Hospede("Pedro", new GregorianCalendar(
				1985, Calendar.SEPTEMBER, 13), "6457.1345.1037.9471"),
				acompanhantes, new GregorianCalendar(), new GregorianCalendar(
						anoAtual, mesAtual, diaAtual + 5), servicos6);

		servicos7.add(new QuartoLuxo(false, TiposDeQuarto.TRIPLO,
				new GregorianCalendar(), new GregorianCalendar(anoAtual,
						mesAtual, diaAtual + 3)));
		contrato7 = new Contrato(new Hospede("Nobrega Duarte",
				new GregorianCalendar(1970, Calendar.MAY, 20),
				"1342.6794.2451.1308"), acompanhantes, new GregorianCalendar(),
				new GregorianCalendar(anoAtual, mesAtual, diaAtual + 3),
				servicos7);

		servicos8.add(new QuartoExecutivo(false, TiposDeQuarto.SIMPLES,
				new GregorianCalendar(), new GregorianCalendar(anoAtual,
						mesAtual, diaAtual + 4)));
		contrato8 = new Contrato(new Hospede("Pablo", new GregorianCalendar(
				1990, Calendar.JUNE, 2), "5461.1320.8761.3490"), acompanhantes,
				new GregorianCalendar(), new GregorianCalendar(anoAtual,
						mesAtual, diaAtual + 4), servicos8);

		hotel.adicionaContrato(contrato1);
		hotel.adicionaContrato(contrato2);
		hotel.adicionaContrato(contrato3);
	}

	@Test
	public void testaAdicionaRemoveContrato()
			throws ExecutivosDuploOcupadosException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, ContratoFechadoException,
			ContratoSemOpiniaoException {
		try {
			hotel.adicionaContrato(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		
		hotel.adicionaContrato(contrato6);
		try {
			hotel.removeContrato(contrato6);
		} catch (ContratoSemOpiniaoException e) {
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
	public void testaAdicionaRemoveConta() throws LoginInvalidoException,
			NomeCompletoInvalidoException, LoginExistenteException,
			NullPointerException, SenhaInvalidaException, ListaVaziaException {
		try {
			hotel.adicionaConta(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(hotel.getContasHotel().get(0).getLogin(), "gerente");
		try {
			hotel.adicionaConta(new Conta("gerente", "outrasenha",
					"Nome completo aqui", TipoFuncionario.GERENTE));
		} catch (LoginExistenteException e) {
			Assert.assertTrue(true);
		}

		Assert.assertTrue(hotel.removeConta("gerente"));
		try {
			hotel.removeConta("qualquer");
		} catch (ListaVaziaException e) {
			Assert.assertTrue(true);
		}

		hotel.adicionaConta(new Conta("loginconta", "senhaconta",
				"Nome Completo Conta", TipoFuncionario.BALCONISTA));
		Assert.assertFalse(hotel.removeConta("login"));
		Assert.assertTrue(hotel.removeConta("loginconta"));
	}

	@Test
	public void testaPesquisaConta() throws LoginInvalidoException,
			NullPointerException, SenhaInvalidaException,
			NomeCompletoInvalidoException, LoginExistenteException {
		try {
			hotel.pesquisaConta(null, "senhaa");
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}
		try {
			hotel.pesquisaConta("user", null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Conta conta = new Conta("usuario", "senhadousuario",
				"Nome completo usuario", TipoFuncionario.BALCONISTA);
		Assert.assertFalse(hotel.pesquisaConta(conta.getLogin(),
				conta.getSenha()));
		hotel.adicionaConta(conta);
		Assert.assertTrue(hotel.pesquisaConta(conta.getLogin(),
				conta.getSenha()));
		Assert.assertFalse(hotel.pesquisaConta(conta.getLogin(),
				"senhadiferente"));
		Assert.assertFalse(hotel.pesquisaConta("logindiferente",
				conta.getSenha()));
	}

	@Test
	public void testaGetContratosRemovidos() throws ContratoFechadoException,
			ContratoSemOpiniaoException, LuxosSimplesOcupadosException,
			LuxosDuploOcupadosException, LuxosTriploOcupadosException,
			ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, NullPointerException,
			NotaInvalidaException, EstouroDeCaracteresException,
			ComentarioVazioException {
		Assert.assertTrue(hotel.getContratos().contains(contrato1));
		Assert.assertTrue(hotel.getContratos().contains(contrato2));
		Assert.assertTrue(hotel.getContratos().contains(contrato3));

		hotel.adicionaContrato(contrato4);
		Assert.assertTrue(hotel.getContratos().contains(contrato4));
		contrato4.inicializaOpiniao(nota, comentario);
		hotel.removeContrato(contrato4);
		Assert.assertTrue(hotel.getContratosRemovidos().contains(contrato4));

		hotel.adicionaContrato(contrato6);
		Assert.assertTrue(hotel.getContratos().contains(contrato6));
		contrato6.inicializaOpiniao(nota, comentario);
		hotel.removeContrato(contrato6);
		Assert.assertTrue(hotel.getContratosRemovidos().contains(contrato6));

		hotel.removeContrato(contrato1);
		Assert.assertFalse(hotel.getContratos().contains(contrato1));
		Assert.assertFalse(hotel.getContratosRemovidos().contains(contrato1));

		hotel.removeContrato(contrato2);
		Assert.assertFalse(hotel.getContratos().contains(contrato2));
		Assert.assertFalse(hotel.getContratosRemovidos().contains(contrato2));

		hotel.removeContrato(contrato3);
		Assert.assertFalse(hotel.getContratos().contains(contrato3));
		Assert.assertFalse(hotel.getContratosRemovidos().contains(contrato3));
	}

	@Test
	public void testaGetEstatisticaQuartos()
			throws LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, ContratoFechadoException,
			ContratoSemOpiniaoException, NullPointerException,
			NotaInvalidaException, EstouroDeCaracteresException,
			ComentarioVazioException {
		hotel.adicionaContrato(contrato8);
		hotel.adicionaContrato(contrato7);
		hotel.adicionaContrato(contrato6);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 0, 0, 0, 1, 1, 0]");

		hotel.adicionaContrato(contrato1);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 0, 0, 0, 1, 1, 0]");

		hotel.adicionaContrato(contrato2);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 0, 0, 0, 1, 1, 0]");

		hotel.adicionaContrato(contrato4);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 1, 0, 0, 1, 1, 0]");

		hotel.removeContrato(contrato1);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 1, 0, 0, 1, 1, 0]");

		contrato4.inicializaOpiniao(nota, comentario);
		hotel.removeContrato(contrato4);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 1, 0, 0, 1, 1, 0]");

		hotel.adicionaContrato(contrato6);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 1, 0, 0, 2, 1, 0]");

		contrato6.inicializaOpiniao(nota, comentario);
		hotel.removeContrato(contrato6);
		Assert.assertEquals(Arrays.toString(hotel.getEstatisticaQuartos()),
				"[1, 1, 0, 0, 2, 1, 0]");
	}

	@Test
	public void testaGetEstatisticaQuartosPorMes() throws MesInvalidoException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, ContratoFechadoException,
			ContratoSemOpiniaoException, NullPointerException,
			NotaInvalidaException, EstouroDeCaracteresException,
			ComentarioVazioException {
		try {
			hotel.getEstatisticaQuartos(0);
		} catch (MesInvalidoException e) {
			Assert.assertTrue(true);
		}

		try {
			hotel.getEstatisticaQuartos(13);
		} catch (MesInvalidoException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato1.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[0, 0, 0, 0, 0, 0, 0]");

		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato2.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[0, 0, 0, 0, 0, 0, 0]");

		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato3.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[0, 0, 0, 0, 0, 0, 0]");

		hotel.adicionaContrato(contrato8);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato8.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[1, 0, 0, 0, 0, 0, 0]");

		hotel.adicionaContrato(contrato7);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato7.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[1, 0, 0, 0, 0, 1, 0]");

		contrato7.inicializaOpiniao(nota, comentario);
		hotel.removeContrato(contrato7);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato7.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[1, 0, 0, 0, 0, 1, 0]");
		
		hotel.adicionaContrato(contrato6);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato7.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[1, 0, 0, 0, 1, 1, 0]");
		
		hotel.adicionaContrato(contrato5);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato7.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[1, 0, 0, 0, 1, 1, 0]");
		
		hotel.removeContrato(contrato5);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaQuartos(contrato7.getDataCheckIn().get(
						Calendar.MONTH) + 1)), "[1, 0, 0, 0, 1, 1, 0]");
	}

	@Test
	public void testaAdicionaOpiniao() throws NullPointerException,
			NotaInvalidaException, EstouroDeCaracteresException,
			ComentarioVazioException {
		try {
			hotel.adicionaOpiniao(null);
		} catch (NullPointerException e) {
			Assert.assertTrue(true);
		}

		Opiniao opiniao1 = new Opiniao("Muito bom", 7);
		Opiniao opiniao2 = new Opiniao("Razoavel", 4);

		hotel.adicionaOpiniao(opiniao1);
		Assert.assertTrue(hotel.getOpinioes().contains(opiniao1));
		Assert.assertFalse(hotel.getOpinioes().contains(opiniao2));

		hotel.adicionaOpiniao(opiniao2);
		Assert.assertTrue(hotel.getOpinioes().contains(opiniao2));
	}

	@Test
	public void testaGetQuartosDesocupados()
			throws LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, ContratoFechadoException,
			ContratoSemOpiniaoException {
		hotel.removeContrato(contrato1);
		hotel.removeContrato(contrato2);
		hotel.removeContrato(contrato3);

		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.EXECUTIVO_SIMPLES
						.ordinal()), 5);
		Assert.assertEquals(hotel
				.getQuartosDesocupados(IndexQuartos.EXECUTIVO_DUPLO.ordinal()),
				15);
		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.EXECUTIVO_TRIPLO
						.ordinal()), 20);
		Assert.assertEquals(hotel
				.getQuartosDesocupados(IndexQuartos.LUXO_SIMPLES.ordinal()), 5);
		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.LUXO_DUPLO.ordinal()),
				15);
		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.LUXO_TRIPLO.ordinal()),
				20);
		Assert.assertEquals(hotel
				.getQuartosDesocupados(IndexQuartos.SUITE_PRESIDENCIAL
						.ordinal()), 5);

		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[5, 15, 20, 5, 15, 20, 5]");

		hotel.adicionaContrato(contrato1);
		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.EXECUTIVO_SIMPLES
						.ordinal()), 4);
		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[4, 15, 20, 5, 15, 20, 5]");

		hotel.adicionaContrato(contrato2);
		Assert.assertEquals(hotel
				.getQuartosDesocupados(IndexQuartos.LUXO_SIMPLES.ordinal()), 4);
		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[4, 15, 20, 4, 15, 20, 5]");

		hotel.adicionaContrato(contrato3);
		Assert.assertEquals(hotel
				.getQuartosDesocupados(IndexQuartos.SUITE_PRESIDENCIAL
						.ordinal()), 4);
		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[4, 15, 20, 4, 15, 20, 4]");

		hotel.adicionaContrato(contrato5);
		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.EXECUTIVO_TRIPLO
						.ordinal()), 19);
		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[4, 15, 19, 4, 15, 20, 4]");

		hotel.removeContrato(contrato5);
		Assert.assertEquals(
				hotel.getQuartosDesocupados(IndexQuartos.EXECUTIVO_TRIPLO
						.ordinal()), 20);
		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[4, 15, 20, 4, 15, 20, 4]");

		hotel.removeContrato(contrato1);
		hotel.removeContrato(contrato2);
		hotel.removeContrato(contrato3);
		Assert.assertEquals(
				Arrays.toString(hotel.getArrayQuartosDesocupados()),
				"[5, 15, 20, 5, 15, 20, 5]");
	}

	@Test
	public void testaGetEstatisticaServicos()
			throws AddQuartoContratoException, NullPointerException,
			ServicoInvalidoException, DataInvalidaException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, ValorNegativoException,
			ContratoFechadoException, ContratoSemOpiniaoException {
		contrato4
				.adicionaServico(new Carro(TipoCarro.LUXO,
						new GregorianCalendar(2016, Calendar.OCTOBER, 15),
						new GregorianCalendar(2016, Calendar.OCTOBER, 18),
						false, true));
		contrato4.adicionaServico(new Baba(new GregorianCalendar(2016,
				Calendar.OCTOBER, 15), new GregorianCalendar(2016,
				Calendar.OCTOBER, 18)));
		hotel.adicionaContrato(contrato4);
		Assert.assertEquals(
				Arrays.toString(hotel.getEstatisticaOutrosServicos()),
				"[1, 1, 0]");
		hotel.removeContrato(contrato4);
		Assert.assertEquals(
				Arrays.toString(hotel.getEstatisticaOutrosServicos()),
				"[1, 1, 0]");

		contrato8.adicionaServico(new Carro(TipoCarro.EXECUTIVO,
				new GregorianCalendar(anoAtual, mesAtual, diaAtual + 1),
				new GregorianCalendar(anoAtual, mesAtual, diaAtual + 2), false,
				true));
		contrato8.adicionaServico(new Baba(new GregorianCalendar(anoAtual,
				mesAtual, diaAtual + 1), new GregorianCalendar(anoAtual,
				mesAtual, diaAtual + 2)));
		contrato8.adicionaServico(new ContaRestaurante(500));

		hotel.adicionaContrato(contrato8);

		Assert.assertEquals(
				Arrays.toString(hotel.getEstatisticaOutrosServicos()),
				"[2, 2, 1]");

		hotel.removeContrato(contrato8);
		Assert.assertEquals(
				Arrays.toString(hotel.getEstatisticaOutrosServicos()),
				"[2, 2, 1]");
	}

	@Test
	public void testaGetEstatisticaServicosPorMes()
			throws AddQuartoContratoException, NullPointerException,
			ServicoInvalidoException, DataInvalidaException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException, ValorNegativoException,
			ContratoFechadoException, ContratoSemOpiniaoException {
		try {
			hotel.getEstatisticaOutrosServicos(0);
		} catch (MesInvalidoException e) {
			Assert.assertTrue(true);
		}
		try {
			hotel.getEstatisticaOutrosServicos(13);
		} catch (MesInvalidoException e) {
			Assert.assertTrue(true);
		}

		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaOutrosServicos(Calendar.NOVEMBER + 1)),
				"[0.0, 0.0, 0.0]");
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaOutrosServicos(Calendar.JULY + 1)),
				"[0.0, 0.0, 0.0]");
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaOutrosServicos(Calendar.APRIL + 1)),
				"[0.0, 0.0, 0.0]");

		contrato8.adicionaServico(new Carro(TipoCarro.EXECUTIVO,
				new GregorianCalendar(anoAtual, mesAtual, diaAtual + 1),
				new GregorianCalendar(anoAtual, mesAtual, diaAtual + 2), false,
				false));

		contrato8.adicionaServico(new Baba(new GregorianCalendar(anoAtual,
				mesAtual, diaAtual + 1), new GregorianCalendar(anoAtual,
				mesAtual, diaAtual + 2)));

		contrato8.adicionaServico(new ContaRestaurante(200));

		hotel.adicionaContrato(contrato8);
		Assert.assertEquals(Arrays.toString(hotel
				.getEstatisticaOutrosServicos(mesAtual + 1)), "[1.0, 1.0, 1.0]");

		hotel.removeContrato(contrato8);
		System.out.println(Arrays.toString(hotel
				.getEstatisticaOutrosServicos(mesAtual + 1)));
		// Assert.assertEquals(Arrays.toString(hotel
		// .getEstatisticaOutrosServicos(mesAtual + 1)), "[1.0, 1.0, 1.0]");
	}
}
