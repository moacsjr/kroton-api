package br.com.kroton.api.alunos;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.katharsis.resource.registry.ResourceRegistry;
import io.katharsis.spring.boot.KatharsisConfigV2;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@SpringBootApplication
@Import(KatharsisConfigV2.class)
public class AlunoAPIApplication {
	
    @Autowired
    private ResourceRegistry resourceRegistry;

    @RequestMapping("/resourcesInfo")
    public Map<?, ?> getResources() {
        Map<String, String> result = new HashMap<>();
        for (Class<?> clazz : resourceRegistry.getResources().keySet()) {
           result.put(resourceRegistry.getResourceType(clazz), resourceRegistry.getResourceUrl(clazz));
        }
        return result;
    }
	
	@Autowired( required=true)
    Environment env;
	
	@Bean
	public CountDownLatch closeLatch() {
	    return new CountDownLatch(1);
	}
	
    public static void main(String[] args) throws Exception {
    	SpringApplicationBuilder app = new SpringApplicationBuilder(AlunoAPIApplication.class);
    	app.registerShutdownHook(true);
    	app.main(AlunoAPIApplication.class);
    	app.run(args);
    	
    	final CountDownLatch closeLatch = app.context().getBean(CountDownLatch.class);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                closeLatch.countDown();
            }
        });
        closeLatch.await();
    	
    }

}