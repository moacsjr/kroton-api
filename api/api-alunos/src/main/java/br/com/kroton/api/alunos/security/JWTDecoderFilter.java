package br.com.kroton.api.alunos.security;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.kroton.api.alunos.exception.APIErrorResponse;
import br.com.kroton.api.alunos.exception.AcessoNegadoException;
import br.com.kroton.api.alunos.exception.JwtTokenMissingException;
import br.com.kroton.api.alunos.exception.JwtTokenValidationException;

public class JWTDecoderFilter implements Filter {

	Log LOG = LogFactory.getLog(JWTDecoderFilter.class);
	
	private static final String HTTP_AUTH_HEADER = "Authorization";
	private static final String JWT_TOKEN_PREFIX = "Bearer ";
	private JwtDecoderUtil jwt;
	private String apiVersion;


	public JWTDecoderFilter(JwtDecoderUtil jwt, String apiVersion) {
		this.jwt = jwt;
		this.apiVersion = apiVersion;
	}
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		String authToken;
		
		try {
		
			authToken = getAuthenticationToken(httpRequest, httpResponse);
			
		} catch (JwtTokenMissingException e) {
			
			LOG.error("Erro ao validar header de autenticacao: " + e.getMessage());
			
			handleException(httpResponse, HttpServletResponse.SC_BAD_REQUEST, "901", "http-jwt-header-not-found", "JWT Token not found", "O cabeçalho de segurança não foi encotrado ou não é váldio.");
			
			return;
			
		}
		
		LOG.debug("Validando RA e Sistema");
		
		String ra = getRa(httpRequest);
		
		String sistema = getSistema(httpRequest);
		
		LOG.debug(String.format("Obteve RA(%s) e Sistema(%s)", ra, sistema));
		
		try {
			
			jwt.parseToken(ra, sistema, authToken);
			
		} catch (ClassNotFoundException | AcessoNegadoException e) {
			
			handleException(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "902", "ra-not-match", "Acesso ao Recurso foi Negado", "O RA ou Sistema do usuário autenticado não é compatível com os dados informados na requisicao");
			
			return;
			
		} catch (JwtTokenValidationException e) {
			
			handleException(httpResponse, HttpServletResponse.SC_FORBIDDEN, "903", "jwt-token-invalid", "Jwt Token Invalido", "O token JWT informando não está bem formado e foi considerado inválido");
			
			return;
			
		}
		
		successfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	@Override
	public void destroy() {

	}
	
    public String getAuthenticationToken(HttpServletRequest request, HttpServletResponse response) throws JwtTokenMissingException {

        String header = request.getHeader(HTTP_AUTH_HEADER);

        LOG.debug(String.format("Verificando cabecalho de authenticacao: %s", header));
        
        if (header == null) {
            throw new JwtTokenMissingException("No authorization header found in request");
        }
        
        if (!header.startsWith(JWT_TOKEN_PREFIX)) {
            throw new JwtTokenMissingException("Malformed JWT header found in request");
        }
        
        return header.substring(7);

    }


    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
    	
    	LOG.debug("Token JWT autenticado com sucesso!");
        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
    
    private String getRa(HttpServletRequest request) {
    	String uri = request.getRequestURI();
    	
    	Pattern urlPatt = Pattern.compile(".*\\/alunos\\/(\\d*)");
    	Matcher m = urlPatt.matcher(uri);
    	String ra = null;
		if (m.matches()) {
    		ra   = m.group(1);
    	}
    	
    	return ra;
    }
    
    private String getSistema(HttpServletRequest request) {
    	String uri = request.getQueryString();
    	
    	Pattern urlPatt = Pattern.compile(".*\\[sistema\\]=(\\w*)");
    	Matcher m = urlPatt.matcher(uri.toLowerCase());
    	String sistema = null;
		if (m.matches()) {
    		sistema   = m.group(1);
    	}
    	
    	return sistema.toUpperCase();
    }
    
    private void handleException(HttpServletResponse response, int status, String id, String code, String title, String detail) {
    	try {
    		
    		APIErrorResponse errorModel = new APIErrorResponse(code, title, detail, apiVersion);
    		
    		ObjectMapper jsonMapper = new ObjectMapper();
    		
            String json = jsonMapper.writeValueAsString(errorModel);
    		
    		response.addHeader("Content-Type", "application/json");
    		
    		response.setStatus(status);
    		
    		response.getWriter().write(json);
    		
    		response.flushBuffer();
    		
    	} catch (Exception e) {
    		
    		e.printStackTrace();
    		
    	}
    }

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}
}
