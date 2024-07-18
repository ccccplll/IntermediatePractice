package com.example.demo.Service.impl;

import com.example.demo.Service.PageService;
import com.example.demo.entity.Page;
import com.example.demo.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageServiceImpl implements PageService {
    @Autowired
    private PageMapper pageMapper;
    @Override
    public List<Page> findAll() {
        return pageMapper.findAll();
    }

    @Override
    public Page findById(int id) {
        return pageMapper.findById(id);
    }

    @Override
    public int deleteById(int id) {
        return pageMapper.deleteById(id);
    }

    @Override
    public int insert(Page page) {
        return pageMapper.insert(page);
    }
}
