package com.example.demo.Service.impl;

import com.example.demo.Service.ConsultantService;
import com.example.demo.entity.Consultant;
import com.example.demo.entity.ConsultantQueryParams;
import com.example.demo.mapper.ConsultantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultantServiceImpl implements ConsultantService {

    @Autowired
    private ConsultantMapper consultantMapper;


    @Override
    public List<Consultant> queryConsultants(ConsultantQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return consultantMapper.queryConsultants(offset, params.getPageSize(), params.getTrueName(), params.getCity(), params.getAge(), params.getSex(), params.getId());
    }

    @Override
    public int getTotalConsultants() {
        return consultantMapper.getTotalConsultants();
    }

    @Override
    public void deleteConsultantById(Integer id) {
        consultantMapper.deleteById(id);
    }
}
