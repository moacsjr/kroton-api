package br.com.kroton.api.alunos.data;

import java.util.List;

import io.katharsis.resource.annotations.JsonApiId;
import io.katharsis.resource.annotations.JsonApiIncludeByDefault;
import io.katharsis.resource.annotations.JsonApiResource;
import io.katharsis.resource.annotations.JsonApiToMany;
import io.katharsis.resource.annotations.JsonApiToOne;

@JsonApiResource(type = "pessoas")
public class Pessoa {

	@JsonApiId
	private Long id;

	private String nome;

	private String tipoPessoa;

	private String sistemaOrigem;

	@JsonApiToOne
	@JsonApiIncludeByDefault
	private PessoaFisica pessoaFisica;
	
	@JsonApiToMany
	@JsonApiIncludeByDefault
	private List<NecessidadeEspecial> necessidadesEspeciais;
	
	@JsonApiToMany
	@JsonApiIncludeByDefault
	private List<Endereco> pessoaEnderecos;
	
	@JsonApiToMany
	@JsonApiIncludeByDefault
	private List<Telefone> pessoaTelefones;
	
	@JsonApiToMany
	@JsonApiIncludeByDefault
	private List<Documento> pessoaDocumentos;
	
	@JsonApiToMany
	@JsonApiIncludeByDefault
	private List<MidiaSocial> midiasSociais;
	
	@JsonApiToOne
	@JsonApiIncludeByDefault
	private Historico historico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(String tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getSistemaOrigem() {
		return sistemaOrigem;
	}

	public void setSistemaOrigem(String sistemaOrigem) {
		this.sistemaOrigem = sistemaOrigem;
	}

	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

	public List<NecessidadeEspecial> getNecessidadesEspeciais() {
		return necessidadesEspeciais;
	}

	public void setNecessidadesEspeciais(List<NecessidadeEspecial> necessidadesEspeciais) {
		this.necessidadesEspeciais = necessidadesEspeciais;
	}

	public List<Endereco> getPessoaEnderecos() {
		return pessoaEnderecos;
	}

	public void setPessoaEnderecos(List<Endereco> pessoaEnderecos) {
		this.pessoaEnderecos = pessoaEnderecos;
	}

	public List<Telefone> getPessoaTelefones() {
		return pessoaTelefones;
	}

	public void setPessoaTelefones(List<Telefone> pessoaTelefones) {
		this.pessoaTelefones = pessoaTelefones;
	}

	public List<Documento> getPessoaDocumentos() {
		return pessoaDocumentos;
	}

	public void setPessoaDocumentos(List<Documento> pessoaDocumentos) {
		this.pessoaDocumentos = pessoaDocumentos;
	}

	public List<MidiaSocial> getMidiasSociais() {
		return midiasSociais;
	}

	public void setMidiasSociais(List<MidiaSocial> midiasSociais) {
		this.midiasSociais = midiasSociais;
	}

	public Historico getHistorico() {
		return historico;
	}

	public void setHistorico(Historico historico) {
		this.historico = historico;
	}

}
