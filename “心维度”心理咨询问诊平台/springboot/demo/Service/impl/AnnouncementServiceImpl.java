package com.example.demo.Service.impl;

import com.example.demo.Service.AnnouncementService;
import com.example.demo.entity.Announcement;
import com.example.demo.mapper.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementServiceImpl implements AnnouncementService {

    @Autowired
    private AnnouncementMapper announcementMapper;
    @Override
    public List<Announcement> findAll() {
        return announcementMapper.findAll();
    }

    @Override
    public Announcement findById(int id) {
        return announcementMapper.findById(id);
    }

    @Override
    public int deleteById(int id) {
        return announcementMapper.deleteById(id);
    }

    @Override
    public int insert(Announcement announcement) {
        return announcementMapper.insert(announcement);
    }
}
