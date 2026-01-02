package com.vasanti.web.addresses.respositoy;

import com.vasanti.web.addresses.model.Address;
import reactor.core.publisher.Mono;

public interface CustomAddressesRepository {

    Mono<Address> findByAddressId(Long addressid);




}
