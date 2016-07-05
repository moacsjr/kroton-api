package br.com.kroton.api.alunos;

import javax.servlet.Filter;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.kroton.api.alunos.security.JWTDecoderFilter;
import br.com.kroton.api.alunos.security.JwtUtil;

@Configuration
public class JWTTokenConfiguration {

	@Bean
	public FilterRegistrationBean jwtDecoderFilterRegistration(JwtUtil jwt) {

	    FilterRegistrationBean registration = new FilterRegistrationBean();
	    registration.setFilter(jwtDecoderFilter(jwt));
	    registration.addUrlPatterns("/api/*");
	    //registration.addInitParameter("paramName", "paramValue");
	    registration.setName("jwtDecoderFilter");
	    registration.setOrder(1);
	    return registration;
	}

	@Bean(name = "jwtDecoderFilter")
	public Filter jwtDecoderFilter(JwtUtil jwt) {
	    return new JWTDecoderFilter(jwt);
	}
}
