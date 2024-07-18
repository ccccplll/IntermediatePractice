package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Consultant;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    @Select("<script>" +
            "SELECT * FROM user " +
            "<where> " +
            " <if test='name != null'> AND name = #{name} </if>" +
            " <if test='city != null'> AND city = #{city} </if>" +
            " <if test='age != null'> AND age = #{age} </if>" +
            " <if test='sex != null'> AND sex = #{sex} </if>" +
            " <if test='id != null'> AND id = #{id} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<User> queryUsers(@Param("offset") int offset,
                          @Param("pageSize") int pageSize,
                          @Param("name") String name,
                          @Param("city") String city,
                          @Param("age") Integer age,
                          @Param("sex") String sex,
                          @Param("id") Integer id);
    @Select("SELECT COUNT(*) FROM user")
    int getTotalUsers();
    @Select("SELECT id, name, city, age, password, u_condition, sex, email, phoneNumber, avatar FROM user WHERE id = #{id}")
    @Results({
            @Result(property = "uCondition", column = "u_condition"),
    })
    User getUserById(int id);

    @Update("UPDATE user SET seemessage = '未看过' WHERE id = #{id}")
    void  setUserSeeMessageState(@Param("id") int id);
    @Select("SELECT phoneNumber FROM consultant WHERE id = #{id}")
    String getPhoneNumberById(Integer id);
}
