package hotel;

import java.sql.*;

public abstract class EstrategiaAplicavel {
	
	private Date dataInicio;
	private Date dataFim;
	private Double porcentagemAplicada;
	
	public Date getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}
	public Date getDataFim() {
		return dataFim;
	}
	public void setDataFim(Date dataFim) {
		this.dataFim = dataFim;
	}
	public Double getPorcentagemAplicada() {
		return porcentagemAplicada;
	}
	public void setPorcentagemAplicada(Double porcentagemAplicada) {
		this.porcentagemAplicada = porcentagemAplicada;
	}
	
}
