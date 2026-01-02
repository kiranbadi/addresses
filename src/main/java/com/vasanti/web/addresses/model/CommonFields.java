package com.vasanti.web.addresses.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;


@Getter
@Setter
@ToString
public class CommonFields {

    private Timestamp created_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;
    private String created_by;
    private String updated_by;
}