package com.example.demo.controller;

import com.example.demo.Service.UserLoginMessageService;
import com.example.demo.entity.UserLoginMessage;
import com.example.demo.entity.UserLoginMessageQueryParams;
import com.example.demo.mapper.UserLoginMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/UserLoginMessage")
public class UserLoginMessageController {
    @Autowired
    UserLoginMessageMapper userLoginMessageMapper;
    @Autowired
    UserLoginMessageService userLoginMessageService;

    @PostMapping("/search")
    public Map<String, Object> queryUserLoginMessages(@RequestBody UserLoginMessageQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            List<UserLoginMessage> userLoginMessagesList = userLoginMessageService.queryUserLoginMessages(params);
            int total = userLoginMessageService.getTotalTestQuestions();
            System.out.println(userLoginMessagesList);
            resultMap.put("userLoginMessagesList", userLoginMessagesList);
            resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
    }
}
