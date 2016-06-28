/**
 *
 */
package br.com.kroton.api.tokens.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.kroton.api.tokens.JwtUtil;
import br.com.kroton.api.tokens.PasswordFilter;
import br.com.kroton.api.tokens.dao.UsuarioDAO;
import br.com.kroton.api.tokens.data.LoginRequest;
import br.com.kroton.api.tokens.data.LoginResponse;
import br.com.kroton.api.tokens.data.Usuario;

/**
 * @author jair.souza
 *
 */
@RestController
public class TokensController {
	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private PasswordFilter passwordFilter;

	@Autowired
	private JwtUtil jwt;

	@RequestMapping(path = "tokens", method = RequestMethod.POST, consumes={"application/json"})
	public LoginResponse login(@RequestBody LoginRequest request) throws JsonProcessingException {

		List<Usuario> usuariosAutenticados = new ArrayList<>();

		if (!StringUtils.isEmpty(request.getCpf()) || !StringUtils.isEmpty(request.getEmail())) {

			List<Usuario> usuarios = usuarioDao.find(request.getCpf(), request.getEmail());

			try {

				usuariosAutenticados = passwordFilter.checkPassword(usuarios, request.getSenha());

			} catch (Exception e) {

				e.printStackTrace();

			}

			usuariosAutenticados = usuarios;//tirar essa linha

			String ra = usuariosAutenticados.get(0).getRa();

			//gerar token

			String tokenJwt = null;
			try {
				tokenJwt = jwt.generateToken(ra , request.getCpf(), request.getEmail(), usuariosAutenticados);
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


			//validar token

			try {
				List<Map<String,Object>> retorno = jwt.parseToken(ra, tokenJwt);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			//gerar response

			return new LoginResponse(tokenJwt);

		}

		return null; //http 406

	}

}
