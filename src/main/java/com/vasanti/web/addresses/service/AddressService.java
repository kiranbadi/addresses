package com.vasanti.web.addresses.service;

import com.vasanti.web.addresses.model.Address;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AddressService {
    @NewSpan
    Mono<Address> insert(Address addr);

    @NewSpan
    Flux<Address> saveAll(List<Address> cities);

    @NewSpan
    Mono<Address> findById(String id);

    @NewSpan
    Mono<Address> findByAddressId(Long addressid);

    @NewSpan
    Flux<Address> findAll();

    @NewSpan
    Flux<Address> findAllByState(String state, Pageable pageable);

    @NewSpan
    Flux<Address> findAllByCity(String city, Pageable pageable);

    @NewSpan
    Mono<Void> deleteAll();

    @NewSpan
    Mono<Void> deleteByAddressId(Long addressid);

    @NewSpan
    Mono<Void> updateByAddressId(Long addressid);


}
