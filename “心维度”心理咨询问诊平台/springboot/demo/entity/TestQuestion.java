package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
@TableName("testquestions")
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

