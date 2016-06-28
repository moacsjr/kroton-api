/**
 * 
 */
package br.com.kroton.api.tokens.data;

/**
 * @author jair.souza
 *
 */
public class LoginResponse {

	private String jwt;
	
	public LoginResponse() {
		
	}

	public LoginResponse(String tokenJwt) {
		this.jwt = tokenJwt;
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	
	

}
