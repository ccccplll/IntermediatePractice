package com.example.demo.mapper;

import com.example.demo.entity.Cmessage;
import com.example.demo.entity.Umessage;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;

public interface CmessageMapper {
    @Insert("INSERT INTO consultant_message (c_id, context, create_time,type) VALUES (#{cid}, #{context}, #{createTime}, #{type})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Cmessage cmessage);
}
