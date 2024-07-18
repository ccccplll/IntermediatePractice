package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.ApplyExt;
import com.example.demo.entity.Test;
import com.example.demo.entity.TestExt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;


import java.util.List;

public interface TestMapper extends BaseMapper<Test> {
    @Select("<script>" +
            "SELECT t.*, u.name as name " +
            "FROM test t " +
            "JOIN user u ON t.u_id = u.id " +
            "<where> " +
            " <if test='uid != null'> AND t.U_id = #{uid} </if>" +
            " <if test='name != null'> AND u.name = #{name} </if>" +
            " <if test='id != null'> AND t.id = #{id} </if>" +
            " <if test='condition != null'> AND t.condition = #{condition} </if>" +
            "</where> " +
            "ORDER BY t.id DESC " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    @Results({
            @Result(property = "condition", column = "t_condition"),
            @Result(property = "uid", column = "u_id"),
    })
    List<TestExt> queryTests(@Param("offset") int offset,
                             @Param("pageSize") int pageSize,
                             @Param("name") String name,
                             @Param("condition") String condition,
                             @Param("id") Integer id,
                             @Param("uid") Integer uid
                              );
    @Select("SELECT COUNT(*) FROM test")
    int getTotalTests();
}
