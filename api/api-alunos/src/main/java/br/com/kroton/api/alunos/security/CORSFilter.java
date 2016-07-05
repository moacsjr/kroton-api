package br.com.kroton.api.alunos.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements javax.servlet.Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletResponse resp = (HttpServletResponse) response;
		resp.addHeader("Access-Control-Allow-Origin", "*");
		//headers.add("Access-Control-Allow-Origin", "http://podcastpedia.org"); //allows CORS requests only coming from podcastpedia.org		
		resp.addHeader("Access-Control-Allow-Methods", "GET");			
		resp.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, X-Codingpedia");
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		
	}
	
	

}
