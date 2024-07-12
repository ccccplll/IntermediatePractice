package com.example.demo.Service;

import com.example.demo.entity.Announcement;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AnnouncementService {
    List<Announcement> findAll();
    Announcement findById(int id);
    int deleteById(int id);
    int insert(Announcement announcement);
}
