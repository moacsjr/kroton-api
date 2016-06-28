package br.com.kroton.api.alunos.security;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;
    
    @Value("${jwt.privatekey}")
	private String privateKeyFileName;

    @Value("${jwt.publickey}")
	private String publicKeyFileName;

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @return the User object extracted from specified token or null if a token is invalid.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @throws AcessoNegadoException 
     * @throws ClassNotFoundException 
     * @throws JwtTokenValidationException 
     */
    public List<Map<String,Object>> parseToken(String ra, String token) throws JsonParseException, JsonMappingException, IOException, AcessoNegadoException, ClassNotFoundException, JwtTokenValidationException {
        try {
        	
        	final PublicKey publicKey = getPublicKey();
        	
        	Claims body = Jwts.parser()
                    .setSigningKey(publicKey)
                    .parseClaimsJws(token)
                    .getBody();

            ObjectMapper jsonMapper = new ObjectMapper();
            List<Map<String, Object>> usuarios = (List<Map<String, Object>>) jsonMapper.readValue((String)body.get("usuarios"), jsonMapper.getTypeFactory().constructCollectionType(
                    List.class, HashMap.class));
            String subject = body.getSubject();
            
            if(!subject.equals(ra)){
            	throw new AcessoNegadoException("O usuário informado não tem permissão para acesar esse recurso.");
            }
            
            return usuarios;

        } catch (JwtException | ClassCastException e) {
            throw new JwtTokenValidationException(e);
        }
    }

	private PublicKey getPublicKey() throws IOException, FileNotFoundException, ClassNotFoundException {
		ObjectInputStream inputStream = null;
		
		//decrypt using public key
		inputStream = new ObjectInputStream(new FileInputStream(publicKeyFileName));
		final PublicKey publicKey = (PublicKey) inputStream.readObject();
		return publicKey;
	}

}