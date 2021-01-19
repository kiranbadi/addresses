package com.vasanti.web.addresses.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;
/*
{"addressid":1,"addresstype":"house","housenumber":"1013","street":"Delaronde Ln","neareststreet":"Addenbrock drive"
,"city":"morrisville","state":"North Carolina","country":"US","zipcode":"27560","latitude":"100.98"
,"longitude":"-102.32","nearestlandmark":"Harris teeter"}
 */

@Document(collection = "addresses")
public class address implements Serializable {

    @Id
    private String id;
    private Long addressid;
    private String addresstype ; // house/office/commerical/apartment
    private String housenumber;
    private String street;
    private String neareststreet;
    private String city;
    private String state;
    private String country;
    private String zipcode;
    private String latitude;
    private String longitude;
    private String nearestlandmark;

    public address() {
    }

    public address(@JsonProperty("id")String id, @JsonProperty("addressid")Long addressid, @JsonProperty("addresstype")String addresstype, @JsonProperty("housenumber")String housenumber, @JsonProperty("street")String street,  @JsonProperty("neareststreet")String neareststreet,@JsonProperty("city") String city, @JsonProperty("state")String state,@JsonProperty("country") String country, @JsonProperty("zipcode")String zipcode, @JsonProperty("latitude")String latitude,@JsonProperty("longitude") String longitude,@JsonProperty("nearestlandmark") String nearestlandmark) {
        this.id = id;
        this.addressid = addressid;
        this.addresstype = addresstype;
        this.housenumber = housenumber;
        this.street = street;
        this.neareststreet = neareststreet;
        this.city = city;
        this.state = state;
        this.country = country;
        this.zipcode = zipcode;
        this.latitude = latitude;
        this.longitude = longitude;
        this.nearestlandmark = nearestlandmark;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getAddressid() {
        return addressid;
    }

    public void setAddressid(Long addressid) {
        this.addressid = addressid;
    }

    public String getAddresstype() {
        return addresstype;
    }

    public void setAddresstype(String addresstype) {
        this.addresstype = addresstype;
    }

    public String getHousenumber() {
        return housenumber;
    }

    public void setHousenumber(String housenumber) {
        this.housenumber = housenumber;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeareststreet() {
        return neareststreet;
    }

    public void setNeareststreet(String neareststreet) {
        this.neareststreet = neareststreet;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getNearestlandmark() {
        return nearestlandmark;
    }

    public void setNearestlandmark(String nearestlandmark) {
        this.nearestlandmark = nearestlandmark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        address address = (address) o;
        return id.equals(address.id) &&
                addressid.equals(address.addressid) &&
                addresstype.equals(address.addresstype) &&
                housenumber.equals(address.housenumber) &&
                street.equals(address.street) &&
                neareststreet.equals(address.neareststreet) &&
                city.equals(address.city) &&
                state.equals(address.state) &&
                country.equals(address.country) &&
                zipcode.equals(address.zipcode) &&
                latitude.equals(address.latitude) &&
                longitude.equals(address.longitude) &&
                nearestlandmark.equals(address.nearestlandmark);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, addressid, addresstype, housenumber, street, neareststreet, city, state, country, zipcode, latitude, longitude, nearestlandmark);
    }

    @Override
    public String toString() {
        return "address{" +
                "id='" + id + '\'' +
                ", addressid=" + addressid +
                ", addresstype='" + addresstype + '\'' +
                ", housenumber='" + housenumber + '\'' +
                ", street='" + street + '\'' +
                ", neareststreet='" + neareststreet + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", nearestlandmark='" + nearestlandmark + '\'' +
                '}';
    }
}
