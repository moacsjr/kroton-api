package br.com.kroton.api.alunos.data;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "alunoPeriodos")
public class AlunoPeriodo {
	
	@JsonApiId
	private String id;
	
	private Dominio status;
	
	private Unidade unidade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dominio getStatus() {
		return status;
	}

	public void setStatus(Dominio status) {
		this.status = status;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

}
