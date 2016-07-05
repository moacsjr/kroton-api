package br.com.kroton.api.alunos.data;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "telefones")
public class Telefone {

	@JsonApiId
	private String id;
	private String sistema;
	private String ddi;
	private String ddd;
	private String numero;
	private String ramal;
	private String preferencia;
	private Dominio tipoTelefone;
	private Historico historico;
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getDdi() {
		return ddi;
	}
	public void setDdi(String ddi) {
		this.ddi = ddi;
	}
	public String getDdd() {
		return ddd;
	}
	public void setDdd(String ddd) {
		this.ddd = ddd;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getRamal() {
		return ramal;
	}
	public void setRamal(String ramal) {
		this.ramal = ramal;
	}
	public String getPreferencia() {
		return preferencia;
	}
	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}
	public Dominio getTipoTelefone() {
		return tipoTelefone;
	}
	public void setTipoTelefone(Dominio tipoTelefone) {
		this.tipoTelefone = tipoTelefone;
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
