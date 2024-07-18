package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("essay")
public class Essay {
    private Integer id;
    private Integer cid;
    private String content;
    private Integer good;
    private String state;
    private Date time;
    private String title;
    private String type;
    private String image;
}
