package br.com.kroton.api.alunos.exception;

import java.util.HashMap;
import java.util.Map;

import br.com.kroton.api.alunos.APIUtils;

public class APIErrorResponse {
	
	public class Error {

		public String code;
		public String title;
		public String detail;
		public Map<String, String> links = new HashMap<>();
	}
	
	private Map<String, String> meta = new HashMap<>();
	
	private Error errors;

	private String apiVersion;
	
	public APIErrorResponse(String code, String title, String detail, String apiVersion) {
		Error err = new Error();
		
		err.code = code;
		err.title = title;
		err.detail = detail;
		err.links.put("rel", "");
		err.links.put("href", "");
		
		errors = err;
		meta.put("transactionId", APIUtils.getUID());
		meta.put("apiVersion", apiVersion);
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

}
