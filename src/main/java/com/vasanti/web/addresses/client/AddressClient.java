package com.vasanti.web.addresses.client;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class AddressClient {

    private final WebClient client = WebClient.create("https://localhost:8443");

    private final Mono<ClientResponse> result = client.get()
            .uri("/test")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();


    public String getResult() {
        return result.flatMap(res -> res.bodyToMono(String.class)).block();
    }







}
