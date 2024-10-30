package com.example.blog.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.blog.po.User;

import java.util.List;

public interface UserService  {
    User checkUser(String username, String password);

    User findUserById(Long id);

    User save(User user);

    User updateUser(Long id, User admin);

    List<User> listUser();

    void deleteUserById(Long id);
}
