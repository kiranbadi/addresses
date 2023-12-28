package com.vasanti.web.addresses.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;


@Getter
@Setter
@Document(collection = "addresses")
public class address implements Serializable {

    @Id
    private String id;
    private Long addressid;
    private String addressType ; // house/office/commercial/apartment
    private String houseNumber;
    private String street;
    private String nearestStreet;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String latitude;
    private String longitude;
    private String nearestLandmark;
}
