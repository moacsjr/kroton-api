package br.com.kroton.api.alunos;

import java.nio.charset.Charset;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.springframework.core.env.Environment;

public class APIUtils {
	
	public static String apiVersion;

	public static String getUID(){
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

	public static String getAPIVersion() {
		
		return apiVersion;
	}

	
}
