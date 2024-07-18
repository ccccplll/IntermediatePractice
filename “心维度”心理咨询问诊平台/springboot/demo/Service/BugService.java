package com.example.demo.Service;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Bug;

import java.util.List;

public interface BugService {
    List<Bug> findAll();
    Bug findById(int id);
    int deleteById(int id);
}
