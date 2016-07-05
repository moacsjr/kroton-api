package br.com.kroton.api.alunos;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VersionConfiguration {

	@Value("${api.version}")
	private String apiVersion;
	
	@Bean
	APIUtils getBean(){
		APIUtils api = new APIUtils();
		api.apiVersion = apiVersion;
		return api;
	}
	
}
