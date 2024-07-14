package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Service.TestQuestionService;
import com.example.demo.entity.*;
import com.example.demo.mapper.TestQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/TestQuestion")
public class TestQuestionController {

    @Autowired
    private TestQuestionService testQuestionService;

    @Autowired
    private TestQuestionMapper testQuestionMapper;

    @PostMapping("/addTestQuestion")
    public ResponseEntity<String> addTestQuestion(@RequestBody TestQuestion testQuestion) {
        try {
            testQuestionService.addTestQuestion(testQuestion);
            return ResponseEntity.ok("Test question added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding test question");
        }
    }

    @GetMapping("/list/{id}")
    public List<TestQuestion> list(@PathVariable int id) {
        // 构建查询条件
        QueryWrapper<TestQuestion> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);

        // 执行查询
        return testQuestionMapper.selectList(queryWrapper);
    }

    @PostMapping("/search")
    public Map<String, Object> queryTests(@RequestBody TestQuestionQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<TestQuestion> testQuestionsList = testQuestionService.queryTestQuestions(params);
            int total = testQuestionService.getTotalTestQuestions();
            System.out.println(testQuestionsList);
            resultMap.put("testQuestionsList", testQuestionsList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
}

