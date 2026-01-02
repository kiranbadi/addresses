package com.vasanti.web.addresses.service;

import com.vasanti.web.addresses.model.Address;
import com.vasanti.web.addresses.respositoy.ReactiveAddressesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
@Log4j2
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {


    private final ReactiveAddressesRepository reactiveAddressesRepository;

    @Override
    public Mono<Address> insert(Address addr) {
        log.info("Address value in DAO -\n {}", addr);
        return reactiveAddressesRepository.save(addr);
    }

    @Override
    public Flux<Address> saveAll(List<Address> Addresses) {
        return reactiveAddressesRepository.saveAll(Addresses);
    }

    @Override
    public Mono<Address> findById(String id) {
        return reactiveAddressesRepository.findById(id);
    }

    @Override
    @NewSpan
    public Mono<Address> findByAddressId(Long addressid) {
        return reactiveAddressesRepository.findByAddressId(addressid);
    }

    @Override
    public Flux<Address> findAll() {
        return reactiveAddressesRepository.findAll();
    }

    @Override
    public Flux<Address> findAllByState(String state, Pageable pageable) {
        return reactiveAddressesRepository.findAllByState(state,pageable);
    }

    @Override
    public Flux<Address> findAllByCity(String city, Pageable pageable) {
        return reactiveAddressesRepository.findAllByCity(city,pageable);
    }

    @Override
    public Mono<Void> deleteAll() {
        return reactiveAddressesRepository.deleteAll();
    }

    @Override
    public Mono<Void> deleteByAddressId(Long addressid) {
        return reactiveAddressesRepository.deleteByAddressId(addressid);
    }

    @Override
    public Mono<Void> updateByAddressId(Long addressid) {
       return null;

    }
}
