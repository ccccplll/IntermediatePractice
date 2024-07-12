package com.example.demo.controller;

import com.example.demo.Service.ApplyService;
import com.example.demo.Service.TestService;
import com.example.demo.entity.ApplyExt;
import com.example.demo.entity.ApplyQueryParams;
import com.example.demo.entity.TestExt;
import com.example.demo.entity.TestQueryParams;
import com.example.demo.mapper.ApplyMapper;
import com.example.demo.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Test")
@CrossOrigin
public class TestController {
    @Autowired
    TestMapper testMapper;
    @Autowired
    TestService testService;
    @PostMapping("/search")
    public Map<String, Object> queryTests(@RequestBody TestQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<TestExt> testsList = testService.queryTests(params);
            int total = testService.getTotalTests();
            System.out.println(testsList);
            resultMap.put("testsList", testsList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }

}
