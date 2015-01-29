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