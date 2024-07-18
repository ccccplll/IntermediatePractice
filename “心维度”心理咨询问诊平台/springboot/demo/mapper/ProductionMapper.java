package com.example.demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface ProductionMapper {
    @Update("UPDATE production SET message_state = '已发送' WHERE p_id = #{id}")
    void  setProductionMessageState(@Param("id") int id);
}
