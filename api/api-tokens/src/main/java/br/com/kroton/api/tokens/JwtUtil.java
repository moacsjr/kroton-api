package br.com.kroton.api.tokens;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kroton.api.tokens.data.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
@Component
public class JwtUtil {

    Log log = LogFactory.getLog(JwtUtil.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.privatekey}")
    private String privateKeyFileName;

    @Value("${jwt.publickey}")
    private String publicKeyFileName;

    @Value("${jwt.expiration}")
    private int expirationInterval;

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
     */
    public List<Map<String,Object>> parseToken(String ra, String token) throws JsonParseException, JsonMappingException, IOException, AcessoNegadoException, ClassNotFoundException, URISyntaxException {
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
            return null;
        }
    }

    /**
     * Generates a JWT token containing username as subject, and userId and role as additional claims. These properties are taken from the specified
     * User object. Tokens validity is infinite.
     *
     * @param usuariosAutenticados the user for which the token will be generated
     * @return the JWT token
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException
     */
    public String generateToken(String ra, String cpf, String email, List<Usuario> usuariosAutenticados) throws FileNotFoundException, IOException, ClassNotFoundException {
        Claims claims = Jwts.claims().setSubject(ra);

        ObjectMapper jsonMapper = new ObjectMapper();
        String usuarios = jsonMapper.writeValueAsString(usuariosAutenticados);
        DateTime expiration = DateTime.now().plusHours(expirationInterval);

        claims.put("ra", ra);
        claims.put("cpf", cpf);
        claims.put("email", email);
        claims.put("usuarios", usuarios);
        claims.put("exp", expiration.toDate()); //expiration

        final PrivateKey privateKey = getPrivateKey();

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.RS256, privateKey)
                .compact();
    }

    private PublicKey getPublicKey() throws IOException, ClassNotFoundException {

        // Decrypt using public key
        final PublicKey publicKey = (PublicKey)getStreamKey(privateKeyFileName);
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
