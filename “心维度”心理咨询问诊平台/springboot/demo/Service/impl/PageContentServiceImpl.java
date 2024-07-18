package com.example.demo.Service.impl;

import com.example.demo.Service.PageContentService;
import com.example.demo.entity.PageContent;
import com.example.demo.entity.PageContentExt;
import com.example.demo.mapper.PageContentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PageContentServiceImpl implements PageContentService {
    @Autowired
    PageContentMapper pageContentMapper;

    @Override
    public List<PageContentExt> findById(int id) {
        return pageContentMapper.findById(id);
    }
}
