/**
 * 
 */
package br.com.kroton.api.tokens.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import br.com.kroton.api.tokens.data.Usuario;

/**
 * @author jair.souza
 *
 */
@Mapper
public interface UsuarioDAO {
	public List<Usuario> find(String cpf, String email);
}
