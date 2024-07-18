package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginMessageQueryParams {
    private Integer pageNum;
    private Integer pageSize;
    private Date loginTime;
}
