package com.example.demo.entity;
import lombok.Data;
import lombok.Getter;

@Data
public class UserQueryParams {
    private String name;
    private String city;
    private Integer age;

    private Integer pageNum;
    private Integer pageSize;
    //    private String email;
    private String sex;
    private Integer id;

}
