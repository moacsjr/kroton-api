package br.com.kroton.api.tokens;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import br.com.kroton.api.tokens.data.Usuario;
import br.com.kroton.api.tokens.exception.ValidacaoAPIException;

@Component
public class PasswordFilter {
	
	private static final String SHA1 = "SHA";
	
	private static final String MD5 = "MD5";
	
	private Log LOG = LogFactory.getLog(PasswordFilter.class);
	
	public List<Usuario> checkPassword(List<Usuario> usuarios, String senhaDescriptografada) throws NoSuchAlgorithmException, ValidacaoAPIException{
		
		List<Usuario> usuariosAutenticados = new ArrayList<Usuario>();
		
		for ( Usuario usuario:usuarios ) {
			
			String hashSenha = gerarHashSenha(usuario, senhaDescriptografada);
			
			LOG.debug(String.format("Validando senha usuario:[%s] metodo (%s) BANCO[%s] - USUARIO[%s]", usuario.getRa(), usuario.getMetodoAcesso(), usuario.getSenha(), hashSenha));	
			
			if (usuario.getSenha().equalsIgnoreCase(hashSenha)) {
			
				LOG.debug("SENHA VALIDADA!");
				
				usuariosAutenticados.add(usuario);
				
			}
    		
    	}

 	   	return usuariosAutenticados;
	}

	
	private String gerarHashSenha(Usuario usuario, String senha) throws ValidacaoAPIException, NoSuchAlgorithmException {
		
		String senhaDescriptografada = senha;
		
		String senhaCriptografada;
		
		if(senhaDescriptografada == null){
			
			throw new ValidacaoAPIException("Senha do usuário não pode ser nula");
			
		}
		
		if(usuario.getMetodoAcesso() == null) {
			
			throw new ValidacaoAPIException("Senha do usuário não pode ser nula");
			
		}
		
		if(usuario.getMetodoAcesso().equals("SHA-1")){
			
			senhaCriptografada = gerarHashSenha(senhaDescriptografada .trim(), SHA1);
			
		}else if(usuario.getMetodoAcesso().equals("MD5")) {
			
			senhaCriptografada = gerarHashSenha(senhaDescriptografada .trim(), MD5);
			
		}else {
			
			throw new ValidacaoAPIException("Método de criptografia desconhecido");
			
		}
		
		return senhaCriptografada;
		
	}


	private String gerarHashSenha (String senha, String metodo) throws NoSuchAlgorithmException {

		MessageDigest m=MessageDigest.getInstance(metodo);
		m.update(senha.getBytes(),0,senha.length());
	    return new BigInteger(1,m.digest()).toString(16);
	    
    }

}
