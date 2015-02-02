package hotel;

public abstract class Quarto2 implements Servico{
	
	protected double preco;
	protected String descricao;	
	private boolean temCamaExtra;
	
	
	public Quarto2 (boolean temCamaExtra) {
		this.temCamaExtra = temCamaExtra;
	}

	public abstract double getPreco();
		
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isTemCamaExtra() {
		return temCamaExtra;
	}

	public void setTemCamaExtra(boolean temCamaExtra) {
		this.temCamaExtra = temCamaExtra;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Quarto2)) {
			return false;
		}	
		Quarto2 outro = (Quarto2) obj;
		return this.preco == outro.getPreco();
	}

	@Override
	public String toString() {
		return "Quarto2 [preco=" + preco + ", temCamaExtra=" + temCamaExtra
				+ "]";
	}
	
	
}
