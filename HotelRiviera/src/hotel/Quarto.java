package hotel;

import java.util.Calendar;

public abstract class Quarto implements Servico{
	
	public static final String DESCRICAO = "As acomodacoes do hotel sao todas novas, equipadas com TV LCD 42'', split, frigobar, cofre.";	
	
	private boolean CamaExtra;
	private Calendar inicioHospedagem;
	private Calendar fimHospedagem;
	
	public Quarto (boolean temCamaExtra) {
		this.CamaExtra = temCamaExtra;
	}
		
	public Calendar getInicioHospedagem() {
		return inicioHospedagem;
	}

	public void setInicioHospedagem(Calendar novoInicioHospedagem){
		this.inicioHospedagem = novoInicioHospedagem;
	}

	public Calendar getFimHospedagem() {
		return fimHospedagem;
	}

	public void setFimHospedagem(Calendar novoFimHospedagem) {
		this.fimHospedagem = novoFimHospedagem;
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
		
}
