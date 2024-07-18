package com.example.demo.Service.impl;

import com.example.demo.Service.CommentService;
import com.example.demo.entity.*;
import com.example.demo.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;
    @Override
    public List<CommentExt> queryComments(CommentQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return commentMapper.queryComments(offset, params.getPageSize(),params.getUname(), params.getCname());
    }

    @Override
    public int getTotalComments() {
        return commentMapper.getTotalComments();
    }
}
