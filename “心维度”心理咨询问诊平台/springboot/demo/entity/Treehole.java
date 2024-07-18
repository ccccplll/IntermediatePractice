package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("treehole")
public class Treehole {
    private Integer id;
    private Integer cid;
    private Integer uid;
    private String send;
    private String receive;
    private String uname;
    private String cname;
}
