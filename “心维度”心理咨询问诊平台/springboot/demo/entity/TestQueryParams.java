package com.example.demo.entity;

import lombok.Data;

@Data
public class TestQueryParams {
    private String name;
    private Integer id;
    private Integer uid;
    private String condition;
    private Integer pageNum;
    private Integer pageSize;
}
