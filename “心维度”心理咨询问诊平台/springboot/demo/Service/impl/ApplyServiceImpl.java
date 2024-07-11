package com.example.demo.Service.impl;

import com.example.demo.Service.ApplyService;
import com.example.demo.entity.*;
import com.example.demo.mapper.ApplyMapper;
import com.example.demo.mapper.ConsultantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplyServiceImpl implements ApplyService {

    @Autowired
    private ApplyMapper applyMapper;


    @Override
    public List<ApplyExt> queryApplys(ApplyQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return applyMapper.queryApplys(offset, params.getPageSize(), params.getName(), params.getAstate(), params.getCid(), params.getDate(), params.getId());
    }

    @Override
    public int getTotalApplys() {
        return applyMapper.getTotalApplys();
    }

    @Override
    public void passApplyById(Integer id) {
        applyMapper.passApplyById(id);
    }

    @Override
    public void unpassApplyById(Integer id) {
        applyMapper.unpassApplyById(id);
    }
}
