package br.com.kroton.api.alunos;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.kroton.api.alunos.security.CORSFilter;
import br.com.kroton.api.alunos.security.JWTDecoderFilter;
import br.com.kroton.api.alunos.security.JwtDecoderUtil;

@Configuration
public class APISecurityConfiguration {
	
	@Value("${api.version}")
	private String apiVersion;
	
	@Bean
	public FilterRegistrationBean corsFilterRegistration(JwtDecoderUtil jwt) {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(corsFilter());
	    registration.addUrlPatterns("/api/*");
	    registration.setName("CORS_Filter");
	    registration.setOrder(1);
	    return registration;
	}

	@Bean
	public Filter corsFilter() {
		return new CORSFilter();
	}


	@Bean
	public FilterRegistrationBean jwtDecoderFilterRegistration(JwtDecoderUtil jwt) {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(jwtDecoderFilter(jwt));
	    registration.addUrlPatterns("/api/*");
	    //registration.addInitParameter("paramName", "paramValue");
	    registration.setName("jwtDecoderFilter");
	    registration.setOrder(1);
	    return registration;
	}

	@Bean(name = "jwtDecoderFilter")
	public Filter jwtDecoderFilter(JwtDecoderUtil jwt) {
	    return new JWTDecoderFilter(jwt, apiVersion);
	}
}
