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

	private String jwt;
	
	public LoginResponse() {
		
		
	}

	public LoginResponse(String tokenJwt) {
		this.jwt = tokenJwt;
		meta.put("transactionId", getUID());
	}

	public LoginResponse(String tokenJwt, String apiVersion) {
		this(tokenJwt);
		meta.put("apiVersion", apiVersion);
	}

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public Map<String, String> getMeta() {
		return meta;
	}

	public void setMeta(Map<String, String> meta) {
		this.meta = meta;
	}
	
	private String getUID(){
		MessageDigest messageDigest = null;
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		messageDigest.update(new UID().toString().getBytes(Charset.forName("UTF8")));  
	    final byte[] resultByte = messageDigest.digest();
	    final String result = new String(Hex.encodeHex(resultByte));
	    return result;
	}

}
