package br.com.kroton.api.alunos.security;

public class JwtTokenValidationException extends Exception {

	public JwtTokenValidationException(RuntimeException e) {
		super(e);
	}

}
