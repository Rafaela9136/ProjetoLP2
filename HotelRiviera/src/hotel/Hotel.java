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
	private int[] quartosDesocupados;
	private List<Opiniao> opinioes;
	private List<Contrato> contratosRemovidos;
	
	public Hotel(List<Contrato> contratos, int[] quartosDesocupados, List<Opiniao> opinioes) throws NullPointerException {
		if(contratos == null || quartosDesocupados == null || opinioes == null)
			throw new NullPointerException();
		
		this.contratos = contratos;
		this.quartosDesocupados = quartosDesocupados;
		this.opinioes = opinioes;
		contratosRemovidos = new ArrayList<Contrato>();
	}// Construtor
	
	public void adicionaContrato(Contrato contrato)
			throws ExecutivosDuploOcupadosException,
			SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException {
		atualizaQuantQuartosParaContratosNovos(contrato);
		contratos.add(contrato);

	}// adicionaContrato

	public List<Opiniao> getOpinioesOrdenadas() {
//		opinioes.sort(new OpiniaoComparator());
		return opinioes;
	}// getOpinioesComMaioresNotas

	public boolean removeContrato(Contrato contrato) {
		atualizaQuantQuartosParaContratosVelhos(contrato);
		boolean saida = contratos.remove(contrato);
		if(saida) {
			opinioes.add(contrato.getOpiniao());
			contratosRemovidos.add(contrato);
		}
		return saida;
	}// removeContrato

	public List<Contrato> getContratos() {
		return contratos;
	}// getContratos
	
	public List<Contrato> getContratosRemovidos() {
		return contratosRemovidos;
	}// getContratosRemovidos

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
		quantASerSomada = quantidadeDeQuartos(contrato);

		for (int i = 0; i < quartosDesocupados.length; i++)
			quartosDesocupados[i] += quantASerSomada[i];

	}// atualizaQuantQuartosParaContratosVelhos

	private int[] quantidadeDeQuartos(Contrato contrato) {
		int[] quantASerRetirada = new int[7];

		for (int i = 0; i < quantASerRetirada.length; i++)
			quantASerRetirada[i] = 0;

		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Quarto) {
				if (servico instanceof SuitePresidencial) {
					quantASerRetirada[IndexQuartos.SUITE_PRESIDENCIAL
							.ordinal()]++;

				} else if (servico instanceof QuartoLuxo) {
					QuartoLuxo quarto = (QuartoLuxo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantASerRetirada[IndexQuartos.LUXO_SIMPLES
								.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantASerRetirada[IndexQuartos.LUXO_DUPLO
								.ordinal()]++;
					} else {
						quantASerRetirada[IndexQuartos.LUXO_TRIPLO
								.ordinal()]++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else

				} else {
					QuartoExecutivo quarto = (QuartoExecutivo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantASerRetirada[IndexQuartos.EXECUTIVO_SIMPLES
								.ordinal()]++;
					} else if (quarto.getTipoDeQuarto().equals(
							TiposDeQuarto.DUPLO)) {
						quantASerRetirada[IndexQuartos.EXECUTIVO_DUPLO
								.ordinal()]++;
					} else {
						quantASerRetirada[IndexQuartos.EXECUTIVO_TRIPLO
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
		quantASerRetirada = quantidadeDeQuartos(contrato);

		for (int i = 0; i < quartosDesocupados.length; i++)
			quartosDesocupados[i] -= quantASerRetirada[i];

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
	 
 
	ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("hotel.dat")));
	out.writeObject(new Hotel(contratos, quartosDesocupados, opinioes));
	 out.close();
	 System.out.println("Gravados com sucesso");
	 
	 ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("hotel.dat")));
	 Hotel hotel1 = (Hotel) in.readObject();
	 in.close();
	
	 for (int i = 0; i < quartosDesocupados.length; i++) {
	 System.out.println(hotel1.toString());
	 System.out.println(hotel1.getContratos().size());
	 System.out.println(hotel1.getQuartosDesocupados(i));
	 }
	 }// main

}// Hotel
