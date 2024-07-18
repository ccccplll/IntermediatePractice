package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeholeQueryParams {
    private Integer pageNum;
    private Integer pageSize;
    private String state;
//    private String uname;
//    private String cname;
}
