package com.vasanti.web.addresses.service;


import com.vasanti.web.addresses.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceQueries {
    private final MongoTemplate mongoTemplate;

    @Autowired
    public AddressServiceQueries(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public List<Address> findAll(String field,
                                 int pageNb,
                                 int pageSize) {
        Query allPagedAndOrdered = new Query()
                .with(Sort.by(Sort.Direction.ASC, field))
                .with(PageRequest.of(pageNb, pageSize));

        return this.mongoTemplate.find(allPagedAndOrdered, Address.class);
    }

    public Address findSingleById(String id) {
        return this.mongoTemplate.findById(id, Address.class);
    }

    public long countZipcodes(Integer zipcode) {
        Query zipcodes = Query.query(Criteria.where("zipcode")
                .is(zipcode));
        return this.mongoTemplate.count(zipcodes, Address.class);
    }

    public List<Address> findByCity(String city) {
        Query byCity = new Query()
                .addCriteria(Criteria.where("city").is(city));
        return this.mongoTemplate.find(byCity, Address.class);
    }

    public List<Address> findByAddressIdBetween(int fromaddressid, int toaddressid) {
        Query byAddressIdBetween = Query
                .query(Criteria.where("addressid")
                        .gte(fromaddressid)
                        .lte(toaddressid))
                .with(Sort.by(Sort.Direction.DESC, "addressid"));
        return this.mongoTemplate.find(byAddressIdBetween, Address.class);
    }

    public List<Address> findByStreet(String street, String city) {
        Query ByStreet = Query.query(Criteria
                .where("city").is(city)
                .and("street").is(street));

        return this.mongoTemplate.find(ByStreet, Address.class);
    }

    public List<Address> findRelatedToCityAndHouseNumber(String city, String housenumber, String state) {
        Query byCityAndHousenumber = Query.query(
                new Criteria()
                        .orOperator(
                                Criteria.where("city").is(city),
                                Criteria.where("housenumber").is(housenumber))
                        .andOperator(
                                Criteria.where("state").is(state)
                        )
        );

        return this.mongoTemplate.find(byCityAndHousenumber, Address.class);
    }


    public List<Address> findByHouseNumber(String housenumber) {
        Query byhouseNumber = Query.query(Criteria.where("housenumber").is(housenumber));
        return this.mongoTemplate.find(byhouseNumber, Address.class);
    }

    public List<Address> findByText(String text) {
        TextCriteria textCriteria = TextCriteria
                .forDefaultLanguage()
                .matching(text);

        Query byFreeText = TextQuery.queryText(textCriteria)
                .sortByScore()
                .with(PageRequest.of(0, 10));

        return this.mongoTemplate.find(byFreeText, Address.class);
    }
}