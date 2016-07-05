package br.com.kroton.api.tokens.data;

public class Curso {
	
	private String id;

	private String codigo;

	private String sistema;

	private String nome;
	
	private Dominio marca;
	
	private Dominio modalidade;

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

	

}
