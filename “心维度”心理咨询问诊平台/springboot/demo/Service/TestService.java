package com.example.demo.Service;

import com.example.demo.entity.TestExt;
import com.example.demo.entity.TestQueryParams;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQueryParams;

import java.util.List;

public interface TestService {
    List<TestExt> queryTests(TestQueryParams params);
    int getTotalTests();

}
