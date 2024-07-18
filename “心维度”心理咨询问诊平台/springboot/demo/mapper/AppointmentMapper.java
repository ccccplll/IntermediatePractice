package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.Apply;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentExt;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentMapper extends BaseMapper<Appointment> {
    @Select("<script>" +
            "SELECT a.*, c.trueName as cname, u.name as uname " +
            "FROM appointment a " +
            "JOIN consultant c ON a.c_id = c.id " +
            "JOIN user u ON a.u_id = u.id " +
            "<where> " +
            " <if test='cname != null'> AND c.trueName = #{cname} </if>" +
            " <if test='uname != null'> AND u.name = #{uname} </if>" +
            " <if test='state != null'> AND a.state = #{state} </if>" +
            " <if test='consultWay != null'> AND a.consult_way = #{consultWay} </if>" +
            " <if test='id != null'> AND a.id = #{id} </if>" +
            " <if test='createTime != null'> AND a.create_time = #{createTime} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "consultWay", column = "consult_way"),
            @Result(property = "cid", column = "c_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "uid", column = "u_id"),
    })
    List<AppointmentExt> queryAppointments(int offset, Integer pageSize, String cname, String uname, Integer id, String consultWay, Timestamp createTime, String state);
    @Select("SELECT COUNT(*) FROM appointment")
    int getTotalAppointments();
    @Select("SELECT u_id FROM appointment WHERE p_id = #{pid} AND state = '待完成' " )
    Integer getUid(int pid);
}
