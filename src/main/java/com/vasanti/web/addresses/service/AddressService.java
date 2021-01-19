package com.vasanti.web.addresses.service;

import com.vasanti.web.addresses.model.address;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AddressService {
    Mono<address> insert(address addr);
    Flux<address> saveAll(List<address> cities);
    Mono<address> findById(String id);
    Mono<address> findByaddressid(Long addressid);
    Flux<address> findAll();
    Flux<address> findAllByState(String state,Pageable pageable);
    Flux<address> findAllByCity(String city, Pageable pageable);
    Mono<Void> deleteAll();
    Mono<Void> deleteByaddressid(Long addressid);
    Mono<Void> updateByaddressid(Long addressid);


}
