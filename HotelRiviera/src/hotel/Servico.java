package hotel;

import java.io.Serializable;
import java.util.Calendar;

public interface Servico {
	
	/**
	 * Recupera o preco do servico
	 * @return Preco do servico
	 */
	public double getPreco();
	
	/**
	 * Recupera o nome do servico.
	 * @return Retorna o nome do servico.
	 */
	public String getNome();
	
	/**
	 * Recupera a data inicial do servico.
	 * @return Data inicial do servico.
	 */
	public Calendar getDataCheckIn();
	
	/**
	 * Recupera a data final do servico.
	 * @return Data Final do servico.
	 */
	public Calendar getDataCheckOut();

}// Servico
