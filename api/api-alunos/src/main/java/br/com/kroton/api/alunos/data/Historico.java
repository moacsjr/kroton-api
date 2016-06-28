package br.com.kroton.api.alunos.data;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "historicos")
public class Historico {

	@JsonApiId
	private String usuarioCriacao;
	private String dataCriacao;
	private String usuarioAtualizacao;
	private String dataAtualizacao;
	
	public String getUsuarioCriacao() {
		return usuarioCriacao;
	}
	public void setUsuarioCriacao(String usuarioCriacao) {
		this.usuarioCriacao = usuarioCriacao;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public String getUsuarioAtualizacao() {
		return usuarioAtualizacao;
	}
	public void setUsuarioAtualizacao(String usuarioAtualizacao) {
		this.usuarioAtualizacao = usuarioAtualizacao;
	}
	public String getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(String dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

}
