package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Announcement;
import com.example.demo.entity.Consultant;
import com.example.demo.entity.Essay;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface EssayMapper extends BaseMapper<Essay> {
    @Select("<script>" +
            "SELECT e.*, i.img_src as image " +
            "FROM essay e " +
            "JOIN essay_image i ON i.e_id = e.id " +
            "WHERE e.state = '未通过审核' " +
            "</script>")
    List<Essay> findAll();
//    @Select("SELECT * FROM essay WHERE state = '未通过审核'")
//    List<Essay> findAll();

    @Update("UPDATE essay SET state = '已通过审核' WHERE id = #{id}")
    void passEssayById(@Param("id") int id);
}
