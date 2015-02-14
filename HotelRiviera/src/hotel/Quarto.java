package hotel;

/*
 * Representa um quarto do hotel. Ha tres tipos de quartos: executivo, luxo e presidencial.
 * Quartos do tipo executivo e luxo existem nas categorias simples, duplo e triplo.
 */
public abstract class Quarto implements Servico{
	
	public static final String DESCRICAO = "As acomodacoes do hotel sao todas novas, equipadas com TV LCD 42'', split, frigobar, cofre.";	
	private boolean CamaExtra;
	private Frigobar frigobar;
	
	/**
	 * Construtor da classe Quarto.
	 * 
	 * @param camaExtra
	 * 			Um boolean que indica se o cliente solicitou uma cama extra para o quarto.
	 */
	public Quarto (boolean camaExtra) {
		this.CamaExtra = camaExtra;
		frigobar = new Frigobar();
	}
	
	public Frigobar getFrigobar() {
		return frigobar;
	}

	public void setFrigobar(Frigobar frigobar) {
		this.frigobar = frigobar;
	}


	/**
	 * Calcula e deve retornar o preco da diario do quarto.
	 */
	public abstract double getPreco();
	
	@Override
	public String getNome() {
		return "Quarto";
	}// getNome
	
	/**
	 * Informa se o cliente solicitou uma cama extra no quarto.
	 * 
	 * @return Retorna true se o cliente solicitou uma cama extra no quarto.
	 */
	public boolean isCamaExtra() {
		return CamaExtra;
	}
	
	/**
	 * Define um novo valor para a variavel "camaExtra", que informa se o hospede solicitou ou nao uma cama extra para o quarto.
	 * 
	 * @param camaExtra O novo valor (true/false) a ser definido para a camaExtra. 
	 */
	public void setCamaExtra(boolean temCamaExtra) {
		this.CamaExtra = temCamaExtra;
	}
	
	/**
	 * Compara dois quartos e informa se se sao iguais ou nao.
	 * 
	 * @return true se os quartos comparados forem iguais.
	 */
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Quarto)) {
			return false;
		}	
		Quarto outro = (Quarto) obj;
		return (this.getPreco() == outro.getPreco() && this.isCamaExtra() == outro.isCamaExtra());
	}
	
	/**
	 * Retorna uma representacao em String do objeto.
	 */
	@Override
	public String toString() {
		return "Quarto [preco=" + this.getPreco() + ", camaExtra=" + CamaExtra
				+ "]";
	}
		
}
