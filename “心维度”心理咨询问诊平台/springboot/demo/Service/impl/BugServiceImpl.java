package com.example.demo.Service.impl;

import com.example.demo.Service.BugService;
import com.example.demo.entity.Bug;
import com.example.demo.mapper.BugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BugServiceImpl implements BugService {
    @Autowired
    private BugMapper bugMapper;

    @Override
    public List<Bug> findAll() {
        return bugMapper.findAll();
    }

    @Override
    public Bug findById(int id) {
        return bugMapper.findById(id);
    }

    @Override
    public int deleteById(int id) {
        return bugMapper.deleteById(id);
    }
}
