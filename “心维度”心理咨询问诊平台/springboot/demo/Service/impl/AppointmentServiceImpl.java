package com.example.demo.Service.impl;


import com.example.demo.Service.ApplyService;
import com.example.demo.Service.AppointmentService;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentExt;
import com.example.demo.entity.AppointmentQueryParams;
import com.example.demo.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentMapper appointmentMapper;


    @Override
    public List<AppointmentExt> queryAppointments(AppointmentQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return appointmentMapper.queryAppointments(
                offset,
                params.getPageSize(),
                params.getCname(),
                params.getUname(),
                params.getId(),
                params.getConsultWay(),
                params.getCreateTime(),
                params.getState());
    }

    @Override
    public int getTotalAppointments() {
        return appointmentMapper.getTotalAppointments();
    }


}
