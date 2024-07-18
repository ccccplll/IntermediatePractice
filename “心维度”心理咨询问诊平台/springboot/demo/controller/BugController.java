package com.example.demo.controller;

import com.example.demo.Service.AppointmentService;
import com.example.demo.Service.BugService;
import com.example.demo.entity.Announcement;
import com.example.demo.entity.Bug;
import com.example.demo.mapper.AppointmentMapper;
import com.example.demo.mapper.BugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Bug")
@CrossOrigin
public class BugController {
    @Autowired
    BugService bugService;
    @Autowired
    BugMapper bugMapper;

    @GetMapping("/findAll")
    public List<Bug> findAll() {
        return bugService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Bug findById(@PathVariable("id") int id) {
        return bugService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public int deleteById(@PathVariable("id") int id) {
        return bugService.deleteById(id);
    }
}
