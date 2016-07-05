package br.com.kroton.api.alunos.data;

import java.util.List;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiIncludeByDefault;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type = "alunos")
public class Aluno {

	@JsonApiId
	private String ra;
	
	private String sistema;
	
	private String dataIngresso;
	
	private String descricaoPeriodoIngresso;
	
	private String periodoSituacaoAcademica;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Unidade unidade;

	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Curso curso;

	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Pessoa pessoa;
	
	@JsonApiToMany
	@JsonApiIncludeByDefault
	private List<AlunoPeriodo> alunoPeriodos;
	
	private Historico historico;

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public Unidade getUnidade() {
		return unidade;
	}

	public void setUnidade(Unidade unidade) {
		this.unidade = unidade;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public String getDescricaoPeriodoIngresso() {
		return descricaoPeriodoIngresso;
	}

	public void setDescricaoPeriodoIngresso(String descricaoPeriodoIngresso) {
		this.descricaoPeriodoIngresso = descricaoPeriodoIngresso;
	}

	public String getPeriodoSituacaoAcademica() {
		return periodoSituacaoAcademica;
	}

	public void setPeriodoSituacaoAcademica(String periodoSituacaoAcademica) {
		this.periodoSituacaoAcademica = periodoSituacaoAcademica;
	}

	public String getDataIngresso() {
		return dataIngresso;
	}

	public void setDataIngresso(String dataIngresso) {
		this.dataIngresso = dataIngresso;
	}

	public List<AlunoPeriodo> getAlunoPeriodos() {
		return alunoPeriodos;
	}

	public void setAlunoPeriodos(List<AlunoPeriodo> alunoPeriodos) {
		this.alunoPeriodos = alunoPeriodos;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
