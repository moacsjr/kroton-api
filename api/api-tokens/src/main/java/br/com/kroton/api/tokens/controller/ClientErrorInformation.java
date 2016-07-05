package br.com.kroton.api.tokens.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.kroton.api.tokens.data.APIUtils;

public class ClientErrorInformation {
	
	public class Error {

		public String code;
		public String title;
		public String detail;
		public Map<String, String> links = new HashMap<>();
	}
	
	@JsonIgnore
	private HttpStatus httpStatus;
	
	private Map<String, String> meta = new HashMap<>();
	
	private Error errors;
	
	public ClientErrorInformation(HttpStatus httpStatus, String code, String title, String detail, String links) {
		super();
		Error err = new Error();
		
		err.code = code;
		err.title = title;
		err.detail = detail;
		err.links.put("rel", "");
		err.links.put("href", "");
		
		errors = err;
		this.httpStatus = httpStatus;
		meta.put("transactionId", APIUtils.getUID());
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}

	public Error getErrors() {
		return errors;
	}

	public void setErrors(Error errors) {
		this.errors = errors;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public void setApiVersion(String apiVersion) {
		meta.put("apiVersion", apiVersion);
	}


	

}
