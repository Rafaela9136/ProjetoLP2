package hotel;

import java.util.*;

/**
 * Interface para definir estrategias de calculo de preco em altas e baixas estacoes do ano.
 * 
 */
public interface Estacao {
	
	/**
	 * Informa a porcentagem que sera multiplicada pelo custo total da estadia no hotel Rivieira a fim de definir
	 * o preco final a pagar. A porcentagem a ser aplicada pode encarecer (em periodos de alta estacao) ou baratear
	 * (em periodos de baixa estacao) o custo da estadia no hotel.
	 * 
	 * @return a porcentagem que sera multiplicada pelo custo.
	 */
	public double getPorcentagemAAplicar();
	
	/**
	 * Informa a data do inicio da estacao.
	 * 
	 * @return A data de inicio da estacao.
	 */
	public Calendar getDataInicio(); 
	
	/**
	 * Informa a data do fim da estacao.
	 * 
	 * @return A data do fim da estacao.
	 */
	public Calendar getDataFim();
	
}
