package com.example.demo.entity;

import lombok.Getter;
import lombok.Data;

@Data
public class ConsultantQueryParams {
    private String trueName;
    private String city;
    private Integer age;

    private Integer pageNum;
    private Integer pageSize;
    private String sex;
    private Integer id;
}


