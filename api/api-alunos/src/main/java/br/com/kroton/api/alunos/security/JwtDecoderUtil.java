package br.com.kroton.api.alunos.security;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kroton.api.alunos.exception.AcessoNegadoException;
import br.com.kroton.api.alunos.exception.JwtTokenValidationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Component
public class JwtDecoderUtil {

    Log log = LogFactory.getLog(JwtDecoderUtil.class);
	
    @Value("${jwt.privatekey}")
	private String privateKeyFileName;

    @Value("${jwt.publickey}")
	private String publicKeyFileName;
    
    

    /**
     * Tries to parse specified String as a JWT token. If successful, returns User object with username, id and role prefilled (extracted from token).
     * If unsuccessful (token is invalid or not containing all required user properties), simply returns null.
     * 
     * @param token the JWT token to parse
     * @param authToken 
     * @return the User object extracted from specified token or null if a token is invalid.
     * @throws IOException 
     * @throws JsonMappingException 
     * @throws JsonParseException 
     * @throws AcessoNegadoException 
     * @throws ClassNotFoundException 
     * @throws JwtTokenValidationException 
     */
    public List<Map<String,Object>> parseToken(String ra, String sistema, String token) throws JsonParseException, JsonMappingException, IOException, AcessoNegadoException, ClassNotFoundException, JwtTokenValidationException {
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
            
            String valido = ( subject.equals(ra) || body.get("sistema").equals(sistema) ) ? "VALIDO" : "INVALIDO";
            
            //valida ra/sistema no token jwt com dados informados na uri
            log.info(String.format("Verificando Token[RA=%s, Sistema=%s] %s", subject, body.get("sistema"), valido));
            if(!subject.equals(ra) || !body.get("sistema").equals(sistema)){
            	throw new AcessoNegadoException("O usuário/sistema informado não tem permissão para acesar esse recurso.");
            }
            
            return usuarios;

        } catch (JwtException | ClassCastException e) {
            throw new JwtTokenValidationException(e);
        }
    }

    private PublicKey getPublicKey() throws IOException, ClassNotFoundException {

        // Decrypt using public key
        final PublicKey publicKey = (PublicKey)getStreamKey(publicKeyFileName);
        return publicKey;
    }

    private PrivateKey getPrivateKey() throws IOException, ClassNotFoundException {

        // Encrypt the string using the private key
        final PrivateKey privateKey = (PrivateKey)getStreamKey(privateKeyFileName);
        return privateKey;
    }

    private Object getStreamKey(String key) throws IOException, ClassNotFoundException {

        // objeto de retorno
    	Object value = null;

    	// stream de leitura para o object
    	InputStream input = null;

        // chave criada
    	InputStream resource = getClass().getResourceAsStream(key);

    	// realiza conversao para DataInputStream
    	if (resource instanceof BufferedInputStream)
    		input = new DataInputStream(resource);
    	else
    		input = resource;

        // converte o inputstream para objectstream
        ObjectInputStream stream = new ObjectInputStream(input);

        // realiza a leitura do object
        value = stream.readObject();

        // fecha o stream
        stream.close();

        // retorno do objeto lido
        return value;
    }

}