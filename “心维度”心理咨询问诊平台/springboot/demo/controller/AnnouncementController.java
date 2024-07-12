package com.example.demo.controller;

import com.example.demo.Service.AnnouncementService;
import com.example.demo.entity.Announcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/Announcement")
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping("/findAll")
    public List<Announcement> findAll() {
        return announcementService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Announcement findById(@PathVariable("id") int id) {
        return announcementService.findById(id);
    }

    @DeleteMapping("/deleteById/{id}")
    public int deleteById(@PathVariable("id") int id) {

        return announcementService.deleteById(id);
    }

    @PostMapping("/insert")
    public int insert(@RequestBody Announcement announcement) {
        return announcementService.insert(announcement);
    }
}
