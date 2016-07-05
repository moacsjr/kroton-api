package br.com.kroton.api.alunos.data;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "necessidadesEspeciais")
public class NecessidadeEspecial {

	@JsonApiId
	private String id;
	private String sistema;
	private String detalhe;
	private Dominio tipoPNE;
	private Historico historico;
	
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getDetalhe() {
		return detalhe;
	}
	public void setDetalhe(String detalhe) {
		this.detalhe = detalhe;
	}
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	public Dominio getTipoPNE() {
		return tipoPNE;
	}
	public void setTipoPNE(Dominio tipoPNE) {
		this.tipoPNE = tipoPNE;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	
}
