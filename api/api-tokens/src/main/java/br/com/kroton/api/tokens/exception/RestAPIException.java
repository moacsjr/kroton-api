package br.com.kroton.api.tokens.exception;

import br.com.kroton.api.tokens.controller.ClientErrorInformation;

public class RestAPIException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private ClientErrorInformation info;
	
	public RestAPIException(ClientErrorInformation erro) {
		this.info = erro;
	}
	public ClientErrorInformation getInfo() {
		return info;
	}
	public void setInfo(ClientErrorInformation info) {
		this.info = info;
	}
	
	
	
	
	

}
