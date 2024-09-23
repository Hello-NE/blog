package com.example.blog.service;

import com.example.blog.pojo.User;

public interface UserService {
    User checkUsernameAndPassword(String username, String password);
}
