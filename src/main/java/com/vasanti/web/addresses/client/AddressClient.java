package com.vasanti.web.addresses.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class AddressClient {

    private WebClient client = WebClient.create("https://localhost:8443");

    private Mono<ClientResponse> result = client.get()
            .uri("/test")
            .accept(MediaType.TEXT_PLAIN)
            .exchange();


    public String getResult() {
        return result.flatMap(res -> res.bodyToMono(String.class)).block();
    }







}
