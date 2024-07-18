package com.example.demo.mapper;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Bug;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BugMapper {
    @Select("SELECT * FROM buglist")
    @Results({
            @Result(property = "content", column = "bug"),
    })
    List<Bug> findAll();

    @Select("SELECT * FROM buglist WHERE id = #{id}")
    Bug findById(int id);

    @Delete("DELETE FROM buglist WHERE id = #{id}")
    int deleteById(int id);

}
