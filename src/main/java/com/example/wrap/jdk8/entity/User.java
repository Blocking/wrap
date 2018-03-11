package com.example.wrap.jdk8.entity;

import lombok.Data;

import java.util.Optional;

/**
 * Created by 12232 on 2017/12/9.
 */
@Data
public class User {
    private String userName;
    private String password;

    private String gender;

    private Address address;

    public User(String tom, String s) {
        this.userName = tom;
        this.password  = s;
    }

    public User(String userName, String password, String gender) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
    }

    public User(String userName, Address address) {
        this.userName = userName;
        this.address = address;
    }
    public Optional<Address> getOptionalAddress(){
        return Optional.ofNullable(this.address);
    }
}
