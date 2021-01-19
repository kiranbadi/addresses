package com.vasanti.web.addresses.respositoy;

import com.vasanti.web.addresses.model.address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;



import reactor.core.publisher.Mono;

public class CustomAddressesRepositoyImpl implements CustomAddressesRespository {

    private final ReactiveMongoTemplate mongoTemplate;

    @Autowired
    public CustomAddressesRepositoyImpl(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Mono<address> findByaddressid(Long addressid) {
        Query query = new Query(Criteria.where("addressid").is(addressid));
        return mongoTemplate.findOne(query,address.class);
    }

}
