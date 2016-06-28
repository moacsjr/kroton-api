/**
 * 
 */
package br.com.kroton.api.tokens.data;

/**
 * @author jair.souza
 *
 */
public class LoginRequest {
	private String cpf;
	private String email;
	private String senha;

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
