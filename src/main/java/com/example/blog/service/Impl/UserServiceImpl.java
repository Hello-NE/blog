package com.example.blog.service.Impl;

import com.example.blog.dao.UserRepository;
import com.example.blog.po.User;
import com.example.blog.service.UserService;
import com.example.blog.util.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }

    @Transactional
    @Override
    public User findUserById(Long id) {
        return userRepository.getOne(id);
    }

    @Override
    public User save(User user) {
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        if (user.getAvatar() == null || "".equals(user.getAvatar())) {
            user.setAvatar("http://test.png");
        }
        return userRepository.save(user);
    }

    @Transactional
    @Override
    public User updateUser(Long id, User admin) {
        User u = userRepository.getOne(id);
        BeanUtils.copyProperties(admin, u, MyBeanUtils.getNullPropertyNames(admin));
        return userRepository.save(u);
    }

    @Override
    public List<User> listUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

}
