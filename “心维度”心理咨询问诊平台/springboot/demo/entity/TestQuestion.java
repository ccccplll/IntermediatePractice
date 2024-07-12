package com.example.demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TestQuestion {
    private Integer id;
    private Date date;
    private Integer scoreA;
    private Integer scoreB;
    private Integer scoreC;
    private Integer scoreD;
    private String questionContext;
    private String a;
    private String b;
    private String c;
    private String d;
}

