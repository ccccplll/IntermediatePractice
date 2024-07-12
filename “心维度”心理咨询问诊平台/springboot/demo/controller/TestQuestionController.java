package com.example.demo.controller;

import com.example.demo.Service.TestQuestionService;
import com.example.demo.entity.TestExt;
import com.example.demo.entity.TestQueryParams;
import com.example.demo.entity.TestQuestion;
import com.example.demo.entity.TestQuestionQueryParams;
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

    @PostMapping("/addTestQuestion")
    public ResponseEntity<String> addTestQuestion(@RequestBody TestQuestion testQuestion) {
        try {
            testQuestionService.addTestQuestion(testQuestion);
            return ResponseEntity.ok("Test question added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error adding test question");
        }
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

