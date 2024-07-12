package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestQuestionQueryParams {
    private Integer pageNum;
    private Integer pageSize;
    //    private String email;
    private Date date;
}
