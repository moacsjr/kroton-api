/**
 *
 */
package br.com.kroton.api.tokens.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.kroton.api.tokens.JwtUtil;
import br.com.kroton.api.tokens.PasswordFilter;
import br.com.kroton.api.tokens.dao.UsuarioDAO;
import br.com.kroton.api.tokens.data.AutenticacaoRequest;
import br.com.kroton.api.tokens.data.LoginRequest;
import br.com.kroton.api.tokens.data.LoginResponse;
import br.com.kroton.api.tokens.data.Usuario;

/**
 * @author jair.souza
 *
 */
@RestController
@RequestMapping(value="/api")
public class TokensController {
	
	private Log LOG = LogFactory.getLog(TokensController.class);
	
	@Autowired
	private UsuarioDAO usuarioDao;

	@Autowired
	private PasswordFilter passwordFilter;

	@Autowired
	private JwtUtil jwt;

	@Value("${api.version}")
	private String apiVersion;

	@RequestMapping(path = "tokens", method = RequestMethod.POST, consumes={"application/json"})
	public ResponseEntity<LoginResponse> login(@RequestBody AutenticacaoRequest autenticacao) throws RestAPIException {

		List<Usuario> usuariosAutenticados = new ArrayList<>();
		
		if(autenticacao.getAutenticacao() == null){
			
			ClientErrorInformation erro = new ClientErrorInformation(HttpStatus.BAD_REQUEST, "PDA-905", "INVALID CONTENT", "Invalid Request Data", null);
			
			throw new RestAPIException(erro);
		}
		
		LoginRequest request = autenticacao.getAutenticacao();

		if (!StringUtils.isEmpty(request.getCpf()) || !StringUtils.isEmpty(request.getEmail())) {

			LOG.info(String.format("Processing login request for [CPF=%s] and [email=%s]", request.getCpf(), request.getEmail()));
			
			List<Usuario> usuarios = usuarioDao.find(request.getCpf(), request.getEmail());
			
			if(usuarios.size() == 0){
				
				ClientErrorInformation erro = new ClientErrorInformation(HttpStatus.NOT_FOUND, "PDA-901", "NOT_FOUND", "NOT_FOUND", null);
				
				throw new RestAPIException(erro);
				
			}

			try {

				usuariosAutenticados = passwordFilter.checkPassword(usuarios, request.getSenha());
				
				LOG.info("Usuários autenticados: "+usuariosAutenticados.size());
				

			} catch (Exception e) {

				LOG.error("Ocorreu um erro durante a validação da senha do usuário", e);
				
				ClientErrorInformation erro = new ClientErrorInformation(HttpStatus.INTERNAL_SERVER_ERROR, "PDA-903", "FALHA NA VERIFICACAO DA SENHA", e.getMessage(), null);
				
				throw new RestAPIException(erro);
				
			}

			if(usuariosAutenticados.size() == 0){
				
				ClientErrorInformation erro = new ClientErrorInformation(HttpStatus.UNAUTHORIZED, "PDA-902", "ACESSO NEGADO", "PASSWORD CHECK", null);
				
				throw new RestAPIException(erro);
				
			}
			//usuariosAutenticados = usuarios;//tirar essa linha

			String ra = usuariosAutenticados.get(0).getRa();
			String sistema = usuariosAutenticados.get(0).getSistema();

			//gerar token

			String tokenJwt = null;
			
			try {
				
				tokenJwt = jwt.generateToken(ra , request.getCpf(), request.getEmail(), sistema, usuariosAutenticados);
				
				LOG.info(String.format("[RA=%S] [SISTEMA=%S] - JWT Token: %S", ra, sistema, tokenJwt));
				
				
			} catch (ClassNotFoundException | IOException e1) {
				
				LOG.error("Erro ao tentar gerar o token jwt: ", e1);
				
				ClientErrorInformation erro = new ClientErrorInformation(HttpStatus.INTERNAL_SERVER_ERROR, "PDA-904", "ERRO AO GERAR TOKEN", e1.getMessage(), null);
				
				throw new RestAPIException(erro);
			}


//			//validar token
//
//			try {
//				List<Map<String,Object>> retorno = jwt.parseToken(ra, tokenJwt);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

			//gerar response

			return new ResponseEntity<LoginResponse>(new LoginResponse(tokenJwt, apiVersion), HttpStatus.OK);

		}
		LOG.info("Invalid Request ");
		return new ResponseEntity<LoginResponse>(HttpStatus.BAD_REQUEST); //http 406

	}
	
	
	@ExceptionHandler(RestAPIException.class)
	public ResponseEntity<ClientErrorInformation> rulesForCustomerNotFound(HttpServletRequest req, Exception e) 
	{
	ClientErrorInformation error = ((RestAPIException)e).getInfo();
	return new ResponseEntity<ClientErrorInformation>(error, error.getHttpStatus());
	}

}
