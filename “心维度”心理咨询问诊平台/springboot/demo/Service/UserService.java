package com.example.demo.Service;
import com.example.demo.entity.User;
import com.example.demo.entity.UserQueryParams;

import java.util.List;

public interface UserService {

    List<User> queryUsers(UserQueryParams params);
    int getTotalUsers();

    void deleteUserById(Integer id);
}

