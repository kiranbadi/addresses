package com.vasanti.web.addresses.service;

import com.vasanti.web.addresses.model.address;
import com.vasanti.web.addresses.respositoy.ReactiveAddressesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


@Service
public class AddressServiceImpl implements AddressService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddressServiceImpl.class);

    private final ReactiveAddressesRepository reactiveAddressesRepository;


    @Autowired
    public AddressServiceImpl(ReactiveAddressesRepository reactiveAddressesRepository) {
        this.reactiveAddressesRepository = reactiveAddressesRepository;
    }




    @Override
    public Mono<address> insert(address addr) {
        LOGGER.info("Address value in DAO -\n {}", addr);
        return reactiveAddressesRepository.save(addr);
    }

    @Override
    public Flux<address> saveAll(List<address> addresses) {
        return reactiveAddressesRepository.saveAll(addresses);
    }

    @Override
    public Mono<address> findById(String id) {
        return reactiveAddressesRepository.findById(id);
    }

    @Override
    @NewSpan
    public Mono<address> findByaddressid(Long addressid) {
        return reactiveAddressesRepository.findByaddressid(addressid);
    }

    @Override
    public Flux<address> findAll() {
        return reactiveAddressesRepository.findAll();
    }

    @Override
    public Flux<address> findAllByState(String state,Pageable pageable) {
        return reactiveAddressesRepository.findAllByState(state,pageable);
    }

    @Override
    public Flux<address> findAllByCity(String city, Pageable pageable) {
        return reactiveAddressesRepository.findAllByCity(city,pageable);
    }

    @Override
    public Mono<Void> deleteAll() {
        return reactiveAddressesRepository.deleteAll();
    }

    @Override
    public Mono<Void> deleteByaddressid(Long addressid) {
        return reactiveAddressesRepository.deleteByaddressid(addressid);
    }

    @Override
    public Mono<Void> updateByaddressid(Long addressid) {
       return null;

    }
}
