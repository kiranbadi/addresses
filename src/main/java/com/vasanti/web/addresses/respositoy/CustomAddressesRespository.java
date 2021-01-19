package com.vasanti.web.addresses.respositoy;

import com.vasanti.web.addresses.model.address;
import org.springframework.data.mongodb.repository.Query;
import reactor.core.publisher.Mono;

public interface CustomAddressesRespository {

    Mono<address> findByaddressid(Long addressid);




}
