package br.com.kroton.api.tokens;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.kroton.api.tokens.data.Usuario;

@Component
public class PasswordFilter {
	
	public List<Usuario> checkPassword(List<Usuario> usuarios, String senhaDescriptografada) throws NoSuchAlgorithmException{
		
		List<Usuario> usuariosAutenticados = new ArrayList<Usuario>();
		
		for ( Usuario usuario:usuarios ) {
			
			String hashMD5 = gerarHashMD5(senhaDescriptografada.trim());

			if (usuario.getSenha().equalsIgnoreCase(hashMD5)) {
				
				usuariosAutenticados.add(usuario);
				
			}
    		
    	}

 	   	return usuariosAutenticados;
	}

	
	private String gerarHashMD5 (String senha) throws NoSuchAlgorithmException {

		MessageDigest m=MessageDigest.getInstance("MD5");
		m.update(senha.getBytes(),0,senha.length());
	    return new BigInteger(1,m.digest()).toString(16);
	    
    }

}
