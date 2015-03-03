package hotel;

import java.util.*;

import excecoes.ExecutivosDuploOcupadosException;
import excecoes.ExecutivosSimplesOcupadosException;
import excecoes.ExecutivosTriploOcupadosException;
import excecoes.ListaVaziaException;
import excecoes.LoginInvalidoException;
import excecoes.LuxosDuploOcupadosException;
import excecoes.LuxosSimplesOcupadosException;
import excecoes.LuxosTriploOcupadosException;
import excecoes.MesInvalidoException;
import excecoes.NomeCompletoInvalidoException;
import excecoes.SenhaInvalidaException;
import excecoes.SuitesPresidenciaisOcupadasException;
import excecoes.LoginExistenteException;

import java.io.*;

/**
 * A classe Hotel guarda informa��es sobre contratos, quartos dispon�veis,
 * opini�es de clientes e login dos funcion�rios do hotel Rivieira
 * 
 */
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

	/**
	 * Cria um objeto do tipo Hotel.
	 * 
	 * @param contratos
	 *            Uma lista com os contratos em vigor.
	 * @param quartosDesocupados
	 *            Um array de int que informa a quantia de quartos desocupados
	 *            dentre cada um dos quartos dispon�veis.
	 * @param opinioes
	 *            Uma lista com as Opinioes dos clientes sobre o hotel.
	 * @throws NullPointerException
	 *             Se qualquer um dos parametros for null.
	 * @throws LoginInvalidoException
	 *             Se o login contem espacos em branco ou se for menor que o
	 *             TAMANHO_MINIMO_LOGIN, que e 6.
	 * @throws SenhaInvalidaException
	 *             Se a senha contem espacos em branco ou se for menor que o
	 *             TAMANHO_MINIMO_SENHA, que e 6.
	 * @throws NomeCompletoInvalidoException
	 *             Se o nome completo nao contem nenhum espaco.
	 */
	public Hotel() throws LoginInvalidoException, NullPointerException,
			SenhaInvalidaException, NomeCompletoInvalidoException {
		contratos = new ArrayList<Contrato>();
		contratosRemovidos = new ArrayList<Contrato>();
		opinioes = new ArrayList<Opiniao>();
		contasHotel = new ArrayList<Conta>();

		contasHotel.add(new Conta("gerente", "euamorafael", "Rafael Klynger",
				TipoFuncionario.GERENTE));

		IndexQuartos[] index = IndexQuartos.values();
		quartosDesocupados = new int[QUANT_TIPOS_DE_QUARTOS];
		for (int i = 0; i < index.length; i++) {
			quartosDesocupados[i] = index[i].getQuantidade();
		}// for
	}// Construtor

	/**
	 * Adiciona um novo contrato a lista de contratos.
	 * 
	 * @param contrato
	 *            O novo contrato a ser adicionado.
	 * @throws ExecutivosDuploOcupadosException
	 *             Se todos os quartos executivo duplo estiverem ocupados e for
	 *             requisitado um novo quarto para o contrato.
	 * @throws SuitesPresidenciaisOcupadasException
	 *             Se todos as suites presidenciais estiverem ocupadas e for
	 *             requisitada uma nova suite para o contrato.
	 * @throws LuxosSimplesOcupadosException
	 *             Se todos os luxo simples estiverem ocupados e for requisitado
	 *             um novo quarto para o contrato.
	 * @throws LuxosDuploOcupadosException
	 *             Se todos os quartos luxo duplo estiverem ocupados e for
	 *             requisitado um novo quarto para o contrato.
	 * @throws LuxosTriploOcupadosException
	 *             Se todos os quartos luxo triplo estiverem ocupados e for
	 *             requisitado um novo quarto para o contrato.
	 * @throws ExecutivosSimplesOcupadosException
	 *             Se todos os quartos executivo simples estiverem ocupados e
	 *             for requisitado um novo quarto para o contrato.
	 * @throws ExecutivosTriploOcupadosException
	 *             Se todos os quartos executivo triplo estiverem ocupados e for
	 *             requisitado um novo quarto para o contrato.
	 * @throws NullPointerException
	 *             Se o contrato passado como parametro for null.
	 */
	public void adicionaContrato(Contrato contrato)
			throws LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException,
			ExecutivosTriploOcupadosException,
			SuitesPresidenciaisOcupadasException {
		if (contrato == null) {
			throw new NullPointerException();
		}
		quartosDesocupados = atualizaQuantQuartosParaContratosNovos(contrato);
		contratos.add(contrato);

	}// adicionaContrato

	/**
	 * Retorna a lista de opinioes do Hotel.
	 * 
	 * @return A lista de opinioes.
	 */
	public List<Opiniao> getOpinioes() {
		return opinioes;
	}

	/**
	 * Adiciona uma Opiniao a lista de opinioes do Hotel.
	 * 
	 * @param opiniao
	 *            A opiniao a ser adicionada.
	 * @throws NullPointerException
	 *             Se a opiniao a ser adicionada for null.
	 */
	public void adicionaOpiniao(Opiniao opiniao) {
		if (opiniao == null) {
			throw new NullPointerException();
		}
		opinioes.add(opiniao);
	}

	/**
	 * Retorna a lista de opinioes ordenada.
	 * 
	 * @return a lista de opinioes ordenada.
	 */
	public List<Opiniao> getOpinioesOrdenadas() {
		// opinioes.sort(new OpiniaoComparator());
		return opinioes;
	}// getOpinioesComMaioresNotas

	public int getQuartosDesocupados(int indice) {
		return quartosDesocupados[indice];
	}// getQuartosDesocupados

	public int[] getArrayQuartosDesocupados() {
		return quartosDesocupados;
	}// getArrayQuartosDesocupado

	/**
	 * Remove um contrato da lista de contratos do hotel e o adiciona na lista
	 * de contratos removidos. Tamb�m adiciona a opiniao do cliente do contrato
	 * a lista de opinioes do hotel.
	 * 
	 * @param contrato
	 *            O contrato a ser removido da lista.
	 * @return "true" se o contrato existia na lista de contratos e foi removido
	 *         com sucesso.
	 */
	public boolean removeContrato(Contrato contrato) {
		boolean removido = contratos.remove(contrato);
		if (removido) {
			opinioes.add(contrato.getOpiniao());
			contratosRemovidos.add(contrato);
			quartosDesocupados = atualizaQuantQuartosParaContratosVelhos(contrato);
		}
		return removido;
	}// removeContrato

	/**
	 * Adiciona uma nova conta para login a lista de contas dos funcionarios do
	 * hotel.
	 * 
	 * @param contaNova
	 *            A nova conta a ser adicionada.
	 * @throws LoginInvalidoException
	 *             Se o login da nova conta ja existir em alguma conta na lista
	 *             de contas do hotel.
	 * @throws NullPointerException
	 *             Se o parametro for null.
	 * @throws NomeCompletoInvalidoException
	 *             Se o nome completo ja existir em alguma conta na lista de
	 *             contas do hotel.
	 * @throws LoginExistenteException
	 *             Se o login da nova conta ja pertencer a uma outra conta.
	 */
	public void adicionaConta(Conta contaNova) throws LoginInvalidoException,
			NullPointerException, NomeCompletoInvalidoException,
			LoginExistenteException {
		if (contaNova == null) {
			throw new NullPointerException();
		}
		for (Conta conta : contasHotel) {
			if (contaNova.getLogin().equals(conta.getLogin())) {
				throw new LoginExistenteException();
			}
		}// for
		contasHotel.add(contaNova);
	}// adicionaConta

	/**
	 * Remove uma conta da lista de contas do hotel.
	 * 
	 * @param conta
	 *            A conta a ser removida.
	 * @return True se a conta foi removida com sucesso ou False caso contrario.
	 * @throws ListaVaziaException
	 *             Se a lista de contas do hotel estiver vazia.
	 */
	public boolean removeConta(String login) throws ListaVaziaException,
			NullPointerException {
		verificaParametroNull(login);
		if (contasHotel.isEmpty()) {
			throw new ListaVaziaException();
		}
		for (Conta conta : contasHotel) {
			if (conta.getLogin().equals(login)) {
				contasHotel.remove(conta);
				return true;
			}
		}
		return false;
	}// removeConta

	/**
	 * Pesquisa uma conta na lista de contas do hotel.
	 * 
	 * @param login
	 *            O login da conta que se quer pesquisar.
	 * @param senha
	 *            A senha da conta que se quer pesquisar.
	 * @return "true" se a conta existir na lista de contas do hotel.
	 * @throws NullPointerException
	 *             Se o login passado for null.
	 */
	public boolean pesquisaConta(String login, String senha)
			throws NullPointerException {
		verificaParametroNull(login);
		verificaParametroNull(senha);
		for (Conta conta : contasHotel) {
			if (conta.getLogin().equals(login)
					&& conta.getSenha().equals(senha)) {
				return true;
			}
		}
		return false;
	}// pesquisaConta

	/**
	 * Retorna a lista de contratos em voga do hotel.
	 * 
	 * @return a lista de contratos em voga do hotel.
	 */
	public List<Contrato> getContratos() {
		return contratos;
	}// getContratos

	/**
	 * Retorna a lista de contratos j� fechados do hotel.
	 * 
	 * @return A lista de contratos removidos.
	 */
	public List<Contrato> getContratosRemovidos() {
		return contratosRemovidos;
	}// getContratosRemovidos

	/**
	 * Retorna a lista de contas dos funcionarios do hotel.
	 * 
	 * @return a lista de contas dos funcionarios do hotel.
	 */
	public List<Conta> getContasHotel() {
		return contasHotel;
	}// getContasHotele

	/**
	 * Informa as estatisticas sobre os quartos alugados em determinado mes do
	 * ano.
	 * 
	 * @param mes
	 *            Um int (1 a 12) que representa o mes para o qual as
	 *            estatisticas serao dadas.
	 * @return Um array de double com valores que representam a quantidade de
	 *         aluguel dos quartos com relacao ao total alugado naquele mes.
	 * @throws MesInvalidoException
	 *             Se o valor do mes passado como parametro for invalido (menor
	 *             que um).
	 */
	public int[] getEstatisticaQuartos(int mes) throws MesInvalidoException {
		if (mes < 1 || mes > 12) {
			throw new MesInvalidoException();
		}// if
		mes--;
		int[] estatisticas = new int[QUANT_TIPOS_DE_QUARTOS];
		int[] quantQuartos;
		for (Contrato contrato : contratos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantQuartos = quantidadeDeQuartos(contrato);
				for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
					estatisticas[i] += quantQuartos[i];
				}// for
			}// if
		}// for

		for (Contrato contrato : contratosRemovidos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantQuartos = quantidadeDeQuartos(contrato);
				for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
					estatisticas[i] += quantQuartos[i];
				}// for
			}// if
		}// for
		return estatisticas;
	}// getEstatisticaMensalQuartos

	/**
	 * Informa as estatisticas gerais sobre os quartos alugados no hotel.
	 * 
	 * @return Um array de double com valores que representam a quantidade de
	 *         aluguel dos quartos com relacao ao total alugado ate entao.
	 */
	public int[] getEstatisticaQuartos() {
		int[] estatisticas = new int[QUANT_TIPOS_DE_QUARTOS];
		int[] quantQuartos;
		for (Contrato contrato : contratos) {
			quantQuartos = quantidadeDeQuartos(contrato);
			for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
				estatisticas[i] += quantQuartos[i];
			}// for
		}// for

		for (Contrato contrato : contratosRemovidos) {
			quantQuartos = quantidadeDeQuartos(contrato);
			for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
				estatisticas[i] += quantQuartos[i];
			}// for
		}// for

		return estatisticas;
	}// getEstatisticaGeralQuartos

	/**
	 * Informa as estatisticas sobre os servicos contratados no hotel (com
	 * excecao dos quartos alugados) em determinado mes do ano.
	 * 
	 * @param mes
	 *            Um int (1 a 12) que representa o mes para o qual as
	 *            estatisticas serao dadas.
	 * @return Um array de double com valores que representam a quantidade de
	 *         contratacao de cada tipo de servico com relacao ao total
	 *         contratado naquele mes.
	 * @throws MesInvalidoException
	 *             Se o valor do mes passado como parametro for invalido (menor
	 *             que um).
	 */
	public double[] getEstatisticaOutrosServicos(int mes)
			throws MesInvalidoException {
		if (mes < 1 || mes > 12) {
			throw new MesInvalidoException();
		}// if
		mes--;
		double[] estatisticas = new double[QUANT_OUTROS_SERVICOS];
		int[] quantOutrosServicos;

		for (Contrato contrato : contratos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
				for (int i = 0; i < QUANT_OUTROS_SERVICOS; i++) {
					estatisticas[i] += quantOutrosServicos[i];
				}// for
			}// if
		}// for

		for (Contrato contrato : contratosRemovidos) {
			if (verificaMesEmPeriodoDeContrato(contrato.getDataCheckIn(),
					contrato.getDataCheckOut(), mes)) {
				quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
				for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
					estatisticas[i] += quantOutrosServicos[i];
				}// for
			}// if
		}// for

		return estatisticas;

	}// getEstatisticaOutrosServicos

	/**
	 * Informa as estatisticas gerais sobre os quartos alugados no hotel.
	 * 
	 * @return Um array de double com valores que representam a quantidade de
	 *         aluguel dos quartos com relacao ao total alugado ate entao.
	 */
	public int[] getEstatisticaOutrosServicos() {
		int[] estatisticas = new int[QUANT_OUTROS_SERVICOS];
		int[] quantOutrosServicos;
		for (Contrato contrato : contratos) {
			quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
			for (int i = 0; i < QUANT_OUTROS_SERVICOS; i++)
				estatisticas[i] += quantOutrosServicos[i];
		}// for

		for (Contrato contrato : contratosRemovidos) {
			quantOutrosServicos = quantidadeDeOutrosServicos(contrato);
			for (int i = 0; i < QUANT_OUTROS_SERVICOS; i++) {
				estatisticas[i] += quantOutrosServicos[i];
			}// for
		}// for

		return estatisticas;
	}// getEstatisticaGeralOutrosServicos

	/**
	 * Informa os servicos contratados (com excecao dos quartos alugados) em um
	 * contrato.
	 * 
	 * @param contrato
	 *            O contrato para o qual se quer saber os servicos contratados.
	 * @return Um array de int com indices que representam se o o servico
	 *         correspondente a cada indice foi ou nao contratado.
	 * 
	 */
	private int[] quantidadeDeOutrosServicos(Contrato contrato) {
		int[] servicos = new int[QUANT_OUTROS_SERVICOS];
		for (Servico servico : contrato.getServicos()) {
			if (servico instanceof Baba)
				servicos[IndexOutrosServicos.BABA.ordinal()]++;
			else if (servico instanceof Carro)
				servicos[IndexOutrosServicos.CARRO.ordinal()]++;
			else if (servico instanceof ContaRestaurante)
				servicos[IndexOutrosServicos.CONTA_RESTAURANTE.ordinal()]++;
		}// for
		return servicos;
	}// quantidadeOutrosServicos

	/**
	 * Pesquisa um contrato em aberto no hotel pelo nome do titular ou de um
	 * hospede acompanhante.
	 * 
	 * @param text
	 *            Um String que representa o nome do titular ou de um
	 *            acompanhante.
	 * @return Uma lista com os contratos encontrados.
	 */
	public List<Contrato> pesquisaContrato(String text) {
		List<Contrato> contratosEncontrados = new ArrayList<Contrato>();

		for (Contrato contrato : getContratos()) {
			if (contrato.getHospedeTitular().getNome().equals(text))
				contratosEncontrados.add(contrato);
			for (String acompanhante : contrato.getAcompanhantes()) {
				if (acompanhante.equals(text)) {
					contratosEncontrados.add(contrato);
				}// if
			}// for
		}// for
		return contratosEncontrados;
	}// pesquisaContrato

	/**
	 * Atualiza a quatidade (por tipo) de quartos desocupados. O contrato
	 * passado como parametro foi encerrado e os quartos que estavam ocupados
	 * agora nao estao mais.
	 * 
	 * @param contrato
	 *            O contrato cujos quartos foram desocupados e agoram ja podem
	 *            ser alugados novamente.
	 * @return A lista atualizada de quartosDesocupados.
	 */
	private int[] atualizaQuantQuartosParaContratosVelhos(Contrato contrato) {

		int[] quantASerSomada = new int[QUANT_TIPOS_DE_QUARTOS];
		quantASerSomada = quantidadeDeQuartos(contrato);

		int[] quartosDesocupados = new int[QUANT_TIPOS_DE_QUARTOS];

		for (int i = 0; i < quartosDesocupados.length; i++) {
			quartosDesocupados[i] = this.quartosDesocupados[i];
		}
		for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++) {
			quartosDesocupados[i] += quantASerSomada[i];
		}
		return quartosDesocupados;
	}// atualizaQuantQuartosParaContratosVelhos

	/**
	 * Retorna a quantidade de quartos por tipo alugados em um contrato.
	 * 
	 * @param contrato
	 *            O contrato para o qual se deseja saber os quartos alugados.
	 * @return Um array de int com informacoes que representam o(s) tipo(s) de
	 *         quarto(s) alugado(s) no contrato.
	 */
	private int[] quantidadeDeQuartos(Contrato contrato) {
		int[] quantidade = new int[QUANT_TIPOS_DE_QUARTOS];

		for (int i = 0; i < quantidade.length; i++) {
			quantidade[i] = 0;
		}
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

	/**
	 * Atualiza a quatidade (por tipo) de quartos desocupados. O contrato
	 * passado como parametro foi aberto e os quartos que foram alugados agoram
	 * nao estao mais desocupados.
	 * 
	 * @param contrato
	 *            O contrato que foi aberto, ocupando pelo menos um quarto.
	 * @return A lista atualizada de quartosDesocupados.
	 * 
	 */
	private int[] atualizaQuantQuartosParaContratosNovos(Contrato contrato)
			throws SuitesPresidenciaisOcupadasException,
			LuxosSimplesOcupadosException, LuxosDuploOcupadosException,
			LuxosTriploOcupadosException, ExecutivosSimplesOcupadosException,
			ExecutivosDuploOcupadosException, ExecutivosTriploOcupadosException {

		int[] quantASerRetirada = new int[QUANT_TIPOS_DE_QUARTOS];
		quantASerRetirada = quantidadeDeQuartos(contrato);
		int[] quartosDesocupados = new int[QUANT_TIPOS_DE_QUARTOS];

		for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++)
			quartosDesocupados[i] = this.quartosDesocupados[i];

		for (int i = 0; i < QUANT_TIPOS_DE_QUARTOS; i++)
			quartosDesocupados[i] -= quantASerRetirada[i];
		return quartosDesocupados;
	}// atualizaQuantQuartosParaContratosNovos

	/**
	 * Verifica se determinado mes esta entre (inclusivo) as dataCheckIn e
	 * dataCheckOut de um contrato.
	 * 
	 * @param dataCheckIn
	 *            A data de abertura/check in do contrato.
	 * @param dataCheckOut
	 *            A data de encerramento/ check out do contrato.
	 * @param mesIndice
	 *            Um indice (1 a 12) que representa o m�s do ano.
	 * @return "true" se o mesIndice estiver entre as datas de abertura e
	 *         encerramento do contrato.
	 * @throws MesInvalidoException
	 */
	private boolean verificaMesEmPeriodoDeContrato(Calendar dataCheckIn,
			Calendar dataCheckOut, int mesIndice) throws MesInvalidoException {
		if (dataCheckOut.get(Calendar.YEAR) == dataCheckIn.get(Calendar.YEAR)) {
			for (int i = dataCheckIn.get(Calendar.MONTH); i <= dataCheckOut
					.get(Calendar.MONTH); i++) {
				if (i == mesIndice) {
					return true;
				}// if
			}// for
		} else if (dataCheckOut.get(Calendar.MONTH) >= mesIndice
				|| dataCheckIn.get(Calendar.MONTH) <= mesIndice) {
			return true;
		}
		return false;
	}// verificaMesEmPeriodoDeContrato

	private void verificaParametroNull(String param)
			throws NullPointerException {
		if (param == null) {
			throw new NullPointerException();
		}
	}

	@Override
	public String toString() {
		return "Hotel";
	}

	/*
	 * para criacao dos arquivos
	 */
	public static void main(String[] args) throws FileNotFoundException,
			IOException, ClassNotFoundException, NullPointerException,
			LoginInvalidoException, SenhaInvalidaException,
			NomeCompletoInvalidoException {

		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream("hotel.dat")));
		out.writeObject(new Hotel());
		out.close();
		System.out.println("Gravados com sucesso");

		ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(
				new FileInputStream("hotel.dat")));
		Hotel hotel1 = (Hotel) in.readObject();
		in.close();

		IndexQuartos[] index = IndexQuartos.values();

		System.out.println(hotel1.toString());
		System.out.println("Quantidade inicial de Contratos: "
				+ hotel1.getContratos().size());
		for (int i = 0; i < hotel1.getArrayQuartosDesocupados().length; i++) {
			System.out.println(index[i].getNome() + ": "
					+ hotel1.getQuartosDesocupados(i));
		}
	}// main

}// Hotel
