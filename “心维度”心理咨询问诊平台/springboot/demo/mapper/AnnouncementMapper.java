package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface AnnouncementMapper extends BaseMapper<Announcement> {
    @Select("SELECT * FROM announcement")
    List<Announcement> findAll();

    @Select("SELECT * FROM announcement WHERE id = #{id}")
    Announcement findById(int id);

    @Delete("DELETE FROM announcement WHERE id = #{id}")
    int deleteById(int id);

    @Insert("INSERT INTO announcement (id, context, time) VALUES (#{id}, #{context}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Announcement announcement);
}
