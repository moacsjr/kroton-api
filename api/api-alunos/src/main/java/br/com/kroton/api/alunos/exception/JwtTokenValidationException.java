package br.com.kroton.api.alunos.exception;

public class JwtTokenValidationException extends Exception {

	public JwtTokenValidationException(RuntimeException e) {
		super(e);
	}

}
