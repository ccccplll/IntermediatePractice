package com.example.demo.mapper;

import com.example.demo.entity.PageContent;
import com.example.demo.entity.PageContentExt;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PageContentMapper {

    @Select("<script>" +
            "SELECT p.*, u.name, u.avatar " +
            "FROM pagecontent p " +
            "JOIN user u ON p.u_id = u.id " +
            "<where> " +
            " <if test='id != null'> AND p.t_id = #{id} </if>" +
            "</where> " +
            "</script>")
    List<PageContentExt> findById(@Param("id") Integer id);

}
