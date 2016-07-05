/**
 * 
 */
package br.com.kroton.api.tokens.data;

import java.nio.charset.Charset;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.binary.Hex;

/**
 * @author jair.souza
 *
 */
public class LoginResponse {
	
	private Map<String, String> meta = new HashMap<>();

	private Map<String, Object> data = new HashMap<>();;
	
	public LoginResponse() {
		
	}

	public LoginResponse(String tokenJwt) {
		
		data.put("jwt", tokenJwt);
		
		meta.put("transactionId", APIUtils.getUID());
		
	}

	public LoginResponse(String tokenJwt, String apiVersion) {
		this(tokenJwt);
		meta.put("apiVersion", apiVersion);
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}
	
	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

}
