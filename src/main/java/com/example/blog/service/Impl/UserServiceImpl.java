package com.example.blog.service.Impl;

import com.example.blog.mapper.UserMapper;
import com.example.blog.pojo.User;
import com.example.blog.service.UserService;
import com.example.blog.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User checkUsernameAndPassword(String username, String password) {
        return userMapper.checkUsernameAndPassword(username, MD5Utils.code(password));
    }
}