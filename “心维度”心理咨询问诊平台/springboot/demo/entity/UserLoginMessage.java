package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("loginmessage")
public class UserLoginMessage {
    private Integer id;
    private String device;
    private String ip;
    private String uid;
    private Date loginTime;
}
