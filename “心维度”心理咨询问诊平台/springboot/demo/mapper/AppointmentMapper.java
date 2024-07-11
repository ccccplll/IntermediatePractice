package com.example.demo.mapper;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentExt;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;
import java.util.List;

public interface AppointmentMapper {
    @Select("<script>" +
            "SELECT a.*, c.trueName as cname, u.name as uname " +
            "FROM appointment a " +
            "JOIN consultant c ON a.cid = c.id " +
            "JOIN user u ON a.uid = u.id " +
            "<where> " +
            " <if test='cname != null'> AND c.trueName = #{cname} </if>" +
            " <if test='uname != null'> AND u.name = #{uname} </if>" +
            " <if test='state != null'> AND a.state = #{state} </if>" +
            " <if test='consultWay != null'> AND a.consultWay = #{consultWay} </if>" +
            " <if test='id != null'> AND a.id = #{id} </if>" +
            " <if test='createTime != null'> AND a.createTime = #{createTime} </if>" +
            "</where> " +
            "LIMIT #{offset}, #{pageSize}" +
            "</script>")
    List<AppointmentExt> queryAppointments(int offset, Integer pageSize, String cname, String uname, Integer id, String consultWay, Timestamp createTime, String state);
    @Select("SELECT COUNT(*) FROM appointment")
    int getTotalAppointments();
}
