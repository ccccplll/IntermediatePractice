package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("test")
public class Test {
    private Integer id;
    private Integer score;
    private String condition;
    private Timestamp time;
    private Integer uid;
}
