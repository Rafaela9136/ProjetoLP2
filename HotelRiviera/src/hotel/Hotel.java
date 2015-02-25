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

public class Hotel {

	private static int numExecSimplesDesocupados = 5;
	private static int numExecDuploDesocupados = 15;
	private static int numExecTriploDesocupados = 20;

	private static int numLuxoSimplesDesocupados = 5;
	private static int numLuxoDuploDesocupados = 15;
	private static int numLuxoTriploDesocupados = 20;

	private static int numPresidencialDesocupados = 5;

	private static final String PATH_CONTRATOS = "contratosHotel.dat";

	private static final String PATH_QUARTOS_DESOCUPADOS = "quantQuartosDesocupados.dat";

	public static void adicionaContrato(Contrato contrato)
			throws ExecutivosDuploOcupadosException,
			SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException, FileNotFoundException,
			ClassNotFoundException, IOException {

		atualizaQuantQuartosParaContratosNovos(contrato);
		List<Contrato> contratos = (List<Contrato>) InputObjetos(PATH_CONTRATOS);
		contratos.add(contrato);
		outputObjetos(contratos, PATH_CONTRATOS);

	}// adicionaContrato

	public static boolean removeContrato(Contrato contrato)
			throws FileNotFoundException, ClassNotFoundException, IOException {
		atualizaQuantQuartosParaContratosVelhos(contrato);
		List<Contrato> contratos = (List<Contrato>) InputObjetos(PATH_CONTRATOS);
		boolean saida = contratos.remove(contrato);
		outputObjetos(contratos, PATH_CONTRATOS);
		return saida;
	}// removeContrato

	public static List<Contrato> getContratos() throws FileNotFoundException,
			IOException, ClassNotFoundException {
		return (List<Contrato>) InputObjetos(PATH_CONTRATOS);
	}// getContratos

	public static List<Contrato> pesquisaContrato(String text)
			throws FileNotFoundException, ClassNotFoundException, IOException {
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

	private static Object InputObjetos(String pathArquivo)
			throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream inputArquivo = null;
		Object obj = null;
		try {
			inputArquivo = new ObjectInputStream(new BufferedInputStream(
					new FileInputStream(pathArquivo)));
			obj = (Object) inputArquivo.readObject();
		} finally {
			if (inputArquivo != null)
				inputArquivo.close();
		}
		return obj;
	}// inputContratos

	private static void outputObjetos(Object obj, String pathArquivo)
			throws FileNotFoundException, IOException {
		ObjectOutputStream outputArquivo = null;
		try {
			outputArquivo = new ObjectOutputStream(new BufferedOutputStream(
					new FileOutputStream(pathArquivo)));
			outputArquivo.writeObject(obj);
		} finally {
			if (outputArquivo != null)
				outputArquivo.close();
		}// try-finally
	}// outputContratos

	private static void atualizaQuantQuartosParaContratosVelhos(
			Contrato contrato) throws FileNotFoundException, ClassNotFoundException, IOException {
		
		int[] quantASerRetirada = new int[7];
		
		for (int i = 0; i < quantASerRetirada.length; i++) {
			quantASerRetirada[i] = 0;
		}// for
		
		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Quarto) {
				if (servico instanceof SuitePresidencial) {
					quantASerRetirada[IndexQuartosDesocupados.SUITE_PRESIDENCIAL.ordinal()]++;
					
					
				} else if (servico instanceof QuartoLuxo) {
					QuartoLuxo quarto = (QuartoLuxo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantASerRetirada[IndexQuartosDesocupados.LUXO_SIMPLES.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantASerRetirada[IndexQuartosDesocupados.LUXO_DUPLO.ordinal()]++;
					} else {
						quantASerRetirada[IndexQuartosDesocupados.LUXO_TRIPLO.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else
					
					
				} else {
					QuartoExecutivo quarto = (QuartoExecutivo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantASerRetirada[IndexQuartosDesocupados.EXECUTIVO_SIMPLES.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantASerRetirada[IndexQuartosDesocupados.EXECUTIVO_DUPLO.ordinal()]++;
					} else {
						quantASerRetirada[IndexQuartosDesocupados.EXECUTIVO_TRIPLO.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))

					
					
				}// if (servico instanceof SuitePresidencial)-else

			}// if(servico instanceof Quarto)
		}// for
		
		int[] quantQuartosDesocupados = (int[]) InputObjetos(PATH_QUARTOS_DESOCUPADOS);
		
				
		for (int i = 0; i < quantQuartosDesocupados.length; i++) 
			quantQuartosDesocupados[i] += quantASerRetirada[i];
		
		outputObjetos(quantQuartosDesocupados, PATH_QUARTOS_DESOCUPADOS);
		
	}// atualizaQuantQuartosParaContratosVelhos

	private static void atualizaQuantQuartosParaContratosNovos(Contrato contrato)
			throws SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException, ExecutivosTriploOcupadosException {
		int quantPresidenciais = 0;

		int quantLuxoSimples = 0;
		int quantLuxoDuplo = 0;
		int quantLuxoTriplo = 0;

		int quantExecutivoSimples = 0;
		int quantExecutivoDuplo = 0;
		int quantExecutivoTriplo = 0;
		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Quarto) {
				if (servico instanceof SuitePresidencial) {
					if (numPresidencialDesocupados == 0)
						throw new SuitesPresidenciaisOcupadasException();
					quantPresidenciais++;
				} else if (servico instanceof QuartoLuxo) {
					QuartoLuxo quarto = (QuartoLuxo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						if (numLuxoSimplesDesocupados == 0)
							throw new LuxosSimplesOcupadosException();
						quantLuxoSimples++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						if (numLuxoDuploDesocupados == 0)
							throw new LuxosDuploOcupadosException();
						quantLuxoDuplo++;
					} else {
						if (numLuxoTriploDesocupados == 0)
							throw new LuxosTriploOcupadosException();
						quantLuxoTriplo++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else
				} else {
					QuartoExecutivo quarto = (QuartoExecutivo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						if (numExecSimplesDesocupados == 0)
							throw new ExecutivosSimplesOcupadosException();
						quantExecutivoSimples++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						if (numExecDuploDesocupados == 0)
							throw new ExecutivosDuploOcupadosException();
						quantExecutivoDuplo++;
					} else {
						if (numExecTriploDesocupados == 0)
							throw new ExecutivosTriploOcupadosException();
						quantExecutivoTriplo++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))

				}// if (servico instanceof SuitePresidencial)-else

			}// if(servico instanceof Quarto)
		}// for

		numPresidencialDesocupados -= quantPresidenciais;

		numExecSimplesDesocupados -= quantExecutivoSimples;
		numExecDuploDesocupados -= quantExecutivoDuplo;
		numExecTriploDesocupados -= quantExecutivoTriplo;

		numLuxoSimplesDesocupados -= quantLuxoSimples;
		numLuxoDuploDesocupados -= quantLuxoDuplo;
		numLuxoTriploDesocupados -= quantLuxoTriplo;
	}// atualizaQuantidadeDeQuartos

	/*
	 * para criaçao dos arquivos
	 */
	// public static void main(String[] args) throws FileNotFoundException,
	// IOException, ClassNotFoundException {
	// quartosDesocupados[0] = 5;
	// quartosDesocupados[1] = 15;
	// quartosDesocupados[2] = 20;
	// quartosDesocupados[3] = 5;
	// quartosDesocupados[4] = 15;
	// quartosDesocupados[5] = 20;
	// quartosDesocupados[6] = 5;
	// outputObjetos(quartosDesocupados, PATH_QUARTOS_DESOCUPADOS);
	// List<Contrato> contratos = new ArrayList<Contrato>();
	// outputObjetos(contratos, PATH_CONTRATOS);
	// System.out.println("Gravados com sucesso");
	// int[] quartosDesocupados = (int[])
	// InputObjetos(PATH_QUARTOS_DESOCUPADOS);
	//
	// for (int i = 0; i < quartosDesocupados.length; i++) {
	// System.out.println(quartosDesocupados[i]);
	// }
	// }

}// Hotel
