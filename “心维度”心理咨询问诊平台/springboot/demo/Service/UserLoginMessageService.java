package com.example.demo.Service;

import com.example.demo.entity.TestQuestion;
import com.example.demo.entity.TestQuestionQueryParams;
import com.example.demo.entity.UserLoginMessage;
import com.example.demo.entity.UserLoginMessageQueryParams;
import com.example.demo.mapper.TestQuestionMapper;
import com.example.demo.mapper.UserLoginMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLoginMessageService {

    @Autowired
    private UserLoginMessageMapper userLoginMessageMapper;

    public List<UserLoginMessage> queryUserLoginMessages(UserLoginMessageQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return userLoginMessageMapper.queryUserLoginMessages(
                offset,
                params.getPageSize(),
                params.getLoginTime()
        );
    }

    public int getTotalTestQuestions() {
        return userLoginMessageMapper.getTotalUserLoginMessages();
    }
}
