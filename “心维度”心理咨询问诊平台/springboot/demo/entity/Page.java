package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("page")
public class Page {
    private Integer id;
    private Integer goodcount;
    private String image;
    private String pagetxt;
    private Date publishTime;
    private String state;
    private Integer txtcount;
    private Integer uid;
}
