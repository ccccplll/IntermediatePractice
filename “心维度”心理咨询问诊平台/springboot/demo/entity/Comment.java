package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment {
    private Integer id;
    private Integer appointId;
    private Integer cid;
    private String content;
    private double score;
    private Date time;
}
