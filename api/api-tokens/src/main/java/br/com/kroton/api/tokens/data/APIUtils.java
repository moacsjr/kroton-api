package br.com.kroton.api.tokens.data;

import java.nio.charset.Charset;
import java.rmi.server.UID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;

public class APIUtils {

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

	
}
