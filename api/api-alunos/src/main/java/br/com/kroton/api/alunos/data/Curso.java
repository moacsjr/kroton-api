package br.com.kroton.api.alunos.data;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiResource;

@JsonApiResource(type = "cursos")
public class Curso {

	@JsonApiId
	private String id;

	private String codigo;

	private String sistema;

	private String nome;

	private Dominio marca;

	private String tipoPeriodoLetivos;
	
	private String numeroPeriodosLetivos;
	
	private String codigoMec;
	
	private Dominio tipoCurso;
	
	private Dominio regime;
	
	private Dominio modalidade;
	
	private Dominio unidade;
	
	private Historico historico;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSistema() {
		return sistema;
	}

	public void setSistema(String sistema) {
		this.sistema = sistema;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Dominio getMarca() {
		return marca;
	}

	public void setMarca(Dominio marca) {
		this.marca = marca;
	}

	public Dominio getModalidade() {
		return modalidade;
	}

	public void setModalidade(Dominio modalidade) {
		this.modalidade = modalidade;
	}

	public String getTipoPeriodoLetivos() {
		return tipoPeriodoLetivos;
	}

	public void setTipoPeriodoLetivos(String tipoPeriodoLetivos) {
		this.tipoPeriodoLetivos = tipoPeriodoLetivos;
	}

	public String getNumeroPeriodosLetivos() {
		return numeroPeriodosLetivos;
	}

	public void setNumeroPeriodosLetivos(String numeroPeriodosLetivos) {
		this.numeroPeriodosLetivos = numeroPeriodosLetivos;
	}

	public String getCodigoMec() {
		return codigoMec;
	}

	public void setCodigoMec(String codigoMec) {
		this.codigoMec = codigoMec;
	}

	public Dominio getTipoCurso() {
		return tipoCurso;
	}

	public void setTipoCurso(Dominio tipoCurso) {
		this.tipoCurso = tipoCurso;
	}

	public Dominio getRegime() {
		return regime;
	}

	public void setRegime(Dominio regime) {
		this.regime = regime;
	}

	public Dominio getUnidade() {
		return unidade;
	}

	public void setUnidade(Dominio unidade) {
		this.unidade = unidade;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
