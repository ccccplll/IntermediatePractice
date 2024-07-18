package com.example.demo.mapper;

import com.example.demo.entity.PageContentExt;
import com.example.demo.entity.Treehole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TreeholeMapper {
//    @Select("<script>" +
//            "SELECT t.*, u.name as uname, c.trueName as cname " +
//            "FROM treehole t " +
//            "JOIN user u ON t.u_id = u.id " +
//            "JOIN consultant c ON t.c_id = c.id " +
//            "<where> " +
//            " <if test='state != null'> AND state = '未回信' AND t.receive = null </if>" +
//            " <if test='state != null'> AND state = '已回信' AND t.receive != null </if>" +
//            "</where> " +
//            "LIMIT #{offset}, #{pageSize}" +
//            "</script>")
//    List<Treehole> queryTreeholes(@Param("offset") int offset,
//                                  @Param("pageSize") int pageSize,
//                                  @Param("state") String state
//                                  );

    @Select("<script>" +
            "SELECT t.*, u.name as uname, c.trueName as cname " +
            "FROM treehole t " +
            "JOIN user u ON t.u_id = u.id " +
            "LEFT JOIN consultant c ON t.c_id = c.id " +
            "<where> " +
            " <choose> " +
            "   <when test='state != null and state == \"未回信\"'> " +
            "      AND t.receive IS NULL " +
            "   </when> " +
            "   <when test='state != null and state == \"已回信\"'> " +
            "      AND t.receive IS NOT NULL " +
            "   </when> " +
            " </choose> " +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<Treehole> queryTreeholes(@Param("offset") int offset,
                                  @Param("pageSize") int pageSize,
                                  @Param("state") String state);



    @Select("SELECT COUNT(*) FROM treehole")
    int getTotalTreeholes();
   }
