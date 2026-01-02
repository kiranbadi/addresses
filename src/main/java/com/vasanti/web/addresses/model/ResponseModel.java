package com.vasanti.web.addresses.model;

import lombok.*;
import reactor.core.CoreSubscriber;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.concurrent.Flow;


@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {

    private String requestAcknowledgementId ;

    private String responseMessage;

    private String ErrorMessage ;



}
