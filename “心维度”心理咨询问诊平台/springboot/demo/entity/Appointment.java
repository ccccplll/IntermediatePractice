package com.example.demo.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class Appointment {
    private Integer id;
    private String state;
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private String consultWay;
    private Timestamp createTime;
}
