package com.example.demo.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CommentQueryParams {
    private String uname;
    private String cname;
    private Integer pageNum;
    private Integer pageSize;
}
