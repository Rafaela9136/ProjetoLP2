package hotel;

import java.util.Calendar;

import excecoes.DataInvalidaException;

public abstract class Quarto implements Servico {

	public static final String DESCRICAO = "As acomodacoes do hotel sao todas novas, equipadas com TV LCD 42'', split, frigobar, cofre.";

	private boolean temCamaExtra;
	private Calendar inicioServico;
	private Calendar terminoServico;
	
	private Frigobar frigobar;

	public static final boolean TEM_CAMA_EXTRA = true;
	public static final boolean NAO_TEM_CAMA_EXTRA = false;
	
	public static final double MILISSEGUNDOS_EM_UM_DIA = 86400000;

	public Quarto(boolean temCamaExtra, Calendar inicioServico,
			Calendar terminoServico) throws NullPointerException,
			DataInvalidaException {
		verificaDatasValidas(inicioServico, terminoServico);
		
		this.temCamaExtra = temCamaExtra;
		this.inicioServico = inicioServico;
		this.terminoServico = terminoServico;
		
		frigobar = new Frigobar();
		
	}// Construtor
	
	public int getNumeroDeDias() {
		long tempoInicial = getInicioServico().getTimeInMillis();
		long tempoFinal = getTerminoServico().getTimeInMillis();
		int numDeDias = (int) Math
				.round(((tempoFinal - tempoInicial) / MILISSEGUNDOS_EM_UM_DIA));
		return numDeDias;
	}// getNumeroDias
	
	public boolean isTemCamaExtra() {
		return temCamaExtra;
	}// isTemCamaExtra
	
	public Calendar getInicioServico() {
		return inicioServico;
	}// getInicioServico
	
	public Calendar getTerminoServico() {
		return terminoServico;
	}// getTerminoServico
	
	public Frigobar getFrigobar() {
		return frigobar;
	}// getFrigobar

	public void setTemCamaExtra(boolean temCamaExtra) {
		this.temCamaExtra = temCamaExtra;
	}// setTemCamaExtra
	
	public void setTerminoServico(Calendar terminoServico) throws NullPointerException, DataInvalidaException {
		verificaDatasValidas(getInicioServico(), terminoServico);
		this.terminoServico = terminoServico;
	}// setInicioServico

	private void verificaDatasValidas(Calendar inicioServico,
			Calendar terminoServico) throws NullPointerException,
			DataInvalidaException {
		verificaDataNull(inicioServico);
		verificaDataNull(terminoServico);
		if (inicioServico.compareTo(terminoServico) > 0)
			throw new DataInvalidaException();
	}// verificaDataValida

	private void verificaDataNull(Calendar data) throws NullPointerException {
		if (data == null)
			throw new NullPointerException();
	}// verificaDataNull


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((frigobar == null) ? 0 : frigobar.hashCode());
		result = prime * result
				+ ((inicioServico == null) ? 0 : inicioServico.hashCode());
		result = prime * result + (temCamaExtra ? 1231 : 1237);
		result = prime * result
				+ ((terminoServico == null) ? 0 : terminoServico.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Quarto other = (Quarto) obj;
		if (frigobar == null) {
			if (other.frigobar != null)
				return false;
		} else if (!frigobar.equals(other.frigobar))
			return false;
		if (inicioServico == null) {
			if (other.inicioServico != null)
				return false;
		} else if (!inicioServico.equals(other.inicioServico))
			return false;
		if (temCamaExtra != other.temCamaExtra)
			return false;
		if (terminoServico == null) {
			if (other.terminoServico != null)
				return false;
		} else if (!terminoServico.equals(other.terminoServico))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Quarto [temCamaExtra=" + temCamaExtra + ", inicioServico="
				+ inicioServico + ", terminoServico=" + terminoServico
				+ ", frigobar=" + frigobar + "]";
	}




}
