package com.example.blog.controller.Admin;


import com.example.blog.po.Result;
import com.example.blog.po.StatusCode;
import com.example.blog.po.User;
import com.example.blog.service.UserService;
import com.example.blog.util.MD5Utils;
import com.example.blog.util.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result login(@RequestHeader Map<String, Object> header, @RequestBody Map<String, Object> para) {
        String username = para.get("username").toString();
        String password = MD5Utils.code(para.get("password").toString());
        User user = userService.checkUser(username, password);
        if (user != null) {
            String token = TokenUtils.sign(user);
            Map<String, Object> info = new HashMap<String, Object>();
            info.put("user", user);
            info.put("token", token);
            return new Result(true, StatusCode.OK, "管理员登录成功", info);
        } else {
            return new Result(false, StatusCode.OK, "管理员登录失败", null);

        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
