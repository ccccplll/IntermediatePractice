package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("refundapply")
public class RefundApply {
    Integer id;
    Integer aid;
    Integer uid;
    String context;
    Date submitTime;
    String state;
}
