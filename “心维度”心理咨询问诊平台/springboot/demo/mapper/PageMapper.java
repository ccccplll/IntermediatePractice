package com.example.demo.mapper;

import com.example.demo.entity.Page;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PageMapper {
    @Select("SELECT * FROM page")
    List<Page> findAll();

    @Select("SELECT * FROM page WHERE id = #{id}")
    Page findById(int id);

    @Delete("DELETE FROM page WHERE id = #{id}")
    int deleteById(int id);

    @Insert("INSERT INTO page (id, context, time) VALUES (#{id}, #{context}, #{time})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(Page page);
}
