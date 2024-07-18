package com.example.demo.mapper;

import com.example.demo.entity.Essay;
import com.example.demo.entity.RefundApply;
import com.example.demo.entity.TestExt;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface RefundApplyMapper {
    @Select("SELECT * FROM refundapply WHERE state = '待审核'")
    List<RefundApply> findAll();

    @Select("SELECT u_id FROM refundapply WHERE id = #{id}")
    Integer findUidById(@Param("id") int id);
    @Select("SELECT a_id FROM refundapply WHERE id = #{id}")
    Integer findAidById(@Param("id") int id);
    @Update("UPDATE refundapply SET state = '通过审核' WHERE id = #{id}")
    void refundById(@Param("id") int id);
}
