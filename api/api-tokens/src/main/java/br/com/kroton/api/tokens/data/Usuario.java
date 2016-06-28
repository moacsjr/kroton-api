package br.com.kroton.api.tokens.data;

public class Usuario {
	private String ra;
	private String cpf;
	private String sistema;
	private String periodoLetivo;
	private String statusFinanceiro;
	private String statusAcademico;
	private String metodoAcesso;
	private String senha;
	private boolean autenticado;

	public boolean isAutenticado() {
		return autenticado;
	}
	public void setAutenticado(boolean autenticado) {
		this.autenticado = autenticado;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getMetodoAcesso() {
		return metodoAcesso;
	}
	public void setMetodoAcesso(String metodoAcesso) {
		this.metodoAcesso = metodoAcesso;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getPeriodoLetivo() {
		return periodoLetivo;
	}
	public void setPeriodoLetivo(String periodoLetivo) {
		this.periodoLetivo = periodoLetivo;
	}
	public String getStatusFinanceiro() {
		return statusFinanceiro;
	}
	public void setStatusFinanceiro(String statusFinanceiro) {
		this.statusFinanceiro = statusFinanceiro;
	}
	public String getStatusAcademico() {
		return statusAcademico;
	}
	public void setStatusAcademico(String statusAcademico) {
		this.statusAcademico = statusAcademico;
	}
}
