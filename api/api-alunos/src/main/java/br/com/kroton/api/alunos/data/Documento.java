package br.com.kroton.api.alunos.data;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "documentos")
public class Documento {

	@JsonApiId
	private String id;
	private String sistema;
	private String numero;
	private String orgaoEmissor;
	private String zona;
	private String secao;
	private String dataExpedicao;
	private String dataExpiracao;
	private Dominio tipoDocumento;
	private String cidadeEmissor;
	private String estado;
	private Historico historico;
	
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getOrgaoEmissor() {
		return orgaoEmissor;
	}
	public void setOrgaoEmissor(String orgaoEmissor) {
		this.orgaoEmissor = orgaoEmissor;
	}
	public String getZona() {
		return zona;
	}
	public void setZona(String zona) {
		this.zona = zona;
	}
	public String getSecao() {
		return secao;
	}
	public void setSecao(String secao) {
		this.secao = secao;
	}
	public String getDataExpedicao() {
		return dataExpedicao;
	}
	public void setDataExpedicao(String dataExpedicao) {
		this.dataExpedicao = dataExpedicao;
	}
	public String getDataExpiracao() {
		return dataExpiracao;
	}
	public void setDataExpiracao(String dataExpiracao) {
		this.dataExpiracao = dataExpiracao;
	}
	public Dominio getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(Dominio tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getCidadeEmissor() {
		return cidadeEmissor;
	}
	public void setCidadeEmissor(String cidadeEmissor) {
		this.cidadeEmissor = cidadeEmissor;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

}
