package hotel;

public abstract class Quarto implements Servico{
	
	public static final String DESCRICAO = "As acomodacoes do hotel sao todas novas, equipadas com TV LCD 42'', split, frigobar, cofre.";	
	
	private boolean CamaExtra;
	private Frigobar frigobar;
	
	public Quarto (boolean temCamaExtra) {
		this.CamaExtra = temCamaExtra;
		frigobar = new Frigobar();
	}
		
	public abstract double getPreco();
		
	public boolean isTemCamaExtra() {
		return CamaExtra;
	}

	public void setTemCamaExtra(boolean temCamaExtra) {
		this.CamaExtra = temCamaExtra;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Quarto)) {
			return false;
		}	
		Quarto outro = (Quarto) obj;
		return (this.getPreco() == outro.getPreco() && this.isTemCamaExtra() == outro.isTemCamaExtra());
	}

	@Override
	public String toString() {
		return "Quarto [preco=" + this.getPreco() + ", temCamaExtra=" + CamaExtra
				+ "]";
	}
	
	public Frigobar getFrigobar() {
		return frigobar;
	}
		
}
