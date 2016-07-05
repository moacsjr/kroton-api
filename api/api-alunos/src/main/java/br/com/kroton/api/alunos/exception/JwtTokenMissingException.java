package br.com.kroton.api.alunos.exception;

public class JwtTokenMissingException extends Exception {
	private static final long serialVersionUID = -7217611041502505052L;

	public JwtTokenMissingException(String string) {
		super(string);
	}

}
