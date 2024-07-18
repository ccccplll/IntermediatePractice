package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@TableName("consultant_message")
@AllArgsConstructor
@NoArgsConstructor
public class Cmessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "c_id")
    private Integer cid;
    private String context;
    @Column(name = "create_time")
    private Date createTime;
    private String type;
}
