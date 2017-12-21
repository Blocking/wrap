package com.example.wrap.JDK8.entity;

import lombok.Data;

/**
 * Created by 12232 on 2017/12/11.
 */
@Data
public class Address {
    private String country;
    private String city;

    public Address(String country, String city) {
        this.country = country;
        this.city = city;
    }
}
