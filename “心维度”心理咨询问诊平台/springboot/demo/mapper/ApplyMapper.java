package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Apply;
import com.example.demo.entity.ApplyExt;
import com.example.demo.entity.ApplyQueryParams;
import com.example.demo.entity.Consultant;
import lombok.Data;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface ApplyMapper extends BaseMapper<Apply> {
    @Select("<script>" +
            "SELECT a.*, c.trueName as name " +
            "FROM apply a " +
            "JOIN consultant c ON a.cid = c.id " +
            "<where> " +
            " <if test='name != null'> AND c.trueName = #{name} </if>" +
            " <if test='astate != null'> AND a.astate = #{astate} </if>" +
            " <if test='cid != null'> AND a.cid = #{cid} </if>" +
            " <if test='id != null'> AND a.id = #{id} </if>" +
            " <if test='date != null'> AND a.date = #{date} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<ApplyExt> queryApplys(@Param("offset") int offset,
                               @Param("pageSize") int pageSize,
                               @Param("name") String name,
                               @Param("astate") String astate,
                               @Param("cid") Integer cid,
                               @Param("date") Date date,
                               @Param("id") Integer id);
//    @Select("<script>" +
//            "SELECT * FROM apply " +
//            "<where> " +
//            " <if test='a_state != null'> AND a_state = #{a_state} </if>" +
//            " <if test='c_id != null'> AND c_id = #{c_id} </if>" +
//            " <if test='id != null'> AND id = #{id} </if>" +
//            " <if test='date != null'> AND date = #{date} </if>" +
//            "</where> " +
//            "LIMIT #{offset}, #{pageSize}" +
//            "</script>")
//    List<ApplyExt> queryApplys(@Param("offset") int offset,
//                               @Param("pageSize") int pageSize,
//                               @Param("name") String name,
//                               @Param("a_state") String a_state,
//                               @Param("c_id") Integer c_id,
//                               @Param("date") Date date,
//                               @Param("id") Integer id);
    @Update("UPDATE apply SET astate = 'pass' WHERE id = #{id}")
    void passApplyById(@Param("id") int id);
    @Update("UPDATE apply SET astate = 'unpass' WHERE id = #{id}")
    void unpassApplyById(@Param("id") int id);
    @Select("SELECT COUNT(*) FROM apply")
    int getTotalApplys();
}
