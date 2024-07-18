package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User {
    private Integer id;
    private String name;
    private String city;
    private Integer age;
    private String password;
    private String uCondition;
    private String sex;
    private String email;
    private String phoneNumber;
    private String avatar;
    private String seemessage;

    public User(String name, Integer age, String city) {
        this.name=name;
        this.age=age;
        this.city=city;
    }
}
