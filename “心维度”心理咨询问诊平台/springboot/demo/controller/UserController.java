package com.example.demo.controller;

import com.example.demo.Service.UserService;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQueryParams;
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
