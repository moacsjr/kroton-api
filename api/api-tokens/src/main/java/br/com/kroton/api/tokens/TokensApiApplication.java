package br.com.kroton.api.tokens;

import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class TokensApiApplication {

	@Autowired(required = true)
	Environment env;

	@Bean
	public CountDownLatch closeLatch() {
		return new CountDownLatch(1);
	}

	public static void main(String[] args) throws Exception {
		SpringApplicationBuilder app = new SpringApplicationBuilder(TokensApiApplication.class);
		app.registerShutdownHook(true);
		app.main(TokensApiApplication.class);
		app.run(args);

		final CountDownLatch closeLatch = app.context().getBean(CountDownLatch.class);
		Runtime.getRuntime().addShutdownHook(new Thread() {
			@Override
			public void run() {
				closeLatch.countDown();
			}
		});
		closeLatch.await();

		// SpringApplication.run(UserServiceApplication.class, args);
	}

}