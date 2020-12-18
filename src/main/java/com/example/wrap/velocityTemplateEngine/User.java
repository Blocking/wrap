package com.example.wrap.velocityTemplateEngine;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by 12232 on 2018/1/27.
 */
@Data
public class User implements Serializable {
    @Getter
    @Setter
    public String name;
    private String password;
    private String email;
    private Integer age;
    private Date birthday;
}
