package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.Service.UserService;
import com.example.demo.entity.*;
import com.example.demo.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/User")
public class UserController {
    @Autowired
    TestController testService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    private UserService userService;
    @RequestMapping("/insert")
    public String insert(String name,Integer age,String address){
        return userMapper.insert(new User(name,age,address))>0?"success!":"fail";
    }

    @RequestMapping("/list")
    public List<User> list(){
        return userMapper.selectList(null);
    }
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable  int id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("User deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/getUser/{id}")
    public User list(@PathVariable int id) {
        // 构建查询条件
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", id);

        // 执行查询
        return userMapper.getUserById(id);
    }
//    @GetMapping("/getUserTest/{id}")
//    public List<TestExt> getTest(@PathVariable int id) {
//        // 构建查询条件
////        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
////        queryWrapper.eq("id", id);
//        TestQueryParams params = new TestQueryParams();
//        params.setId(id);
//        List<TestExt> testList = (List<TestExt>) testService.queryTests(params);
//        // 执行查询
//        return userMapper.getUserById(id);
//    }
   private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping("/search")
   public Map<String, Object> queryUsers(@RequestBody UserQueryParams params) {
        Map<String, Object> resultMap = new HashMap<>();
        logger.debug("Received queryUsers request with pageParam: {}", params.getPageNum());
        logger.debug("Received queryUsers request with pageParam: {}", params.getPageSize());
        try {
        logger.debug("Received queryUsers request with pageParam: {}", params);
        List<User> usersList = userService.queryUsers(params);
        int total = userService.getTotalUsers();
        System.out.println(usersList);
        resultMap.put("usersList", usersList);
        resultMap.put("total", total);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("error", e.getMessage());
        }
        return resultMap;
   }
}
