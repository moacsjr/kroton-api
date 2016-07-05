package br.com.kroton.api.alunos.data;

import java.util.Date;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiIncludeByDefault;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type="pessoaFisica")
public class PessoaFisica {
	
	@JsonApiId
	private String id;
	
	private String sistema;
	
	private String cpf;
	
	private Date dataNascimento;
	
	private String dataConclusaoEnsMedio;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Dominio raca;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Dominio genero;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Dominio estadoCivil;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Cidade naturalidade;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Estado estadoNacionalidade;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Pais paisNacionalidade;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Historico historico;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSistema() {
		return sistema;
	}
	public void setSistema(String sistema) {
		this.sistema = sistema;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getDataConclusaoEnsMedio() {
		return dataConclusaoEnsMedio;
	}
	public void setDataConclusaoEnsMedio(String dataConclusaoEnsMedio) {
		this.dataConclusaoEnsMedio = dataConclusaoEnsMedio;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public Historico getHistorico() {
		return historico;
	}
	public void setHistorico(Historico historico) {
		this.historico = historico;
	}
	public Dominio getRaca() {
		return raca;
	}
	public void setRaca(Dominio raca) {
		this.raca = raca;
	}
	public Dominio getGenero() {
		return genero;
	}
	public void setGenero(Dominio genero) {
		this.genero = genero;
	}
	public Dominio getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(Dominio estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public Cidade getNaturalidade() {
		return naturalidade;
	}
	public void setNaturalidade(Cidade naturalidade) {
		this.naturalidade = naturalidade;
	}
	public Estado getEstadoNacionalidade() {
		return estadoNacionalidade;
	}
	public void setEstadoNacionalidade(Estado estadoNacionalidade) {
		this.estadoNacionalidade = estadoNacionalidade;
	}
	public Pais getPaisNacionalidade() {
		return paisNacionalidade;
	}
	public void setPaisNacionalidade(Pais paisNacionalidade) {
		this.paisNacionalidade = paisNacionalidade;
	}

	
	

}
