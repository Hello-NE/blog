package com.example.blog.controller;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.User;
import com.example.blog.service.UserService;
import com.example.blog.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public Result login(@RequestBody Map<String, User> para) {
        User u = para.get("user");
        User user = userService.checkUser(u.getUsername(), u.getPassword());

        if (user != null) {
            String token = TokenUtils.sign(user);
            Map<String, Object> info = new HashMap<>();
            user.setLoginProvince(u.getLoginProvince());
            user.setLoginCity(u.getLoginCity());
            user.setLoginLat(u.getLoginLat());
            user.setLoginLng(u.getLoginLng());
            user.setLastLoginTime(new Date());
            User newUser = userService.save(user);
            info.put("user", newUser);
            info.put("token", token);
            return new Result(true, StatusCode.OK, "admin login success", info);
        } else {
            return new Result(true, StatusCode.ERROR, "admin login failed", null);
        }
    }

}
