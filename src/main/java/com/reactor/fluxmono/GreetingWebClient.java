package com.reactor.fluxmono;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class GreetingWebClient {
    private WebClient client = WebClient.create("http://dummy.restapiexample.com/api/v1");

    private Mono<ClientResponse> result = client.get()
            .uri("/employees")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();

    public String getResult() {
        return ">> result = " + result.flatMap(res -> res.bodyToMono(String.class));
    }
}