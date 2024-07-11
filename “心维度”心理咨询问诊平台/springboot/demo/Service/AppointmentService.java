package com.example.demo.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentExt;
import com.example.demo.entity.AppointmentQueryParams;

import java.util.List;

public interface AppointmentService {
    List<AppointmentExt> queryAppointments(AppointmentQueryParams params);
    int getTotalAppointments();

}
