package com.vasanti.web.addresses.routes;

import com.vasanti.web.addresses.controller.AddressController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;


@Configuration
public class AddressRouters {
    @Bean
    public RouterFunction<ServerResponse> route(AddressController addressController) {

        return RouterFunctions
                .route(RequestPredicates.GET("/test")
                        .and(RequestPredicates
                                .accept(MediaType.TEXT_PLAIN)),
                        addressController::TestSetUp);
    }

}
