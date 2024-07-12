package com.example.demo.Service;

import com.example.demo.entity.TestExt;
import com.example.demo.entity.TestQueryParams;
import com.example.demo.entity.TestQuestion;
import com.example.demo.entity.TestQuestionQueryParams;
import com.example.demo.mapper.TestQuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestQuestionService {

    @Autowired
    private TestQuestionMapper testQuestionMapper;

    public void addTestQuestion(TestQuestion testQuestion) {
        testQuestionMapper.insertTestQuestion(testQuestion);
    }
    public List<TestQuestion> queryTestQuestions(TestQuestionQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return testQuestionMapper.queryTestQuestions(
                offset,
                params.getPageSize(),
                params.getDate()
        );
    }

    public int getTotalTestQuestions() {
        return testQuestionMapper.getTotalTestQuestions();
    }
}
