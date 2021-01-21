package com.vasanti.web.addresses.respositoy;

import com.vasanti.web.addresses.model.address;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveAddressesRepository extends ReactiveMongoRepository<address, String>,
        CustomAddressesRespository {

    @NewSpan
    @Query(value="{'addressid' : $0}", delete = true)
    Mono<Void> deleteByaddressid (Long addressid);


    @NewSpan
    Flux<address> findAllByCity(String city, Pageable pageable);

    @NewSpan
    Flux<address> findAllByState(String state,Pageable pageable);
}
