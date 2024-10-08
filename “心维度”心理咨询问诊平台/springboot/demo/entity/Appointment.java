package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    private Integer id;
    private String state;
    private Integer cid;
    private Integer uid;
    private Integer pid;
    private String consultWay;
    private Timestamp createTime;
}
