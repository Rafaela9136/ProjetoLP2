package hotel;

import excecoes.CamaExtraException;
import excecoes.ValorNegativoException;

public abstract class Quarto implements Servico {

	
	public final int MAX_PESSOAS;
	
	private TipoDeQuarto tipo;
	private Frigobar frigobar;
	private boolean temCamaExtra;
	
	public Quarto(int MAX_PESSOAS, boolean temCamaExtra, TipoDeQuarto tipo) throws CamaExtraException {
		
		if(!tipo.getPodeCamaExtra() && temCamaExtra)
			throw new CamaExtraException();
		
		this.tipo = tipo;
		this.MAX_PESSOAS = MAX_PESSOAS;
		this.temCamaExtra = temCamaExtra;
		frigobar = new Frigobar();
	}// Construtor
	
	public TipoDeQuarto getTipoDeQuarto() {
		return tipo;
	}// getPrecoDeQuarto
	
	public boolean getTemCamaExtra() {
		return temCamaExtra;
	}// getTeMCamaExtra
	
	public double getPrecoFrigobar() {
		return getFrigobar().getPreco();
	}// getPrecoFrigobar
	
	public Frigobar getFrigobar() {
		return frigobar;
	}// getFrigobar
	
	public void setPrecoFrigobar(double valor) throws ValorNegativoException {
		frigobar.setPreco(valor);
	}// stPrecoFrigobar


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + MAX_PESSOAS;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Quarto [MAX_PESSOAS=" + MAX_PESSOAS + ", preco=" + tipo
				+ ", frigobar=" + frigobar + ", camaExtra=" + temCamaExtra + "]";
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
		if (tipo != other.tipo)
			return false;
		return true;
	}

}// Quarto
