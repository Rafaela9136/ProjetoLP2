package hotel;

import java.util.*;

import excecoes.ExecutivosDuploOcupadosException;
import excecoes.ExecutivosSimplesOcupadosException;
import excecoes.ExecutivosTriploOcupadosException;
import excecoes.LuxosDuploOcupadosException;
import excecoes.LuxosSimplesOcupadosException;
import excecoes.LuxosTriploOcupadosException;
import excecoes.SuitesPresidenciaisOcupadasException;

public class Hotel {

	private static int numExecSimplesDesocupados = 5;
	private static int numExecDuploDesocupados = 15;
	private static int numExecTriploDesocupados = 20;

	private static int numLuxoSimplesDesocupados = 5;
	private static int numLuxoDuploDesocupados = 15;
	private static int numLuxoTriploDesocupados = 20;

	private static int numPresidencialDesocupados = 5;

	private static List<Contrato> contratosHotel = new ArrayList<Contrato>();

	public static void adicionaContrato(Contrato contrato)
			throws ExecutivosDuploOcupadosException,
			SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosTriploOcupadosException {
		atualizaQuantQuartosParaContratosNovos(contrato);
		
		contratosHotel.add(contrato);
	}// adicionaContrato


	public static List<Contrato> getContratos() {
		return contratosHotel;
	}// getContratos

	public static List<Contrato> pesquisaContrato(String text) {
		List<Contrato> contratosEncontrados = new ArrayList<Contrato>();

		for (Contrato contrato : contratosHotel) {
			if (contrato.getHospedeTitular().getNome().equals(text))
				contratosEncontrados.add(contrato);
			for (String acompanhante : contrato.getAcompanhantes()) {
				if (acompanhante.equals(text))
					contratosEncontrados.add(contrato);
			}// for
		}// for
		return contratosEncontrados;
	}// pesquisaContrato

	public static boolean removeContrato(Contrato contrato) {
		atualizaQuantQuartosParaContratosVelhos(contrato);
		return contratosHotel.remove(contrato);
	}// removeContrato
	
	private static void atualizaQuantQuartosParaContratosVelhos(Contrato contrato) {
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
					quantPresidenciais++;
				} else if (servico instanceof QuartoLuxo) {
					QuartoLuxo quarto = (QuartoLuxo) servico;
					if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantLuxoSimples++;
					} else if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.DUPLO)) {
					quantLuxoDuplo++;
					} else {
						quantLuxoTriplo++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else
				} else {
					QuartoExecutivo quarto  = (QuartoExecutivo) servico;
					if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						quantExecutivoSimples++;
					} else if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.DUPLO)) {
						quantExecutivoDuplo++;
					} else {
						quantExecutivoTriplo++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))
						
				}// if (servico instanceof SuitePresidencial)-else

			}// if(servico instanceof Quarto)
		}// for
		
		numPresidencialDesocupados += quantPresidenciais;
		
		numExecSimplesDesocupados += quantExecutivoSimples;
		numExecDuploDesocupados += quantExecutivoDuplo;
		numExecTriploDesocupados += quantExecutivoTriplo;
		
		numLuxoSimplesDesocupados += quantLuxoSimples;
		numLuxoDuploDesocupados += quantLuxoDuplo;
		numLuxoTriploDesocupados += quantLuxoTriplo;
	}
	
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
					} else if (quarto.getTipoDeQuarto().equals(TiposDeQuarto.DUPLO)) {
						if (numLuxoDuploDesocupados == 0)
							throw new LuxosDuploOcupadosException();
					quantLuxoDuplo++;
					} else {
						if(numLuxoTriploDesocupados == 0)
							throw new LuxosTriploOcupadosException();
						quantLuxoTriplo++;
					}// if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES))-else
				} else {
					QuartoExecutivo quarto  = (QuartoExecutivo) servico;
					if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.SIMPLES)) {
						if(numExecSimplesDesocupados == 0)
							throw new ExecutivosSimplesOcupadosException();
						quantExecutivoSimples++;
					} else if(quarto.getTipoDeQuarto().equals(TiposDeQuarto.DUPLO)) {
						if(numExecDuploDesocupados == 0)
							throw new ExecutivosDuploOcupadosException();
						quantExecutivoDuplo++;
					} else {
						if(numExecTriploDesocupados == 0)
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
	
}// Hotel
