package hotel;

import java.util.*;

import excecoes.ExecutivosDuploOcupadosException;
import excecoes.ExecutivosSimplesOcupadosException;
import excecoes.ExecutivosTriploOcupadosException;
import excecoes.LuxosDuploOcupadosException;
import excecoes.LuxosSimplesOcupadosException;
import excecoes.LuxosTriploOcupadosException;
import excecoes.SuitesPresidenciaisOcupadasException;

import java.io.*;

public class Hotel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5360355918182446792L;
	private List<Contrato> contratos;
	private int[] quantQuartosDesocupados;
	
	public Hotel(List<Contrato> contratos, int[] quantQuartosDesocupados) throws NullPointerException {
		if(contratos == null || quantQuartosDesocupados == null)
			throw new NullPointerException();
		
		this.contratos = contratos;
		this.quantQuartosDesocupados = quantQuartosDesocupados;
	}// Construtor
	
	@Override
	public String toString() {
		return "Hotel";
	}

	public void adicionaContrato(Contrato contrato)
			throws ExecutivosDuploOcupadosException,
			SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException {
		atualizaQuantQuartosParaContratosNovos(contrato);
		contratos.add(contrato);

	}// adicionaContrato

	public List<Opiniao> getOpinioesOrdenadas() throws FileNotFoundException,
			ClassNotFoundException, IOException {
		List<Opiniao> opinioes = new ArrayList<Opiniao>();

		for (Contrato contrato : contratos)
			opinioes.add(contrato.getOpiniao());
		opinioes.sort(new OpiniaoComparator());
		return opinioes;
	}// getOpinioesComMaioresNotas

	public boolean removeContrato(Contrato contrato) {
		atualizaQuantQuartosParaContratosVelhos(contrato);
		return contratos.remove(contrato);
	}// removeContrato

	public List<Contrato> getContratos() {
		return contratos;
	}// getContratos

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

	private void atualizaQuantQuartosParaContratosVelhos(Contrato contrato) {

		int[] quantASerSomada = new int[7];
		quantASerSomada = quantidadeASerRetirada(contrato);

		for (int i = 0; i < quantQuartosDesocupados.length; i++)
			quantQuartosDesocupados[i] += quantASerSomada[i];

	}// atualizaQuantQuartosParaContratosVelhos

	private int[] quantidadeASerRetirada(Contrato contrato) {
		int[] quantASerRetirada = new int[7];

		for (int i = 0; i < quantASerRetirada.length; i++)
			quantASerRetirada[i] = 0;

		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Quarto) {
				if (servico instanceof SuitePresidencial) {
					quantASerRetirada[IndexQuartosDesocupados.SUITE_PRESIDENCIAL
							.ordinal()]++;

				} else if (servico instanceof QuartoLuxo) {
					QuartoLuxo quarto = (QuartoLuxo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantASerRetirada[IndexQuartosDesocupados.LUXO_SIMPLES
								.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantASerRetirada[IndexQuartosDesocupados.LUXO_DUPLO
								.ordinal()]++;
					} else {
						quantASerRetirada[IndexQuartosDesocupados.LUXO_TRIPLO
								.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else

				} else {
					QuartoExecutivo quarto = (QuartoExecutivo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantASerRetirada[IndexQuartosDesocupados.EXECUTIVO_SIMPLES
								.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantASerRetirada[IndexQuartosDesocupados.EXECUTIVO_DUPLO
								.ordinal()]++;
					} else {
						quantASerRetirada[IndexQuartosDesocupados.EXECUTIVO_TRIPLO
								.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))

				}// if (servico instanceof SuitePresidencial)-else

			}// if(servico instanceof Quarto)
		}// for
		return quantASerRetirada;
	}// quantASerRetirada

	private void atualizaQuantQuartosParaContratosNovos(Contrato contrato)
			throws SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException, ExecutivosTriploOcupadosException {

		int[] quantASerRetirada = new int[7];
		quantASerRetirada = quantidadeASerRetirada(contrato);

		for (int i = 0; i < quantQuartosDesocupados.length; i++)
			quantQuartosDesocupados[i] -= quantASerRetirada[i];

	}// atualizaQuantQuartosParaContratosNovos

	/*
	 * para cria�ao dos arquivos
	 */
	// public static void main(String[] args) throws FileNotFoundException,
	// IOException, ClassNotFoundException {
	//
	// int[] quartosDesocupados = new int[7];
	//
	// quartosDesocupados[0] = 5;
	// quartosDesocupados[1] = 15;
	// quartosDesocupados[2] = 20;
	// quartosDesocupados[3] = 5;
	// quartosDesocupados[4] = 15;
	// quartosDesocupados[5] = 20;
	// quartosDesocupados[6] = 5;
	// outputObjetos(quartosDesocupados, PATH_QUARTOS_DESOCUPADOS);
	//
	// List<Contrato> contratos = new ArrayList<Contrato>();
	// outputObjetos(contratos, PATH_CONTRATOS);
	//
	// List<Opiniao> opinioes = new ArrayList<Opiniao>();
	// outputObjetos(opinioes, PATH_OPINIOES);
	//
	// System.out.println("Gravados com sucesso");
	// quartosDesocupados = (int[]) InputObjetos(PATH_QUARTOS_DESOCUPADOS);
	//
	// for (int i = 0; i < quartosDesocupados.length; i++) {
	// System.out.println(quartosDesocupados[i]);
	// }
	// }// main

}// Hotel
