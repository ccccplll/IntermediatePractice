package com.example.demo.mapper;


import com.example.demo.entity.TestQuestion;
import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface TestQuestionMapper {

    @Insert("INSERT INTO testquestions (date, score_a, score_b, score_c, score_d, question_context, A, B, C, D) " +
            "VALUES (#{date}, #{scoreA}, #{scoreB}, #{scoreC}, #{scoreD}, #{questionContext}, #{a}, #{b}, #{c}, #{d})")
    void insertTestQuestion(TestQuestion testQuestion);

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
    @Select("<script>" +
            "SELECT * FROM testquestions " +
            "<where> " +
            " <if test='date != null'> AND date = #{date} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<TestQuestion> queryTestQuestions(int offset, Integer pageSize, Date date);

    @Select("SELECT COUNT(*) FROM testquestions")
    int getTotalTestQuestions();
}
