package br.com.kroton.api.alunos.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTDecoderFilter implements Filter {

	private JwtUtil jwt;


	public JWTDecoderFilter(JwtUtil jwt) {
		this.jwt = jwt;
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
			handleException(httpResponse, HttpServletResponse.SC_BAD_REQUEST, "901", "http-jwt-header-not-found", "JWT Token not found");
			return;
		}
		
		String ra = getRa(httpRequest);
		
		try {
			jwt.parseToken(ra, authToken);
		} catch (ClassNotFoundException | AcessoNegadoException e) {
			handleException(httpResponse, HttpServletResponse.SC_UNAUTHORIZED, "902", "ra-not-match", "Acesso ao Recurso foi Negado");
			return;
		} catch (JwtTokenValidationException e) {
			handleException(httpResponse, HttpServletResponse.SC_FORBIDDEN, "903", "jwt-token-invalid", "Jwt Token Invalido");
			return;
		}
		
		successfulAuthentication((HttpServletRequest) request, (HttpServletResponse) response, chain);
	}

	@Override
	public void destroy() {

	}
	
    public String getAuthenticationToken(HttpServletRequest request, HttpServletResponse response) throws JwtTokenMissingException {

        String header = request.getHeader("Bearer");

        if (header == null) {
            throw new JwtTokenMissingException("No JWT token found in request headers");
        }

        return header;

        
    }


    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        

        // As this authentication is in HTTP header, after success we need to continue the request normally
        // and return the response as if the resource was not secured at all
        chain.doFilter(request, response);
    }
    
    private String getRa(HttpServletRequest request) {
    	String uri = request.getRequestURI();
    	
    	String[] parts = uri.split("/");
    	
    	return parts[parts.length-1];
    }
    
    private void handleException(HttpServletResponse response, int status, String id, String code, String title) {
    	try {
    		String json = "{\"type\":\"error\", \"id\":\"%s\", \"status\":\"%d\", \"code\":\"%s\", \"title\":\"%s\"}";
    		
    		response.addHeader("Content-Type", "application/json");
    		response.getWriter().write(String.format(json, id, status, code, title));
    		response.setStatus(status);
    		response.flushBuffer();
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
}
