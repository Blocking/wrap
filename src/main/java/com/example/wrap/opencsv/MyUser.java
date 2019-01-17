package com.example.wrap.opencsv;

import lombok.Data;

/**
 * Create by zhangxy on 2019/1/16 9:50
 */
@Data
public class MyUser {
    private String name;
    private String email;
    private String phoneNo;
    private String country;

    public MyUser() {

    }

    public MyUser(String name, String email, String phoneNo, String country) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.country = country;
    }
}
