package hotel;

import java.util.*;

import excecoes.ExecutivosDuploOcupadosException;
import excecoes.ExecutivosSimplesOcupadosException;
import excecoes.ExecutivosTriploOcupadosException;
import excecoes.LoginInvalidoException;
import excecoes.LuxosDuploOcupadosException;
import excecoes.LuxosSimplesOcupadosException;
import excecoes.LuxosTriploOcupadosException;
import excecoes.MesInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SuitesPresidenciaisOcupadasException;

import java.io.*;

public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5360355918182446792L;
	private List<Contrato> contratos;
	private int[] quartosDesocupados;
	private List<Opiniao> opinioes;
	private List<Contrato> contratosRemovidos;
	private List<Conta> contasHotel;

	private static final int QUANT_TIPOS_DE_QUARTOS = 7;
	private static final int QUANT_OUTROS_SERVICOS = 3;

	public Hotel(List<Contrato> contratos, int[] quartosDesocupados,
			List<Opiniao> opinioes) throws NullPointerException {
		if (contratos == null || quartosDesocupados == null || opinioes == null)
			throw new NullPointerException();

		this.contratos = contratos;
		this.quartosDesocupados = quartosDesocupados;
		this.opinioes = opinioes;
		contratosRemovidos = new ArrayList<Contrato>();
		contasHotel = new ArrayList<Conta>();
	}// Construtor

	public void adicionaContrato(Contrato contrato)
			throws ExecutivosDuploOcupadosException,
			SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException {
		quartosDesocupados = atualizaQuantQuartosParaContratosNovos(contrato);
		contratos.add(contrato);

	}// adicionaContrato

	public List<Opiniao> getOpinioesOrdenadas() {
		// opinioes.sort(new OpiniaoComparator());
		return opinioes;
	}// getOpinioesComMaioresNotas

	public boolean removeContrato(Contrato contrato) {
		boolean saida = contratos.remove(contrato);
		if (saida) {
			opinioes.add(contrato.getOpiniao());
			contratosRemovidos.add(contrato);
			quartosDesocupados = atualizaQuantQuartosParaContratosVelhos(contrato);
		}
		return saida;
	}// removeContrato

	public void adicionaConta(Conta contaNova) throws LoginInvalidoException,
			NullPointerException, NomeCompletoInvalidoException {
		if (contaNova == null)
			throw new NullPointerException();

		for (Conta conta : contasHotel) {
			if (contaNova.getLogin().equals(conta))
				throw new LoginInvalidoException();

			if (contaNova.getNomeCompleto().trim()
					.equals(conta.getNomeCompleto().trim()))
				throw new NomeCompletoInvalidoException();
		}// for
		
		contasHotel.add(contaNova);
	}// adicionaConta
	
	public boolean removeConta(Conta conta) {
		return contasHotel.remove(conta);
	}//removeConta
	
	public boolean pesquisaConta(String login, String senha) throws NullPointerException {
		if(login == null || senha == null)
			throw new NullPointerException();
		for(Conta conta : contasHotel) 
			if(conta.getLogin().equals(login) || conta.getSenha().equals(senha))
				return true;
		
		return false;
	}// pesquisaConta

	public List<Contrato> getContratos() {
		return contratos;
	}// getContratos

	public List<Contrato> getContratosRemovidos() {
		return contratosRemovidos;
	}// getContratosRemovidos

	public List<Conta> getContasHotel() {
		return contasHotel;
	}// getContasHotele

	private boolean verificaMesEmPeriodoDeContrato(Calendar dataCheckIn,
			Calendar dataCheckOut, int mesIndice) {
		for (int i = dataCheckIn.get(Calendar.MONTH); i <= dataCheckOut
				.get(Calendar.MONTH); i++)
			if (i == mesIndice)
				return true;
		return false;
	}// verificaMesEmPeriodoDeContrato

	public double[] getEstatisticaQuartos(int mes) throws MesInvalidoException {
		if (mes < 1)
			throw new MesInvalidoException();
		mes--;
		double[] estatisticas = new double[QUANT_TIPOS_DE_QUARTOS];
		int[] quantQuartos;
		int quantidadeTotal = 0;
		for (Contrato contrato : contratos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantQuartos = quantidadeDeQuartos(contrato);
				for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
					estatisticas[i] += quantQuartos[i];
					quantidadeTotal += quantQuartos[i];
				}// for
			}// if
		}// for

		for (Contrato contrato : contratosRemovidos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantQuartos = quantidadeDeQuartos(contrato);
				for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
					estatisticas[i] += quantQuartos[i];
					quantidadeTotal += quantQuartos[i];
				}// for
			}// if
		}// for

		for (int i = 0; i < estatisticas.length; i++) {
			estatisticas[i] /= quantidadeTotal;
			estatisticas[i] *= 100;
		}// for

		return estatisticas;
	}// getEstatisticaMensalQuartos

	public double[] getEstatisticaQuartos() {
		double[] estatisticas = new double[QUANT_TIPOS_DE_QUARTOS];
		int[] quantQuartos;
		int quantidadeTotal = 0;
		for (Contrato contrato : contratos) {
			quantQuartos = quantidadeDeQuartos(contrato);
			for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
				estatisticas[i] += quantQuartos[i];
				quantidadeTotal += quantQuartos[i];
			}// for
		}// for

		for (Contrato contrato : contratosRemovidos) {
			quantQuartos = quantidadeDeQuartos(contrato);
			for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
				estatisticas[i] += quantQuartos[i];
				quantidadeTotal += quantQuartos[i];
			}// for
		}// for

		for (int i = 0; i < estatisticas.length; i++) {
			estatisticas[i] /= quantidadeTotal;
			estatisticas[i] *= 100;
		}// for
		return estatisticas;
	}// getEstatisticaGeralQuartos

	public double[] getEstatisticaOutrosServicos(int mes)
			throws MesInvalidoException {
		if (mes < 1)
			throw new MesInvalidoException();
		mes--;
		double[] estatisticas = new double[QUANT_OUTROS_SERVICOS];
		int[] quantOutrosServicos;
		int quantidadeTotal = 0;
		for (Contrato contrato : contratos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
				for (int i = 0; i < QUANT_OUTROS_SERVICOS; i++) {
					estatisticas[i] += quantOutrosServicos[i];
					quantidadeTotal += quantOutrosServicos[i];
				}// for
			}// if
		}// for

		for (Contrato contrato : contratosRemovidos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
				for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
					estatisticas[i] += quantOutrosServicos[i];
					quantidadeTotal += quantOutrosServicos[i];
				}// for
			}// if
		}// for

		for (int i = 0; i < estatisticas.length; i++) {
			estatisticas[i] /= quantidadeTotal;
			estatisticas[i] *= quantidadeTotal;
		}// for

		return estatisticas;

	}// getEstatisticaOutrosServicos

	public double[] getEstatisticaOutrosServicos() {
		double[] estatisticas = new double[QUANT_OUTROS_SERVICOS];
		int[] quantOutrosServicos;
		int quantidadeTotal = 0;
		for (Contrato contrato : contratos) {
			quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
			for (int i = 0; i < QUANT_OUTROS_SERVICOS; i++) {
				estatisticas[i] += quantOutrosServicos[i];
				quantidadeTotal += quantOutrosServicos[i];
			}// for
		}// for

		for (Contrato contrato : contratosRemovidos) {
			quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
			for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
				estatisticas[i] += quantOutrosServicos[i];
				quantidadeTotal += quantOutrosServicos[i];
			}// for
		}// for

		for (int i = 0; i < estatisticas.length; i++) {
			estatisticas[i] /= quantidadeTotal;
			estatisticas[i] *= quantidadeTotal;
		}// for

		return estatisticas;
	}// getEstatisticaGeralOutrosServicos

	public int[] quantidadeDeOutrosServicos(Contrato contrato) {
		int[] servicos = new int[QUANT_OUTROS_SERVICOS];
		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Baba)
				servicos[IndexOutrosServicos.BABA.ordinal()]++;
			else if (servico instanceof Carro)
				servicos[IndexOutrosServicos.CARRO.ordinal()]++;
			else
				servicos[IndexOutrosServicos.CONTA_RESTAURANTE.ordinal()]++;
		}// for
		return servicos;
	}// quantidadeOutrosServicos

	public List<Contrato> pesquisaContrato(String text) {
		List<Contrato> contratosEncontrados = new ArrayList<Contrato>();

		for (Contrato contrato : getContratos()) {
			if (contrato.getHospedeTitular().getNome().equals(text))
				contratosEncontrados.add(contrato);
			for (String acompanhante : contrato.getAcompanhantes()) {
				if (acompanhante.equals(text))
					contratosEncontrados.add(contrato);
			}// for
		}// for
		return contratosEncontrados;
	}// pesquisaContrato

	private int[] atualizaQuantQuartosParaContratosVelhos(Contrato contrato) {

		int[] quantASerSomada = new int[QUANT_TIPOS_DE_QUARTOS];
		quantASerSomada = quantidadeDeQuartos(contrato);

		int[] quartosDesocupados = new int[QUANT_TIPOS_DE_QUARTOS];

		for (int i = 0; i < quartosDesocupados.length; i++)
			quartosDesocupados[i] = 0;

		for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++)
			quartosDesocupados[i] += quantASerSomada[i];
		return quartosDesocupados;
	}// atualizaQuantQuartosParaContratosVelhos

	private int[] quantidadeDeQuartos(Contrato contrato) {
		int[] quantidade = new int[QUANT_TIPOS_DE_QUARTOS];

		for (int i = 0; i < quantidade.length; i++)
			quantidade[i] = 0;

		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Quarto) {
				if (servico instanceof SuitePresidencial) {
					quantidade[IndexQuartos.SUITE_PRESIDENCIAL.ordinal()]++;

				} else if (servico instanceof QuartoLuxo) {
					QuartoLuxo quarto = (QuartoLuxo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantidade[IndexQuartos.LUXO_SIMPLES.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantidade[IndexQuartos.LUXO_DUPLO.ordinal()]++;
					} else {
						quantidade[IndexQuartos.LUXO_TRIPLO.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else

				} else {
					QuartoExecutivo quarto = (QuartoExecutivo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantidade[IndexQuartos.EXECUTIVO_SIMPLES.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantidade[IndexQuartos.EXECUTIVO_DUPLO.ordinal()]++;
					} else {
						quantidade[IndexQuartos.EXECUTIVO_TRIPLO.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))

				}// if (servico instanceof SuitePresidencial)-else

			}// if(servico instanceof Quarto)
		}// for
		return quantidade;
	}// quantASerRetirada

	private int[] atualizaQuantQuartosParaContratosNovos(Contrato contrato)
			throws SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException, ExecutivosTriploOcupadosException {

		int[] quantASerRetirada = new int[QUANT_TIPOS_DE_QUARTOS];
		quantASerRetirada = quantidadeDeQuartos(contrato);
		int[] quartosDesocupados = new int[QUANT_TIPOS_DE_QUARTOS];

		for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++)
			quartosDesocupados[i] = 0;

		for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++)
			quartosDesocupados[i] -= quantASerRetirada[i];
		return quartosDesocupados;
	}// atualizaQuantQuartosParaContratosNovos

	public int getQuartosDesocupados(int indice) {
		return quartosDesocupados[indice];
	}

	@Override
	public String toString() {
		return "Hotel";
	}

	/*
	 * para criacao dos arquivos
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException {

		int[] quartosDesocupados = new int[7];

		quartosDesocupados[0] = 5;
		quartosDesocupados[1] = 15;
		quartosDesocupados[2] = 20;
		quartosDesocupados[3] = 5;
		quartosDesocupados[4] = 15;
		quartosDesocupados[5] = 20;
		quartosDesocupados[6] = 5;

		List<Contrato> contratos = new ArrayList<Contrato>();

		List<Opiniao> opinioes = new ArrayList<Opiniao>();

		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("hotel.dat")));
		out.writeObject(new Hotel(contratos, quartosDesocupados, opinioes));
		out.close();
		System.out.println("Gravados com sucesso");

		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream("hotel.dat")));
		Hotel hotel1 = (Hotel) in.readObject();
		in.close();

		for (int i = 0; i < quartosDesocupados.length; i++) {
			System.out.println(hotel1.toString());
			System.out.println(hotel1.getContratos().size());
			System.out.println(hotel1.getQuartosDesocupados(i));
		}
	}// main

}// Hotel
