package com.example.demo.mapper;

import com.example.demo.entity.User;
import com.example.demo.entity.UserLoginMessage;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface UserLoginMessageMapper {


@Select("<script>" +
        "SELECT * FROM loginmessage " +
        "<where> " +
        " <if test='loginTime != null'> AND DATE(login_time) = DATE(#{loginTime}) </if>" +
        "</where> " +
        "LIMIT #{offset}, #{pageSize}" +
        "</script>")
    List<UserLoginMessage> queryUserLoginMessages(
                          @Param("offset") int offset,
                          @Param("pageSize") int pageSize,
                          @Param("loginTime") Date loginTime
    );

    @Select("SELECT COUNT(*) FROM loginmessage")
    int getTotalUserLoginMessages();
}
