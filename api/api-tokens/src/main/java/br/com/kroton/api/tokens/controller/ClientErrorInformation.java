package br.com.kroton.api.tokens.controller;

import org.springframework.http.HttpStatus;

public class ClientErrorInformation {
	
	private HttpStatus httpStatus;
	private String code;
	private String title;
	private String detai;
	private String links;
	
	
	public ClientErrorInformation(HttpStatus httpStatus, String code, String title, String detai, String links) {
		super();
		this.httpStatus = httpStatus;
		this.code = code;
		this.title = title;
		this.detai = detai;
		this.links = links;
	}


	public HttpStatus getHttpStatus() {
		return httpStatus;
	}


	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}


	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDetai() {
		return detai;
	}


	public void setDetai(String detai) {
		this.detai = detai;
	}


	public String getLinks() {
		return links;
	}


	public void setLinks(String links) {
		this.links = links;
	}
	

}
