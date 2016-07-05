package br.com.kroton.api.alunos.exception;

public class AcessoNegadoException extends RuntimeException {
	private static final long serialVersionUID = -7240380475531271494L;

	public AcessoNegadoException(String message) {
		super(message);
	}
}
