package com.example.demo.entity;

import lombok.Data;

import java.sql.Timestamp;
@Data
public class AppointmentQueryParams {
    private Integer id;
    private String state;
    private String cname;
    private String uname;
    private String consultWay;
    private Timestamp createTime;
    private Integer pageNum;
    private Integer pageSize;
}
