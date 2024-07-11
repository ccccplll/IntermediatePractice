package com.example.demo.controller;


import com.example.demo.Service.AppointmentService;
import com.example.demo.entity.Appointment;
import com.example.demo.entity.AppointmentExt;
import com.example.demo.entity.AppointmentQueryParams;
import com.example.demo.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Appointment")
@CrossOrigin
public class AppointmentController {
    @Autowired
    AppointmentService appointmentService;
    @Autowired
    AppointmentMapper appointmentMapper;
    @PostMapping("/search")
    public Map<String, Object> queryAppointments(@RequestBody AppointmentQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<AppointmentExt> appointmentsList = appointmentService.queryAppointments(params);
            int total = appointmentService.getTotalAppointments();
            System.out.println(appointmentsList);
            resultMap.put("appointmentsList", appointmentsList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
}
