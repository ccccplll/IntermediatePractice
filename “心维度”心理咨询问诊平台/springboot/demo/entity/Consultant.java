package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("consultant")
public class Consultant {
    private Integer id;
    private String trueName;
    private Integer accumulatedHour;
    private String city;
    private Integer age;
    private String certificationStatus;
    private String password;
    private String strength;
    private String sex;
    private String email;
    private String phoneNumber;
    private String avatar;

    public Consultant(String trueName, Integer age, String city) {
        this.trueName=trueName;
        this.age=age;
        this.city=city;

    }
}
