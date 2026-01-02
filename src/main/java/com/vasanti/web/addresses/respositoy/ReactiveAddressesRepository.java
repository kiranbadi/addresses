package com.vasanti.web.addresses.respositoy;

import com.vasanti.web.addresses.model.Address;
import org.springframework.cloud.sleuth.annotation.NewSpan;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ReactiveAddressesRepository extends ReactiveMongoRepository<Address, String>,
        CustomAddressesRepository {

    @NewSpan
    @Query(value="{'addressid' : $0}", delete = true)
    Mono<Void> deleteByAddressId (Long addressid);


    @NewSpan
    Flux<Address> findAllByCity(String city, Pageable pageable);

    @NewSpan
    Flux<Address> findAllByState(String state, Pageable pageable);
}
