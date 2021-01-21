package com.vasanti.web.addresses.service;

import com.vasanti.web.addresses.model.address;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AddressService {
    @NewSpan
    Mono<address> insert(address addr);

    @NewSpan
    Flux<address> saveAll(List<address> cities);

    @NewSpan
    Mono<address> findById(String id);

    @NewSpan
    Mono<address> findByaddressid(Long addressid);

    @NewSpan
    Flux<address> findAll();

    @NewSpan
    Flux<address> findAllByState(String state,Pageable pageable);

    @NewSpan
    Flux<address> findAllByCity(String city, Pageable pageable);

    @NewSpan
    Mono<Void> deleteAll();

    @NewSpan
    Mono<Void> deleteByaddressid(Long addressid);

    @NewSpan
    Mono<Void> updateByaddressid(Long addressid);


}
