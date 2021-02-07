package com.reactor.fluxmono;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

//@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setWebApplicationType(WebApplicationType.NONE);
        app.run(args);

        GreetingWebClient gwc = new GreetingWebClient();
        System.out.println(gwc.getResult());
    }

	/*@Bean
	public CommandLineRunner myCommandLineRunner() {
		return args -> {
			// we have to block here, since command line runners don't
			// consume reactive types and simply return after the execution
			String result = ">> result = " + WebClient.create("http://localhost:8080")
					.get()
					.uri("/hello")
					//.body("Hallo")
					.accept(MediaType.TEXT_PLAIN)
					.exchange()
					.flatMap(res -> res.bodyToMono(String.class));
		};
	}*/
}