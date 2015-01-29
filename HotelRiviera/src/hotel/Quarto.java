package hotel;

import excecoes.CamaExtraException;
import excecoes.ValorNegativoException;

public abstract class Quarto implements Servico {

	public final int MAX_PESSOAS;

	private Frigobar frigobar;
	private boolean temCamaExtra;
	private TipoDeQuarto tipo;

	public Quarto(int MAX_PESSOAS, boolean temCamaExtra, TipoDeQuarto tipo)
			throws CamaExtraException {
		if (!tipo.getPodeCamaExtra() && temCamaExtra)
			throw new CamaExtraException();
		this.tipo = tipo;
		this.MAX_PESSOAS = MAX_PESSOAS;
		this.temCamaExtra = temCamaExtra;
		frigobar = new Frigobar();
	}// Construtor

	public boolean getTemCamaExtra() {
		return temCamaExtra;
	}// getTeMCamaExtra

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_PESSOAS;
		result = prime * result
				+ ((frigobar == null) ? 0 : frigobar.hashCode());
		result = prime * result + (temCamaExtra ? 1231 : 1237);
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (MAX_PESSOAS != other.MAX_PESSOAS)
			return false;
		if (frigobar == null) {
			if (other.frigobar != null)
				return false;
		} else if (!frigobar.equals(other.frigobar))
			return false;
		if (temCamaExtra != other.temCamaExtra)
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	public double getPrecoFrigobar() {
		return getFrigobar().getPreco();
	}// getPrecoFrigobar

	public Frigobar getFrigobar() {
		return frigobar;
	}// getFrigobar

	public void somaPrecoFrigobar(double valor) throws ValorNegativoException {
		frigobar.somaPreco(valor);
	}// stPrecoFrigobar

	public TipoDeQuarto getTipo() {
		return tipo;
	}// getTipo

	@Override
	public double getPreco() {
		return getTipo().getPreco();
	}// getPreco

	@Override
	public String toString() {
		return "Quarto [MAX_PESSOAS=" + MAX_PESSOAS + ", frigobar=" + frigobar
				+ ", temCamaExtra=" + temCamaExtra + ", tipo=" + tipo + "]";
	}
	
}// Quarto