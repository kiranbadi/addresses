package com.vasanti.web.addresses.respositoy;

import com.vasanti.web.addresses.model.Address;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;


import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@Repository
@Log4j2
public class CustomAddressesRepositoryImpl implements CustomAddressesRepository {

    private final ReactiveMongoTemplate mongoTemplate;


    @Override
    public Mono<Address> findByAddressId(Long addressid) {
        Query query = new Query(Criteria.where("addressid").is(addressid));
        return mongoTemplate.findOne(query, Address.class);
    }

}
