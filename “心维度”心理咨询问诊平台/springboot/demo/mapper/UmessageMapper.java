package com.example.demo.mapper;

import com.example.demo.entity.Page;
import com.example.demo.entity.Umessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface UmessageMapper {
    @Insert("INSERT INTO user_message (u_id, context, create_time,type) VALUES (#{uid}, #{context}, #{createTime}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Umessage umessage);
}
