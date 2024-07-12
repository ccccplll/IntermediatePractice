package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Consultant;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ConsultantMapper extends BaseMapper<Consultant> {
    @Select("<script>" +
            "SELECT * FROM consultant " +
            "<where> " +
            " <if test='trueName != null'> AND trueName = #{trueName} </if>" +
            " <if test='city != null'> AND city = #{city} </if>" +
            " <if test='age != null'> AND age = #{age} </if>" +
            " <if test='sex != null'> AND sex = #{sex} </if>" +
            " <if test='id != null'> AND id = #{id} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<Consultant> queryConsultants(@Param("offset") int offset,
                                      @Param("pageSize") int pageSize,
                                      @Param("trueName") String trueName,
                                      @Param("city") String city,
                                      @Param("age") Integer age,
                                      @Param("sex") String sex,
                                      @Param("id") Integer id);
    @Select("SELECT COUNT(*) FROM consultant")
    int getTotalConsultants();
}
