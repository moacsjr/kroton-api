package br.com.kroton.api.tokens;

public class AcessoNegadoException extends Exception {
	
	public AcessoNegadoException(String message) {
		super(message);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
