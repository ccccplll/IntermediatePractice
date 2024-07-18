package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("pagecontent")
public class PageContent {
    private Integer id;
    private String content;
    private Date createTime;
    private Integer tid;
    private Integer uid;
}
