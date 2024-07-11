package com.example.demo.Service.impl;
//import com.example.demo.Dao.UserDao;
import com.example.demo.Service.UserService;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQueryParams;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> queryUsers(UserQueryParams params) {
        int offset = (params.getPageNum() - 1) * params.getPageSize();
        return userMapper.queryUsers(offset, params.getPageSize(), params.getName(), params.getCity(), params.getAge(), params.getSex(), params.getId());
    }

    @Override
    public int getTotalUsers() {
        return userMapper.getTotalUsers();
    }

    @Override
    public void deleteUserById(Integer id) {
        userMapper.deleteById(id);
    }
}

