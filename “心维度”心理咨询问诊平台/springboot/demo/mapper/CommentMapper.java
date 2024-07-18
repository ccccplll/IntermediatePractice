package com.example.demo.mapper;

import com.example.demo.entity.ApplyExt;
import com.example.demo.entity.CommentExt;
import org.apache.ibatis.annotations.Param;

import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface CommentMapper {
    @Select("<script>" +
            "SELECT c.*, con.trueName as cname, u.name as uname " +
            "FROM comment c " +
            "JOIN consultant con ON c.c_id = con.id " +
            "JOIN appointment a ON c.appoint_id = a.id " +
            "JOIN user u ON a.u_id = u.id " +
            "<where> " +
            " <if test='cname != null'> AND con.trueName = #{cname} </if>" +
            " <if test='uname != null'> AND u.name = #{uname} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<CommentExt> queryComments(@Param("offset") int offset,
                                   @Param("pageSize") int pageSize,
                                   @Param("uname") String uname,
                                   @Param("cname") String cname);
    @Select("SELECT COUNT(*) FROM consultant")
    int getTotalComments();
}
