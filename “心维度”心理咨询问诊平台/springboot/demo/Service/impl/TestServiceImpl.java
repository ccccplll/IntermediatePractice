package com.example.demo.Service.impl;

import com.example.demo.Service.TestService;
import com.example.demo.entity.TestExt;
import com.example.demo.entity.TestQueryParams;
import com.example.demo.mapper.ApplyMapper;
import com.example.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Override
    public List<TestExt> queryTests(TestQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        System.out.println(params.getUid());

        System.out.println(params);
        return testMapper.queryTests(
                offset,
                params.getPageSize(),
                params.getName(),
                params.getCondition(),
                params.getId(),
                params.getUid()
        );
    }

    @Override
    public int getTotalTests() {
        return testMapper.getTotalTests();
    }

}
