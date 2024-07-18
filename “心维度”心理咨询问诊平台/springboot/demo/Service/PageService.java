package com.example.demo.Service;

import com.example.demo.entity.Announcement;
import com.example.demo.entity.Page;

import java.util.List;

public interface PageService {
    List<Page> findAll();
    Page findById(int id);
    int deleteById(int id);
    int insert(Page page);
}
